package org.example.domain;

public class UserDomain extends GenericDomain<Integer>{

    private String name;
    private int idade;

    public UserDomain() {
    }

    public UserDomain(Integer id, String name, int idade) {
        super(id);
        this.name = name;
        this.idade = idade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmail() {
        return idade;
    }

    public void setEmail(int idade) {
        this.idade = idade;
    }




    @Override
    public String toString() {
        return "UserDomain{" +
                "ID=" + getId() +
                 ", " +
                "name='" + name + '\'' +
                ", idade=" + idade +
                '}';
    }
}
