package com.ocooldev.pix.ms_simulador_pix.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Configuração de Segurança para Simulador PIX
 * Para desenvolvimento local, permite acesso a todos os endpoints
 * Em produção, implementar OAuth2 e JWT
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Permitir acesso a documentação e health checks
                .requestMatchers(
                    "/swagger-ui.html",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/actuator/**",
                    "/h2-console/**",
                    "/health",
                    "/pix/**"  // Todos os endpoints PIX permitidos em dev
                ).permitAll()
                .anyRequest().permitAll()  // Permitir tudo para desenvolvimento local
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .headers(headers -> headers.frameOptions(frame -> frame.disable())); // Para H2 console

        return http.build();
    }
}
