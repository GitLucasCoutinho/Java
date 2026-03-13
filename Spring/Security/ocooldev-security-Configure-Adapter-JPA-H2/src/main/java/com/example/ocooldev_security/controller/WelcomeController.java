package com.example.ocooldev_security.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Indica que esta classe é um controlador REST, ou seja, ela vai expor endpoints HTTP.
public class WelcomeController {

    @GetMapping
    // Define um endpoint GET na raiz ("/").
    public String welcome() {
        return "Welcome to My Spring Boot Web API";
        // Quando alguém acessar "/", vai receber essa mensagem de boas-vindas.
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('USERS','MANAGERS')")
    // Só permite acesso se o usuário tiver o papel "USERS" OU "MANAGERS".
    public String users() {
        return "Authorized user";
        // Se autorizado, retorna essa mensagem.
    }

    @GetMapping("/managers")
    // Define um endpoint GET em "/managers".
    @PreAuthorize("hasRole('MANAGERS')")
    // Só permite acesso se o usuário tiver o papel "MANAGERS".
    public String managers() {
        return "Authorized manager";
        // Se autorizado, retorna essa mensagem.
    }
}
