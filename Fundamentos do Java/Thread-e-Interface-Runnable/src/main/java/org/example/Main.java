package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    // Exemplo 1: Lista sincronizada
    // Usamos Collections.synchronizedList para proteger o acesso à lista.
    // Isso garante que múltiplas threads possam adicionar elementos sem causar problemas.
    private static List<Integer> syncList = Collections.synchronizedList(new ArrayList<>());

    // Exemplo 2: Fila thread-safe
    // ConcurrentLinkedQueue já é segura para uso em ambientes multithread.
    // Não precisamos sincronizar manualmente.
    private static Queue<Integer> queue = new ConcurrentLinkedQueue<>();

    // Exemplo 3: Contador atômico
    // AtomicInteger permite operações atômicas (seguras em concorrência) sobre inteiros.
    private static AtomicInteger atomicCounter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        // -------------------------------
        // Exemplo 1: Lista sincronizada
        // -------------------------------
        // Thread que adiciona números positivos de 0 até 99.999
        Runnable listIncTask = () -> {
            for (int i = 0; i < 100_000; i++) {
                syncList.add(i);
            }
        };

        // Thread que adiciona números negativos de 0 até -99.999
        Runnable listDecTask = () -> {
            for (int i = 0; i > -100_000; i--) {
                syncList.add(i);
            }
        };

        // Criamos duas threads para adicionar números
        Thread listT1 = new Thread(listIncTask);
        Thread listT2 = new Thread(listDecTask);

        // Iniciamos as threads
        listT1.start();
        listT2.start();

        // join() faz a thread principal esperar até que listT1 e listT2 terminem
        listT1.join();
        listT2.join();

        // Iterar sobre uma lista sincronizada exige "synchronized" para evitar problemas
        synchronized (syncList) {
            System.out.println("=== Lista sincronizada ===");
            System.out.println("Tamanho da lista: " + syncList.size());
            int count = 0;
            for (Integer num : syncList) {
                System.out.println(num);
                if (++count >= 50) break; // imprime só os primeiros 50
            }
        }

        // -------------------------------
        // Exemplo 2: Fila thread-safe
        // -------------------------------
        // Thread que adiciona números positivos
        Runnable queueIncTask = () -> {
            for (int i = 0; i < 100_000; i++) {
                queue.add(i);
            }
        };

        // Thread que adiciona números negativos
        Runnable queueDecTask = () -> {
            for (int i = 0; i > -100_000; i--) {
                queue.add(i);
            }
        };

        Thread queueT1 = new Thread(queueIncTask);
        Thread queueT2 = new Thread(queueDecTask);

        queueT1.start();
        queueT2.start();

        queueT1.join();
        queueT2.join();

        // Como a fila já é thread-safe, não precisamos sincronizar para iterar
        System.out.println("\n=== Fila thread-safe ===");
        System.out.println("Tamanho da fila: " + queue.size());
        int countQ = 0;
        for (Integer num : queue) {
            System.out.println(num);
            if (++countQ >= 50) break;
        }

        // -------------------------------
        // Exemplo 3: Contador atômico
        // -------------------------------
        // Thread que incrementa o contador até +100.000
        Runnable atomicIncTask = () -> {
            for (int i = 0; i < 100_000; i++) {
                atomicCounter.incrementAndGet(); // incremento atômico
            }
        };

        // Thread que decrementa o contador até -100.000
        Runnable atomicDecTask = () -> {
            for (int i = 0; i < 100_000; i++) {
                atomicCounter.decrementAndGet(); // decremento atômico
            }
        };

        Thread atomicT1 = new Thread(atomicIncTask);
        Thread atomicT2 = new Thread(atomicDecTask);

        atomicT1.start();
        atomicT2.start();

        atomicT1.join();
        atomicT2.join();

        // O resultado esperado é 0, pois incrementos e decrementos se anulam
        System.out.println("\n=== Contador atômico ===");
        System.out.println("Valor final do contador: " + atomicCounter.get());
    }
}