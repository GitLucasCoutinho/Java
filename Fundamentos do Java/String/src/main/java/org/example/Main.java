package org.example;

public class Main {
    public static void main(String[] args) {
        String texto = "  Olá Mundo  ";
        String nome = "Lucas";
        String outroNome = "lucas";

        // -------------------------------
        // MÉTODOS DE COMPARAÇÃO
        // -------------------------------
        System.out.println("equals: " + nome.equals("Lucas")); // true
        System.out.println("equalsIgnoreCase: " + nome.equalsIgnoreCase(outroNome)); // true
        System.out.println("compareTo: " + nome.compareTo("Maria")); // comparação lexicográfica
        System.out.println("compareToIgnoreCase: " + nome.compareToIgnoreCase(outroNome)); // 0 (iguais ignorando maiúsculas/minúsculas)
        System.out.println("contentEquals: " + nome.contentEquals("Lucas")); // true
        System.out.println("matches: " + nome.matches("[A-Z][a-z]+")); // true (regex: começa com maiúscula seguido de minúsculas)

        // -------------------------------
        // MÉTODOS DE VERIFICAÇÃO
        // -------------------------------
        System.out.println("isEmpty: " + "".isEmpty()); // true
        System.out.println("isBlank: " + "   ".isBlank()); // true (Java 11+)
        System.out.println("contains: " + texto.contains("Mundo")); // true
        System.out.println("startsWith: " + texto.trim().startsWith("Olá")); // true
        System.out.println("endsWith: " + texto.trim().endsWith("Mundo")); // true

        // -------------------------------
        // MÉTODOS DE TRANSFORMAÇÃO
        // -------------------------------
        System.out.println("toLowerCase: " + nome.toLowerCase()); // "lucas"
        System.out.println("toUpperCase: " + nome.toUpperCase()); // "LUCAS"
        System.out.println("trim: '" + texto.trim() + "'"); // remove espaços das extremidades
        System.out.println("strip: '" + texto.strip() + "'"); // similar ao trim (Java 11+)
        System.out.println("replace: " + texto.replace('o', '0')); // troca caracteres
        System.out.println("replaceAll: " + texto.replaceAll("\\s+", "-")); // regex: troca espaços por "-"
        System.out.println("replaceFirst: " + texto.replaceFirst("o", "O")); // troca primeira ocorrência
        System.out.println("concat: " + nome.concat(" Silva")); // concatenação
        System.out.println("repeat: " + nome.repeat(3)); // repete string (Java 11+)

        // -------------------------------
        // MÉTODOS DE EXTRAÇÃO
        // -------------------------------
        System.out.println("charAt: " + nome.charAt(0)); // 'L'
        System.out.println("substring: " + nome.substring(1)); // "ucas"
        System.out.println("substring (intervalo): " + nome.substring(0, 2)); // "Lu"
        String[] partes = texto.trim().split(" "); // divide por espaço
        System.out.println("split: " + partes[0] + " | " + partes[1]); // "Olá | Mundo"
        System.out.println("subSequence: " + nome.subSequence(0, 3)); // "Luc"

        // -------------------------------
        // MÉTODOS DE BUSCA
        // -------------------------------
        System.out.println("indexOf: " + texto.indexOf("Mundo")); // posição inicial da palavra
        System.out.println("lastIndexOf: " + texto.lastIndexOf("o")); // última ocorrência de 'o'

        // -------------------------------
        // MÉTODOS DE CONVERSÃO
        // -------------------------------
        char[] chars = nome.toCharArray();
        System.out.println("toCharArray: " + chars[0] + ", " + chars[1]); // 'L', 'u'
        byte[] bytes = nome.getBytes();
        System.out.println("getBytes: " + bytes[0]); // valor ASCII do 'L'
        System.out.println("intern: " + nome.intern()); // garante referência única no pool de strings
        System.out.println("valueOf: " + String.valueOf(123)); // "123"
        System.out.println("join: " + String.join("-", "Java", "Spring", "Hibernate")); // "Java-Spring-Hibernate"

        // -------------------------------
        // MÉTODOS DE INFORMAÇÃO
        // -------------------------------
        System.out.println("length: " + nome.length()); // 5
        System.out.println("codePointAt: " + nome.codePointAt(0)); // código Unicode do 'L'
        System.out.println("codePointBefore: " + nome.codePointBefore(1)); // código Unicode antes da posição 1
        System.out.println("codePointCount: " + nome.codePointCount(0, nome.length())); // número de pontos de código
        System.out.println("offsetByCodePoints: " + nome.offsetByCodePoints(0, 2)); // índice deslocado por 2 pontos

        //String metodos que suportam REGEX


        String texto2 = "Java,Spring;Hibernate|JPA";
        String frase = "O rato roeu a roupa do rei de Roma";

        // -------------------------------
        // SPLIT COM REGEX
        // -------------------------------
        // Divide usando vírgula, ponto e vírgula ou barra vertical
        String[] partes2 = texto2.split("[,;|]");
        for (String p : partes2) {
            System.out.println("split regex: " + p);
        }
        // Saída: Java / Spring / Hibernate / JPA

        // -------------------------------
        // REPLACEALL COM REGEX
        // -------------------------------
        // Substitui todas as vogais por '*'
        System.out.println("replaceAll regex: " + frase.replaceAll("[aeiouAEIOU]", "*"));
        // Saída: O r*t* r**u * r**p* d* r** d* R*m*

        // -------------------------------
        // REPLACEFIRST COM REGEX
        // -------------------------------
        // Substitui apenas a primeira palavra que começa com 'r'
        System.out.println("replaceFirst regex: " + frase.replaceFirst("\\br\\w+", "XXXX"));
        // Saída: O XXXX roeu a roupa do rei de Roma

        // -------------------------------
        // MATCHES COM REGEX
        // -------------------------------
        // Verifica se a string é composta apenas por letras
        System.out.println("matches regex: " + "Spring".matches("[A-Za-z]+")); // true
        System.out.println("matches regex: " + "Spring123".matches("[A-Za-z]+")); // false

        // -------------------------------
        // SPLIT COM LIMIT E REGEX
        // -------------------------------
        String[] partesLimitadas = frase.split("\\s+", 3); // divide por espaços, mas limita a 3 partes
        for (String p : partesLimitadas) {
            System.out.println("split regex com limit: " + p);
        }
        // Saída: O / rato / roeu a roupa do rei de Roma


    }
}