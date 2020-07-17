package org.example.exemploInterface;

public class PassaroInterface implements AnimalInterface {
    @Override
    public void emitirSom() {
        System.out.println("PassaroInterface: Piu Piu!");
    }
}

