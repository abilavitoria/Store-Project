package com.abila.Store.controller;

import com.abila.Store.domain.Usuario;
import com.abila.Store.repository.UsuarioRepository;
import com.abila.Store.service.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping("/auth")
public class AuthorizeController {
    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody Usuario usuario){
        if(usuarioRepo.findByLogin(usuario.getLogin()).isPresent()){
            return ResponseEntity.badRequest().body("Erro!:usuario ja existe");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        if (usuario.getRoles() == null) usuario.setRoles("ROLE_USER");

        usuarioRepo.save(usuario);
        return ResponseEntity.ok("Usuario cadastrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario){
        var credentials = new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha());
        var authentication = authenticationManager.authenticate(credentials);

        return ResponseEntity.ok("Login efetuado com sucesso");
    }
}
