package com.abila.Store.util;

import com.abila.Store.domain.Clientes;
import com.abila.Store.domain.ItemVendas;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;
import java.util.List;
import java.math.BigDecimal;

@UtilityClass
public class Utils {
    //FUNCOES
    public static void validarDocumentos(Clientes clientes){
        boolean temCpf = clientes.getCpf() != null && !clientes.getCpf().isBlank();
        boolean temCnpj = clientes.getCnpj() != null && !clientes.getCnpj().isBlank();

        if(temCpf && !validacaoCpf(clientes.getCpf())) throw new RuntimeException("Cpf ou cnpj informado é inválido");

        if(temCnpj && !validacaoCnpj(clientes.getCnpj())) throw new RuntimeException("Cpf ou cnpj informado é inválido");

        if(!temCpf && !temCnpj) throw new RuntimeException("Documentos invalidos ou não informados");
    }

    public static boolean validacaoCpf(String cpf){
        if(cpf == null) return false;
        int[] numeros = converterParaArray(cpf, 11);

        int digito1 = calcularDigito(numeros, new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2});
        int digito2 = calcularDigito(numeros, new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2});

        return digito1 == numeros[9] && digito2 == numeros[10];
    }

    public static boolean validacaoCnpj(String cnpj){
        if(cnpj == null) return false;
        int[] numeros = converterParaArray(cnpj, 14);

        int digito1 = calcularDigitoInvertido(numeros, 12);
        int digito2 = calcularDigitoInvertido(numeros, 13);

        return digito1 == numeros[12] && digito2 == numeros [13];
    }

    private static int calcularDigitoInvertido(int[] numeros, int quantidade){
        int soma = 0;
        int peso = 2;

        for(int i = quantidade - 1;  i >= 0; i--){
            soma += numeros[i] * peso;
            peso++;

            if(peso > 9) peso = 2;
        }

        int resto = soma % 11;
        return (resto < 2)? 0: 11 - resto;
    }

    public static int calcularDigito(int[] numeros, int[] pesos){
        int soma = 0;
        for (int i = 0; i < pesos.length; i++){
            soma += numeros[i] * pesos[i];
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    public static int[] converterParaArray(String texto, int tamanho){
        String valorLimpo = texto.replaceAll("(\\D)", "");
        String regexRepetidos = "(\\d)\\1{"+(tamanho - 1)+"}";
        if (valorLimpo.length() != tamanho || valorLimpo.matches(regexRepetidos)) return null;

        int[] numeros = new int[tamanho];
        for(int i = 0; i < tamanho; i++){
            numeros[i] = Character.getNumericValue(valorLimpo.charAt(i));
        }
        return numeros;
    }

    public static BigDecimal calcularTotalVenda(List<ItemVendas> itemVendas){
        if(itemVendas == null || itemVendas.isEmpty()){
            return BigDecimal.ZERO;
        }

        return itemVendas.stream()
                .map(ItemVendas::getPrecoUnitario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
