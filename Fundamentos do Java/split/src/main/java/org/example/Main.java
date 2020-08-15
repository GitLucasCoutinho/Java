package org.example;

import java.util.Scanner;

public class Main {

    // Método que lê os números digitados pelo usuário e retorna um array de inteiros
    private static int[] lerNumeros(Scanner scanner) {
        String entrada = scanner.nextLine(); // lê a linha completa
        String[] numerosString = entrada.split(","); // separa por vírgula
        int[] numeros = new int[numerosString.length];

        for (int i = 0; i < numerosString.length; i++) {
            numeros[i] = Integer.parseInt(numerosString[i].trim());
            // trim remove espaços em branco antes/depois do número
        }
        return numeros;
    }

    // Método para somar todos os números do array
    private static int somar(int[] numeros) {
        int resultado = 0;
        for (int num : numeros) {
            resultado += num;
        }
        return resultado;
    }

    // Método para subtrair todos os números do array
    private static int subtrair(int[] numeros) {
        int resultado = numeros[0]; // começa pelo primeiro número
        for (int i = 1; i < numeros.length; i++) {
            resultado -= numeros[i];
        }
        return resultado;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite \"soma\" para somar e \"subtrair\" para subtrair:");
        String operacao = scanner.nextLine();

        if (operacao.equalsIgnoreCase("soma")) {
            System.out.print("Digite os números que serão somados separados por vírgula: ");
            int[] numeros = lerNumeros(scanner);
            int resultado = somar(numeros);
            System.out.println("Resultado da soma: " + resultado);

        } else if (operacao.equalsIgnoreCase("subtrair")) {
            System.out.print("Digite os números que serão subtraídos separados por vírgula: ");
            int[] numeros = lerNumeros(scanner);
            int resultado = subtrair(numeros);
            System.out.println("Resultado da subtração: " + resultado);

        } else {
            System.out.println("Operação inválida. Digite \"soma\" ou \"subtrair\".");
        }

        scanner.close();
    }
}