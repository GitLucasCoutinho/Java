package com.example.ocooldev.jwt.controller;

import com.example.ocooldev.jwt.model.User;
import com.example.ocooldev.jwt.dtos.Login;
import com.example.ocooldev.jwt.dtos.Sessao;
import com.example.ocooldev.jwt.repository.UserRepository;
import com.example.ocooldev.jwt.security.SecurityConfig;
import com.example.ocooldev.jwt.security.JWTCreator;
import com.example.ocooldev.jwt.security.JWTObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class LoginController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity<Sessao> logar(@RequestBody Login login) {
        try {
            User user = repository.findByUsername(login.getUsername());

            if (user != null) {
                boolean passwordOk = encoder.matches(login.getPassword(), user.getPassword());
                if (!passwordOk) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }

                // Criando objeto JWT
                JWTObject jwtObject = new JWTObject();
                jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
                jwtObject.setExpiration(new Date(System.currentTimeMillis() + securityConfig.getExpiration()));
                jwtObject.setSubject(user.getUsername());
                jwtObject.setRoles(user.getRoles()); // aqui pode ser lista de roles

                // Gerando token
                String token = JWTCreator.create(securityConfig.getPrefix(), securityConfig.getKey(), jwtObject);

                // Criando sessão de retorno
                Sessao sessao = new Sessao();
                sessao.setLogin(user.getUsername());
                sessao.setToken(token);

                return ResponseEntity.ok(sessao);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            // Loga o erro e retorna mensagem amigável
            e.printStackTrace();
            Sessao erroSessao = new Sessao();
            erroSessao.setLogin("erro");
            erroSessao.setToken("Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroSessao);
        }
    }
}
