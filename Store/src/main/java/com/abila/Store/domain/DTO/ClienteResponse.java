package com.abila.Store.domain.DTO;

import com.abila.Store.domain.Clientes;
import jakarta.validation.constraints.NotBlank;

public record ClienteResponse(
        Integer id,
        String nome,
        String email,
        String telefone,
        String cpf,
        String cnpj
) {
    public ClienteResponse(Clientes clientes){
        this(
                clientes.getId(),
                clientes.getNome(),
                clientes.getEmail(),
                clientes.getTelefone(),
                clientes.getCpf(),
                clientes.getCnpj()
        );
    }
}
