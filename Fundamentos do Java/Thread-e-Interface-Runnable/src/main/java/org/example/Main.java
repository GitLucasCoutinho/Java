package org.example;
// Define o pacote onde a classe está. Pacotes ajudam a organizar o código em módulos.
// Pacotes evitam conflitos de nomes e facilitam a organização do projeto.

import java.util.ArrayList;
import java.util.List;
// Importa classes da biblioteca padrão do Java.
// ArrayList: lista dinâmica que cresce conforme adicionamos elementos.
// List: interface que define o comportamento genérico de listas (contrato).

public class Main {
    // Cria uma lista estática que será compartilhada por todas as threads.
    // "static" significa que pertence à classe, não a um objeto específico.
    private static List<Integer> numbers = new ArrayList<>();

        private synchronized static void inc(int number) {
                numbers.add(number); // Adiciona o número à lista compartilhada.
        }
        private synchronized static void show() {
                System.out.println(numbers); // Adiciona o número à lista compartilhada.

        }

    public static void main(String[] args) {

        // Método principal: ponto de entrada do programa.

        // Runnable é uma interface funcional (tem apenas um método: run()).
        // Usamos uma expressão lambda para definir o que cada thread vai executar.

        Runnable inc = () -> {
            // Essa tarefa adiciona números de 0 até 99.999 na lista.
            for (int i = 0; i < 100_000; i++) {
                inc(i); // Adiciona cada número na lista.
            }
        };

        Runnable dec = () -> {
            // Essa tarefa adiciona números negativos até -99.999.
            for (int i = 0; i > -100_000; i--) {
                inc(i); // Adiciona cada número negativo na lista.
            }
        };

        Runnable show = () -> {
            // Essa tarefa tenta imprimir os primeiros 100.000 elementos da lista.
            // Atenção: se a lista não tiver esse tamanho ainda, pode ocorrer erro (IndexOutOfBoundsException).
            for (int i = 0; i < 100_000; i++) {
                show(); // Imprime a lista atual.
            }
        };

        // Aqui criamos e iniciamos três threads diferentes.
        // Cada uma executa uma das tarefas definidas acima.
        new Thread(inc).start();   // Thread que adiciona números positivos.
        new Thread(dec).start();   // Thread que adiciona números negativos.
        new Thread(show).start();  // Thread que imprime os números.
    }
}