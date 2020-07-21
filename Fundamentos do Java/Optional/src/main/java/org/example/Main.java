package org.example;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Criando um Optional com valor presente
        Optional<String> optionalComValor = Optional.of("Java");

        // Criando um Optional vazio
        Optional<String> optionalVazio = Optional.empty();

        // Usando orElse para fornecer valor padrão
        String resultado1 = optionalComValor.orElse("Default");
        String resultado2 = optionalVazio.orElse("Default");

        System.out.println("Resultado 1: " + resultado1); // Saída: Java
        System.out.println("Resultado 2: " + resultado2); // Saída: Default

        // Usando ifPresent para executar ação se valor existir
        optionalComValor.ifPresent(valor -> System.out.println("Valor presente: " + valor));

        // Usando orElseThrow para lançar exceção se vazio
        try {
            String resultado3 = optionalVazio.orElseThrow(() -> new IllegalArgumentException("Valor ausente!"));
            System.out.println("Resultado 3: " + resultado3);
        } catch (Exception e) {
            System.out.println("Exceção lançada: " + e.getMessage());
        }
    }
}
