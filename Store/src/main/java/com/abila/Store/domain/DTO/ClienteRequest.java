package com.abila.Store.domain.DTO;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
        @NotBlank String nome,
        @NotBlank String email,
        String telefone,
        String cpf,
        String cnpj
) {
}
