package exemplos;

import java.util.ArrayList;
import java.util.List;

public class ExemplosBasicos {

    public static void mostrarCondicional(Pessoa pessoa) {
        System.out.println("\n--- Estruturas condicionais ---");
        if (pessoa.getIdade() >= 18) {
            System.out.println("Você é maior de idade.");
        } else {
            System.out.println("Você é menor de idade.");
        }
    }

    public static void mostrarRepeticao() {
        System.out.println("\n--- Estruturas de repetição ---");
        System.out.print("Contagem (for): ");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        int contador = 0;
        while (contador < 3) {
            System.out.println("Loop while: " + contador);
            contador++;
        }
    }

    public static void mostrarArrays() {
        System.out.println("\n--- Arrays ---");
        int[] numeros = {1, 2, 3, 4, 5};
        System.out.println("Primeiro número do array: " + numeros[0]);
    }

    public static void mostrarColecoes() {
        System.out.println("\n--- Coleções (List) ---");
        List<String> nomes = new ArrayList<>();
        nomes.add("Lucas");
        nomes.add("Maria");
        nomes.add("João");

        for (String nome : nomes) {
            System.out.println("- " + nome);
        }
    }

    public static void mostrarMetodos() {
        System.out.println("\n--- Métodos ---");
        int resultado = soma(10, 20);
        System.out.println("Resultado da soma: " + resultado);
    }

    private static int soma(int a, int b) {
        return a + b;
    }

    public static void mostrarOperadoresLogicos() {
        System.out.println("\n--- Operadores lógicos ---");
        System.out.printf("true && false = %s%n", true && false);
        System.out.printf("true || false = %s%n", true || false);
        System.out.printf("true | false = %s%n", true | false);
    }
}