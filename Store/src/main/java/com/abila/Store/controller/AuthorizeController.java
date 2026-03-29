package com.abila.Store.controller;

import com.abila.Store.domain.Usuario;
import com.abila.Store.repository.UsuarioRepository;
import com.abila.Store.service.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@RequiredArgsConstructor

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/auth")
public class AuthorizeController {
    private final UsuarioRepository usuarioRepo;

    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> cadastrar(@RequestBody Usuario usuario){
        if(usuarioRepo.findByLogin(usuario.getLogin()).isPresent()){
            return ResponseEntity.badRequest().body("Erro!:usuario ja existe");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        if (usuario.getRoles() == null){
            usuario.setRoles("ROLE_USER");
        }

        usuarioRepo.save(usuario);
        return ResponseEntity.ok("Usuario cadastrado com sucesso!");
    }
}
