package com.example.ocooldev_security.config;
import com.example.ocooldev_security.model.User;
import com.example.ocooldev_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

// Indica que esta classe é um serviço gerenciado pelo Spring
@Service
// Implementa a interface UserDetailsService, usada pelo Spring Security
public class SecurityDatabaseService implements UserDetailsService {

    // Injeta automaticamente o repositório de usuários
    @Autowired
    private UserRepository userRepository;

    // Método chamado pelo Spring Security quando alguém tenta logar
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) {
        // Busca o usuário no banco pelo username
        User userEntity = userRepository.findByUsername(username);

        // Se não encontrar, lança exceção
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        // Cria a lista de permissões (roles) do usuário
        Set<GrantedAuthority> authorities = new HashSet<>();
        userEntity.getRoles().forEach(role -> {
            // Cada role do banco vira uma autoridade do Spring Security
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        });

        // Cria o objeto UserDetails que o Spring Security usa internamente
        UserDetails user = org.springframework.security.core.userdetails.User
                .withUsername(userEntity.getUsername()) // define o login
                .password(userEntity.getPassword())     // define a senha
                .authorities(authorities)               // define as permissões
                .build();

        // Retorna o usuário pronto para autenticação
        return user;
    }
}
