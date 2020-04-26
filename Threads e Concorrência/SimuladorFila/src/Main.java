import java.util.LinkedList;
import java.util.Queue;
public class Main{
    public static void main(String[] args) {
        // Cria uma fila de clientes
        Queue<String> filaClientes = new LinkedList<>();
        filaClientes.add("Lucas");
        filaClientes.add("Maria");
        filaClientes.add("João");
        filaClientes.add("Ana");

        // Cria e inicia a thread que simula a fila
        SimuladorFila simulador = new SimuladorFila(filaClientes);
        simulador.start();

    }
}

/*
 O que você aprendeu aqui:
- Thread: unidade de execução paralela dentro de um programa.
- run(): método que define o que a thread vai fazer.
- start(): inicia a thread (não confundir com run()).
- sleep(): pausa a execução da thread por um tempo definido.
- Queue: estrutura de dados que segue a ordem FIFO (First In, First Out).
- poll(): remove e retorna o primeiro elemento da fila.
- Simulação de processo: podemos usar filas para representar atendimentos em ordem.
*/
