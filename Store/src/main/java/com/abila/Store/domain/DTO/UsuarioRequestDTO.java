package com.abila.Store.domain.DTO;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank String login,
        @NotBlank String senha,
                  String role
) {
}
