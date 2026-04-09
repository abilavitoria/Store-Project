package com.abila.Store.domain.DTO;

import com.abila.Store.domain.UserRoles;
import com.abila.Store.domain.Usuario;

public record UsuarioResponse(
        Integer id,
        String login,
        String senha,
        UserRoles role
) {
    public UsuarioResponse(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getRole()
        );
    }
}
