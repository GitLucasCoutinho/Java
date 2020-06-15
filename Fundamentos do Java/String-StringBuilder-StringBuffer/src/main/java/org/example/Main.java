package org.example;

public class Main {
    public static void main(String[] args) {

        // -------------------------------
        // Exemplo com String
        // -------------------------------
        // String é IMUTÁVEL.
        // Cada vez que você concatena, o Java cria um NOVO objeto em memória.
        // É simples e seguro, mas pode ser lento em muitas concatenações.
        String texto = "Olá";
        texto = texto + " Lucas";
        texto = texto + "!";
        System.out.println("Usando String: " + texto);

        // -------------------------------
        // Exemplo com StringBuilder
        // -------------------------------
        // StringBuilder é MUTÁVEL.
        // Nao segura para multiplas threads.
        // Foi feito para manipulação eficiente de texto em operações repetitivas.
        // NÃO é thread-safe (não deve ser usado em ambientes concorrentes).
        StringBuilder sb = new StringBuilder("Olá");
        sb.append(" Lucas");
        sb.append("!");
        System.out.println("Usando StringBuilder: " + sb.toString());

        // -------------------------------
        // Exemplo com StringBuffer
        // -------------------------------
        // StringBuffer também é MUTÁVEL, parecido com StringBuilder.
        // A diferença é que StringBuffer é thread-safe (sincronizado).
        // Isso significa que pode ser usado em ambientes com múltiplas threads.
        StringBuffer sbf = new StringBuffer("Olá");
        sbf.append(" Lucas");
        sbf.append("!");
        System.out.println("Usando StringBuffer: " + sbf.toString());

// -------------------------------
// Teste de desempenho
// -------------------------------
// Concatenando 1 milhão de vezes com cada tipo para comparar.
        int repeticoes = 250_000; // aumentamos a carga

        long inicioString = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < repeticoes; i++) {
            s = s + i; // cria novo objeto a cada vez
        }
        long fimString = System.currentTimeMillis();
        System.out.println("Tempo com String: " + (fimString - inicioString) + " ms");

        long inicioSB = System.currentTimeMillis();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < repeticoes; i++) {
            sb2.append(i); // reaproveita o mesmo objeto
        }
        long fimSB = System.currentTimeMillis();
        System.out.println("Tempo com StringBuilder: " + (fimSB - inicioSB) + " ms");

        long inicioSBF = System.currentTimeMillis();
        StringBuffer sbf2 = new StringBuffer();
        for (int i = 0; i < repeticoes; i++) {
            sbf2.append(i); // reaproveita o mesmo objeto, mas com sincronização
        }
        long fimSBF = System.currentTimeMillis();
        System.out.println("Tempo com StringBuffer: " + (fimSBF - inicioSBF) + " ms");
    }
}