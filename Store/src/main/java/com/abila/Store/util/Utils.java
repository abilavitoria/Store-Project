package com.abila.Store.util;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

@UtilityClass
public class Utils {
    //FUNCOES
    public static boolean validacaoCpf(String cpf){
        if(cpf == null) return false;
        cpf.replaceAll("\\D", "");

        if(cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        int[] numeros = new int[11];
        for(int i = 0; i < 11; i++){
            numeros[i] = Character.getNumericValue(cpf.charAt(i));
        }

        //VERIFICACAO PENULTIMO DIGITO
        int soma1 = 0;
        int peso1 = 10;

        for (int i = 0; i < 9; i++){
            soma1 += numeros[i] * peso1;
            peso1--;
        }

        int resto1 = soma1 % 11;
        int penultimo = (resto1 < 2) ? 0 : 11 - resto1;

        //VERIFICACAO ULTIMO DIGITO
        int soma2 = 0;
        int peso2 = 11;
        for(int i = 0; i < 10; i++){
            soma2 += numeros[i] * peso2;
            peso2--;
        }

        int resto2 = soma2 % 11;
        int ultimo = (resto2 < 2 ) ? 0: 11 - resto2;

        return (penultimo == numeros[9] && ultimo == numeros[10]);
    }

    public static boolean validacaoCnpj(String cnpj){
        if(cnpj == null) return false;
        cnpj = cnpj.replaceAll("\\D", "");

        if(cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) return false;

        int[] pesoCnpj = {6,7,8,9,2,3,4,5,6,7,8,9};

        return digitoCnpj(cnpj.substring(0,12), 5) == Character.getNumericValue(cnpj.charAt(12)) &&
                digitoCnpj(cnpj.substring(0, 13), 6) == Character.getNumericValue(cnpj.charAt(13));


    private static int digitoCnpj(String substring, int i) {
        int soma = 0;
        int peso = pesoCnpj;
        for (int i = substring.length() - 1; i >= 0; i--){
            int digito = Character.getNumericValue(substring.charAt(i));
            soma += digito * peso;
            peso = (peso == 9) ? 2 : peso + 1;
        }
        int resto = soma%11;
        return (resto < 2)? 0 : 11 - resto;
    }
}
