package com.abila.Store.domain.DTO;

import com.abila.Store.domain.Clientes;
import com.abila.Store.domain.Vendas;
import jakarta.validation.constraints.NotBlank;

public record ClienteResponse(
        Integer id,
        String nome,
        String email,
        String telefone,
        String documentos,
        Vendas vendas
) {
    public ClienteResponse(Clientes clientes){
        this(
                clientes.getId(),
                clientes.getNome(),
                clientes.getEmail(),
                clientes.getTelefone(),
                documentos(clientes.getCpf(), clientes.getCnpj()),
                clientes.getVendas()
        );
    }

    private static String documentos(String cpf, String cnpj){
        if (cpf != null && !cpf.isEmpty()){
            return cpf.substring(0, 3) + "##.###-##";
        }
        if (cnpj != null && !cnpj.isEmpty()){
            return cnpj.substring(0, 3) + "#.###/####-##";
        }
        return "Documento não informado";
    }
}
