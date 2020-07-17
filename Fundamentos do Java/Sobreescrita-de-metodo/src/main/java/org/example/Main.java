package org.example;

import org.example.exemploAbstrato.AnimalAbstrato;
import org.example.exemploAbstrato.GatoAbstrato;
import org.example.exemploConcreto.AnimalConcreto;
import org.example.exemploConcreto.CachorroConcreto;
import org.example.exemploInterface.AnimalInterface;
import org.example.exemploInterface.PassaroInterface;

// -------------------------------
// Programa principal
// -------------------------------
public class Main {
    public static void main(String[] args) {

            // Usando Classe concreta → sobrescreve um método já implementado.
            //   Uma classe filha herda de uma classe pai e redefine um método.
            //   Exemplo: Cachorro sobrescreve emitirSom() herdado de Animal.
        AnimalConcreto a1 = new AnimalConcreto();
        AnimalConcreto a2 = new CachorroConcreto();
        System.out.println("=== Classe Concreta ===");
        a1.emitirSom(); // som genérico
        a2.emitirSom(); // Au Au!

            // Usando classe abstrata → obriga a sobrescrever métodos abstratos.
            //   Uma classe abstrata define métodos abstratos (sem corpo).
            //   As classes filhas são obrigadas a sobrescrever esses métodos.
        AnimalAbstrato a3 = new GatoAbstrato();
        System.out.println("\n=== Classe Abstrata ===");
        a3.emitirSom(); // Miau!

            // Usando Interface → define contratos que devem ser implementados
            //   Interfaces só têm métodos abstratos (até o Java 7).
            //   A classe que implementa a interface deve sobrescrever todos os métodos.
        AnimalInterface a4 = new PassaroInterface();
        System.out.println("\n=== Interface ===");
        a4.emitirSom(); // Piu Piu!
    }
}