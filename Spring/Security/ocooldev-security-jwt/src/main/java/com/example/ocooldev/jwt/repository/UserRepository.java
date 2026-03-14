package com.example.ocooldev.jwt.repository;

import org.springframework.data.jpa.repository.Query;

public interface UserRepository
extends org.springframework.data.jpa.repository.JpaRepository<com.example.ocooldev.jwt.model.User, Integer> {
    //A query personalizada para buscar um usuário pelo nome de usuário, incluindo os papéis associados
    //Utiliza JOIN FETCH para evitar o problema de LazyInitializationException ao acessar os papéis do usuário fora do contexto da transação
    //:username é um parâmetro que será substituído pelo valor do nome de usuário fornecido na chamada do método findByUsername
    // O resultado da consulta é um objeto User que contém as informações do usuário e seus papéis (roles) associados
    // A anotação @Query é usada para definir uma consulta personalizada em JPQL (Java Persistence Query Language) para buscar um usuário pelo nome de usuário, incluindo os papéis associados.
    // o e representa a entidade User na consulta, e JOIN FETCH e.roles é usado para carregar os papéis do usuário em uma única consulta, evitando problemas de LazyInitializationException ao acessar os papéis fora do contexto da transação.
    // um exemplo dessa consulta em sql seria:
    // SELECT u.*, r.role_id FROM tab_user u JOIN tab_user_roles r ON u.id_user = r.user_id WHERE u.username = :username
    @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.username = :username")
    
    // Método para encontrar um usuário pelo nome de usuário
    com.example.ocooldev.jwt.model.User findByUsername(String username);
}
