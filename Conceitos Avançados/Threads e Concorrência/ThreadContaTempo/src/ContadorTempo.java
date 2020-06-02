// Classe que representa uma Thread que conta o tempo
// Aqui estamos estendendo a classe Thread para criar nosso próprio comportamento
public class ContadorTempo extends Thread {

    // O método run() é o que será executado quando a thread for iniciada
    @Override
    public void run() {
        int segundos = 0; // variável para armazenar o tempo em segundos
        try {
            // Loop infinito: a thread vai rodar até ser interrompida
            while (true) {
                // Exibe o tempo decorrido no console
                System.out.println("Tempo decorrido: " + segundos + " segundos");

                // Incrementa o contador de segundos
                segundos++;

                // Faz a thread "dormir" por 1000 milissegundos (1 segundo)
                // Isso garante que o contador avance de forma controlada
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // Caso a thread seja interrompida, cai aqui
            System.out.println("Thread interrompida!");
        }
    }
}
