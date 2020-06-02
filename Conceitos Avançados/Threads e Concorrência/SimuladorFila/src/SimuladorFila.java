import java.util.LinkedList;
import java.util.Queue;

// Classe que representa uma Thread que simula uma fila de atendimento
public class SimuladorFila extends Thread {

    private Queue<String> fila; // fila de clientes

    // Construtor para inicializar a fila
    public SimuladorFila(Queue<String> fila) {
        this.fila = fila;
    }

    // O método run() é o que será executado quando a thread for iniciada
    @Override
    public void run() {
        try {
            // Enquanto houver pessoas na fila
            while (!fila.isEmpty()) {
                // Remove o próximo da fila
                String cliente = fila.poll();
                System.out.println("Atendendo cliente: " + cliente);

                // Simula tempo de atendimento (2 segundos)
                Thread.sleep(2000);

                System.out.println("Cliente " + cliente + " atendido!");
            }

            System.out.println("Fila vazia. Todos foram atendidos!");
        } catch (InterruptedException e) {
            System.out.println("Atendimento interrompido!");
        }
    }

}
