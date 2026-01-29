package org.example;

// Um POJO simples para representar os dados do JSON
public class Pessoa {
    private String name;
    private int age;

    // Getters e Setters (necessários para Jackson e Gson)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    // toString para imprimir bonito
    @Override
    public String toString() {
        return "Pessoa{name='" + name + "', age=" + age + "}";
    }
}