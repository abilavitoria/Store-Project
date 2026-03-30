package com.abila.Store.service;
import com.abila.Store.domain.Clientes;
import com.abila.Store.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepo;

    //consultar
    public Optional<Clientes> findById(Integer id){
        return clienteRepo.findById(id);
    }

    //cadastrar
    public Clientes saveClientes(Clientes clientes){
        if(!validacaoCpf(clientes.getCpf())){
            throw new RuntimeException("CPF informado não é valido!");
        }
        return clienteRepo.save(clientes);
    }

    //excluir
    public void deleteClientes(Integer id){
        if(!clienteRepo.existsById(id)){
            throw new RuntimeException("Cliente não encontrado");
        }
        else{clienteRepo.deleteById(id);}
    }

    //editar
    public Clientes updateClientes(Clientes clientes, Integer id){
       return clienteRepo.findById(id)
               .map(existingClientes -> {
                    clientes.setId(id);
                    return clienteRepo.save(clientes);
               })
               .orElseThrow(()-> new RuntimeException("Cliente com id" + id + "não encontrado"));
    }

    //FUNCOES
    public boolean validacaoCpf(String cpf){
        cpf = cpf.replaceAll("\\D", "");

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

         if(penultimo == numeros[9] && ultimo == numeros[10]){
             return true;
         }
         else {
             return false;
         }
    }

    public boolean validacaoCnpj(String cnpj){
        cnpj = cnpj.replaceAll("\\D", "");

        if(cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) return false;

        int[] numeros = new int[14];
        for(int i = 0; i < 14; i++){
            numeros[i] = Character.getNumericValue(cnpj.charAt(13 - i));
        }

        //VERIFICACAO PENULTIMO DIGITO
        int soma1 = 0;
        int peso1 = 8;

        for(int i = 2; i <= 9; i++){
            soma1 += numeros[i] * peso1;
            peso1--;
        }

        int resto1 = soma1 % 11;
        int penultimo = (resto1 < 2)? 0: 11 - resto1;

        //VERIFICACAO ULTIMO DIGITO
        int soma2 = 0;
        int peso2 = 8;

        for(int i = 2; i<=9; i++){
            soma2 += numeros[i] * peso2;
            peso2--;
        }

        int resto2 = soma2 % 11;
        int ultimo = (resto2 < 2) ? 0: 11 - resto2;

        if(penultimo == numeros[12] && ultimo == numeros[13]){
            return true;
        }else{
            return false;
        }
    }

}
