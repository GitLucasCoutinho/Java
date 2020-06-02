/*
 * Contexto da classe:
 * Esta classe demonstra como implementar a interface Runnable em Java.
 *
 * - A interface Runnable exige a implementação do método run().
 * - O método run() contém o código que será executado pela thread.
 * - Neste exemplo, o run() imprime mensagens numeradas de 1 a 5.
 * - Entre cada mensagem, a thread pausa por 1 segundo usando Thread.sleep().
 * - Caso a thread seja interrompida durante a execução, uma mensagem de aviso é exibida.
 *
 * Em resumo: esta classe define a tarefa que será executada em paralelo
 * quando for passada para um objeto Thread e iniciada com start().
 */

class MinhaTarefa implements Runnable {

    @Override
    public void run() {
        // Laço que executa 5 vezes
        for (int i = 1; i <= 5; i++) {
            // Mensagem indicando que está rodando dentro da thread
            System.out.println("Executando na thread Runnable: " + i);

            try {
                // Pausa de 1 segundo entre cada execução
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Caso a thread seja interrompida, exibe mensagem
                System.out.println("Thread interrompida!");
            }
        }
    }
}