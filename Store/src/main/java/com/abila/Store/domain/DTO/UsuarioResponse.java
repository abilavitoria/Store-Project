package com.abila.Store.domain.DTO;

import com.abila.Store.domain.Usuario;

public record UsuarioResponse(
        Integer id,
        String login,
        String senha,
        String role
) {
    public UsuarioResponse(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getRoles()
        );
    }
}
