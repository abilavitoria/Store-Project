package com.abila.Store.domain.DTO;

import com.abila.Store.domain.Usuario;

public record UsuarioResponseDTO(
        Integer id,
        String login,
        String senha,
        String role
) {
    public UsuarioResponseDTO(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getRoles()
        );
    }
}
