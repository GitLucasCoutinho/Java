/*
 * Contexto do programa:
 * Este programa demonstra como criar e executar uma thread em Java utilizando a interface Runnable.
 *
 * - A classe `MinhaTarefa` (não mostrada aqui, mas implementa Runnable) define o que a thread irá executar.
 * - No método main, criamos uma instância de Thread passando a tarefa.
 * - Chamamos `start()` para iniciar a execução da thread em paralelo.
 * - Enquanto isso, o método main continua rodando seu próprio laço, mostrando a concorrência.
 *
 * Em resumo: este exemplo mostra como duas partes do código podem ser executadas simultaneamente,
 * evidenciando o conceito de multithreading em Java.
 */

public class Main {
    public static void main(String[] args) {

        // Cria uma nova thread passando a instância de MinhaTarefa
        // MinhaTarefa é uma classe que implementa Runnable e define o método run()
        Thread minhaThread = new Thread(new MinhaTarefa());

        // Inicia a execução da thread
        // Ao chamar start(), o método run() da classe MinhaTarefa será executado em paralelo
        minhaThread.start();

        // Enquanto isso, o método main continua rodando normalmente
        // Este laço imprime mensagens no console e pausa por 0.8 segundos entre cada execução
        for (int i = 1; i <= 5; i++) {
            System.out.println("Executando no main: " + i);
            try {
                Thread.sleep(800); // pausa de 0.8 segundos para simular processamento
            } catch (InterruptedException e) {
                System.out.println("Main interrompida!");
            }
        }
    }
}