package com.abila.Store.domain.DTO;

import com.abila.Store.domain.Vendas;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
        @NotBlank String nome,
        @NotBlank String email,
        String telefone,
        String cpf,
        String cnpj,
        Vendas vendas
) {
}
