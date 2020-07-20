package org.example;

import java.util.function.BiFunction;

/**
 * Este enum mostra como podemos representar operações matemáticas
 * usando um estilo mais moderno e funcional em Java.
 *
 * Cada "palavra" (SUM, SUBTRACTION, etc.) é uma constante do enum
 * que guarda dentro dela uma função (lambda) para fazer o cálculo.
 */
public enum OperationEnum {

    // Cada constante representa uma operação matemática:
    // SUM usa "Integer::sum" (atalho para somar dois números).
    SUM(Integer::sum),

    // SUBTRACTION usa uma expressão lambda para subtrair dois números.
    SUBTRACTION((Integer v1, Integer v2) -> v1 - v2),

    // MULTIPLY usa uma expressão lambda para multiplicar dois números.
    MULTIPLY((Integer v1, Integer v2) -> v1 * v2),

    // DIVISION usa uma expressão lambda para dividir dois números.
    // Aqui colocamos uma verificação para não dividir por zero.
    DIVISION((Integer v1, Integer v2) -> {
        if (v2 == 0) throw new ArithmeticException("Division by zero not allowed");
        return v1 / v2;
    });

    /**
     * Este campo guarda a "função" que faz o cálculo.
     * BiFunction significa: recebe 2 valores (Integer, Integer)
     * e devolve 1 resultado (Integer).
     */
    private final BiFunction<Integer, Integer, Integer> calculate;

    /**
     * Construtor do enum.
     * Quando criamos cada constante (SUM, SUBTRACTION, etc.),
     * passamos a função que será guardada aqui dentro.
     */
    OperationEnum(BiFunction<Integer, Integer, Integer> calculate) {
        this.calculate = calculate;
    }

    /**
     * Método para usar a operação.
     * Basta chamar apply(a, b) e ele executa a função guardada.
     */
    public int apply(int a, int b) {
        return calculate.apply(a, b);
    }
}