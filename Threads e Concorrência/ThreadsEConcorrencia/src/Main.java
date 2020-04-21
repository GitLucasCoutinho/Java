/*
 * Contexto do programa:
 * Este programa demonstra duas formas de criar uma thread simples em Java.
 *
 * - Forma 1: Usando expressão lambda para implementar Runnable.
 * - Forma 2: Usando uma classe anônima que implementa Runnable (sem lambda).
 *
 * Em ambas as formas:
 * - A thread executa um laço que imprime mensagens e pausa por 1 segundo.
 * - Enquanto isso, o método main continua rodando em paralelo, imprimindo suas próprias mensagens.
 * - O objetivo é mostrar como duas partes do código podem ser executadas simultaneamente,
 *   evidenciando o conceito de concorrência em Java.
 *
 * Em resumo: o programa serve como exemplo introdutório de multithreading,
 * mostrando a execução paralela entre a thread criada e a thread principal (main).
 */

public class Main {

    // Método usando expressão lambda
    public static void iniciarThreadComLambda() {
        Thread minhaThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Executando na thread (lambda): " + i);
                try {
                    Thread.sleep(1000); // pausa de 1 segundo
                } catch (InterruptedException e) {
                    System.out.println("Thread (lambda) interrompida!");
                }
            }
        });

        minhaThread.start();
    }

    // Método sem usar expressão lambda (classe anônima)
    public static void iniciarThreadSemLambda() {
        Thread minhaThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Executando na thread (sem lambda): " + i);
                    try {
                        Thread.sleep(1000); // pausa de 1 segundo
                    } catch (InterruptedException e) {
                        System.out.println("Thread (sem lambda) interrompida!");
                    }
                }
            }
        });

        minhaThread.start();
    }

    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        // Chama a versão com lambda
        iniciarThreadComLambda();

        // Chama a versão sem lambda
        iniciarThreadSemLambda();

        // Enquanto isso, o main continua rodando
        for (int i = 1; i <= 5; i++) {
            System.out.println("Executando no main: " + i);
            try {
                Thread.sleep(800); // pausa de 0.8 segundos
            } catch (InterruptedException e) {
                System.out.println("Main interrompida!");
            }
        }

        // Exemplo adicional: mantendo a lógica original
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}