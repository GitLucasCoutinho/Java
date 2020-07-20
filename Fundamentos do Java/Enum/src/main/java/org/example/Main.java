package org.example;

public class Main {
    public static void main(String[] args) {

        // Buscar estado pela sigla (mantive seu exemplo, assumindo que você tem o enum EstadoBrasil)
        EstadoBrasil estado = EstadoBrasil.fromSigla("SP");

        // Exibir informações
        System.out.println("Estado escolhido: " + estado);
        System.out.println("Nome: " + estado.getNome());
        System.out.println("Sigla: " + estado.getSigla());

        // -------------------------------
        // Usando o estilo TRADICIONAL (em português)
        // -------------------------------
        double resultado = OperacaoTradicionalEnum.SOMA.executar(10, 5);
        System.out.println("Resultado da soma (tradicional): " + resultado);

        resultado = OperacaoTradicionalEnum.DIVISAO.executar(10, 2);
        System.out.println("Resultado da divisão (tradicional): " + resultado);

        // -------------------------------
        // Usando o estilo FUNCIONAL (em inglês)
        // -------------------------------
        int resultadoFuncional = OperationEnum.SUM.apply(10, 5);
        System.out.println("Resultado da soma (functional): " + resultadoFuncional);

        resultadoFuncional = OperationEnum.DIVISION.apply(10, 2);
        System.out.println("Resultado da divisão (functional): " + resultadoFuncional);
    }
}
