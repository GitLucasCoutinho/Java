package com.example.ocooldev_security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
// Esses imports trazem as classes necessárias do Spring Security para configurar usuários e regras de segurança.

@Configuration
// Indica que esta classe é uma classe de configuração do Spring (substitui arquivos XML antigos).

@EnableWebSecurity
// Ativa o módulo de segurança web do Spring Security.

@EnableMethodSecurity
// Permite usar anotações como @PreAuthorize e @PostAuthorize em métodos para controlar acesso.

public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        // Cria um serviço de usuários em memória (sem banco de dados).

        var user = User.withUsername("user")
                .password("{noop}user123") // {noop} significa que a senha não está criptografada.
                .roles("USERS", "users")            // Define o papel (role) do usuário.
                .build();

        var admin = User.withUsername("admin")
                .password("{noop}master123")
                .roles("MANAGERS", "managers")       // Define o papel do administrador.
                .build();

        return new InMemoryUserDetailsManager(user, admin);
        // Retorna um gerenciador de usuários que guarda os dois perfis criados.
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        // Define as regras de segurança para requisições HTTP.

        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Qualquer requisição precisa estar autenticada.
                )
                .formLogin(Customizer.withDefaults())   // Habilita login via formulário padrão do Spring Security.
                .httpBasic(Customizer.withDefaults());  // Também habilita autenticação básica (via cabeçalho HTTP).

        return http.build();
        // Constrói e retorna a configuração de segurança.
    }
}
