package com.banco.model;

// Classe que representa um cliente do banco
public class Cliente {
    private String nome;
    private String cpf;

    // Construtor: inicializa nome e CPF
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    // Retorna o nome do cliente
    public String getNome() { return nome; }

    // Retorna o CPF do cliente
    public String getCpf() { return cpf; }

    // Representação textual do cliente
    @Override
    public String toString() {
        return "Cliente: " + nome + " (CPF: " + cpf + ")";
    }
}