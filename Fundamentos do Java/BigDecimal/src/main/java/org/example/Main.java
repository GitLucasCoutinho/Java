package org.example;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Main {
    public static void main(String[] args) {
        // Criando BigDecimal a partir de String (forma recomendada)
        BigDecimal valor1 = new BigDecimal("10.50");
        BigDecimal valor2 = new BigDecimal("4.25");

        // Soma
        BigDecimal soma = valor1.add(valor2);
        System.out.println("Soma: " + soma); // 14.75

        // Subtração
        BigDecimal subtracao = valor1.subtract(valor2);
        System.out.println("Subtração: " + subtracao); // 6.25

        // Multiplicação
        BigDecimal multiplicacao = valor1.multiply(valor2);
        System.out.println("Multiplicação: " + multiplicacao); // 44.625

        // Divisão com arredondamento
        BigDecimal divisao = valor1.divide(valor2, 2, RoundingMode.HALF_UP);
        System.out.println("Divisão: " + divisao); // 2.47

        // Comparação
        int comparacao = valor1.compareTo(valor2);
        if (comparacao > 0) {
            System.out.println("valor1 é maior que valor2");
        } else if (comparacao < 0) {
            System.out.println("valor1 é menor que valor2");
        } else {
            System.out.println("valor1 é igual a valor2");
        }

        // Ajustando escala (quantidade de casas decimais)
        BigDecimal valorComEscala = valor1.setScale(3, RoundingMode.HALF_UP);
        System.out.println("Valor com 3 casas decimais: " + valorComEscala); // 10.500

        // Valor absoluto
        BigDecimal negativo = new BigDecimal("-123.45");
        System.out.println("Valor absoluto: " + negativo.abs()); // 123.45

        // Máximo eMínimo
        BigDecimal maximo = valor1.max(valor2);
        BigDecimal minimo = valor1.min(valor2);
        System.out.println("Máximo: " + maximo); // 10.50
        System.out.println("Mínimo: " + minimo); // 4.25

    }
}
