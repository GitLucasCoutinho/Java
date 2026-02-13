package org.example.domain;

import java.util.Objects;

public class ClientDomain extends GenericDomain<Integer>{
    private int id;
    private String nome;
    private int idade;


    public ClientDomain(Integer id, String nome, int idade) {
        super(id);
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClientDomain that = (ClientDomain) o;
        return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(idade, that.idade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, nome, idade);
    }

    @Override
    public String toString() {
        return "ClientDomain{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade='" + idade + '\'' +
                '}';
    }
}
