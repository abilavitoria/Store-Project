package com.abila.Store.domain.DTO;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(
        @NotBlank String login,
        @NotBlank String senha,
                  String role
) {
}
