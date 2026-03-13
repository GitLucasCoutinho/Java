package com.example.ocooldev_security.init;


import com.example.ocooldev_security.model.User;
import com.example.ocooldev_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
// Essa classe roda automaticamente quando a aplicação inicia
public class StartApplication implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        // --- Criação do usuário ADMIN ---
        User user = repository.findByUsername("admin");
        if (user == null) {
            user = new User();
            user.setName("ADMIN");              // Nome do usuário
            user.setUsername("admin");          // Login
            user.setPassword("master123");      // Senha (texto puro, apenas para testes)
            user.getRoles().add("MANAGERS");    // Papel (role) do usuário
            repository.save(user);              // Salva no banco
        }

        // --- Criação do usuário USER ---
        user = repository.findByUsername("user");
        if (user == null) {
            user = new User();
            user.setName("USER");               // Nome do usuário
            user.setUsername("user");           // Login
            user.setPassword("user123");        // Senha (texto puro, apenas para testes)
            user.getRoles().add("USERS");       // Papel (role) do usuário
            repository.save(user);              // Salva no banco
        }
    }
}
