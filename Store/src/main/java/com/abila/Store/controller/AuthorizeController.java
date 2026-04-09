package com.abila.Store.controller;

import com.abila.Store.domain.DTO.DadosAutenticacao;
import com.abila.Store.domain.DTO.DadosTokenJWT;
import com.abila.Store.domain.UserRoles;
import com.abila.Store.domain.Usuario;
import com.abila.Store.repository.UsuarioRepository;
import com.abila.Store.service.TokenService;
import jakarta.validation.Valid;
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
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody @Valid Usuario usuario){
        if(usuarioRepo.findByLogin(usuario.getLogin()) != null){
            return ResponseEntity.badRequest().body("Erro!:usuario ja existe");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        if (usuario.getRole() == null) usuario.setRole(UserRole.CLIENTE);

        usuarioRepo.save(usuario);
        return ResponseEntity.ok("Usuario cadastrado com sucesso!" + usuario.getRole());
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
