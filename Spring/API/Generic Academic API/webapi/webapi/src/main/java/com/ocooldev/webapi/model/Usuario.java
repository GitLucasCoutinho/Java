package com.ocooldev.webapi.model;

public class Usuario {

    private Integer id ;
    private String nome;
    private String login;
    private String senha;

    private static int contador = 0;


    public Usuario() {
    }

    public Usuario( String nome, String login, String senha) {
        contador++;
        this.id = contador;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }


    public Integer getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "Usuario{" + "id=" + id
                + ", nome=" + nome
                + ", login=" + login
                + ", senha=" + senha + '}';
    }
}
