package com.ocooldev.webapi.controller;

import com.ocooldev.webapi.Repository.UserRepository;
import com.ocooldev.webapi.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        System.out.println("Hello World");
        return "Hello World";
    }
/*
    @PostMapping
     public void save(Usuario usuario) {
        userRepository.save(usuario);
    }
  */
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/users")
     public List<Usuario> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/serch/{username}")
     public Usuario findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @PostMapping("/users")
        public void postUser(@RequestBody Usuario usuario) {
            userRepository.save(usuario);
        }
}
