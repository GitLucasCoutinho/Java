package com.example.ocooldev_security.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

// Indica que esta classe é uma entidade JPA (vai ser mapeada para uma tabela no banco)
@Entity
// Define o nome da tabela no banco de dados
@Table(name = "tab_user")
public class User {

    // Chave primária da tabela
    @Id
    // Geração automática do ID (auto incremento)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Define o nome da coluna no banco
    @Column(name = "user_id")
    private Integer id;

    // Coluna "name" com limite de 50 caracteres e obrigatória (não pode ser nula)
    @Column(length = 50, nullable = false)
    private String name;

    // Coluna "username" com limite de 20 caracteres e obrigatória
    @Column(length = 20, nullable = false)
    private String username;

    // Coluna "password" com limite de 100 caracteres e obrigatória
    @Column(length = 100, nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
// Define que a lista de roles (perfis) será carregada junto com o usuário
    @CollectionTable(
            name = "tab_user_roles", // nome da tabela auxiliar que guarda os papéis
            joinColumns = @JoinColumn(name = "user_id") // chave estrangeira que liga ao usuário
    )
    @Column(name = "role_id")
// Nome da coluna que vai armazenar cada role
    private List<String> roles = new ArrayList<>();

    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    // Método toString para imprimir os dados do objeto de forma legível
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
