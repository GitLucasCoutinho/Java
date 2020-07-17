package org.example.exemploAbstrato;

public class GatoAbstrato extends AnimalAbstrato {
    @Override
    public void emitirSom() {
        System.out.println("GatoAbstrato: Miau!");
    }
}
