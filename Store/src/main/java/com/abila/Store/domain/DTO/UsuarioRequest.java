package com.abila.Store.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(
        @NotBlank String login,
        @NotBlank String senha,
                  String role
) {
}
