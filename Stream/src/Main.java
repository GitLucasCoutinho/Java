import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        /*
         🔎 O que é um Stream?
         - Um Stream em Java é como uma "linha de produção" de dados.
         - Ele pega elementos de uma coleção (List, Set, Map, Array, etc.) e permite aplicar operações
           de forma declarativa (sem precisar escrever loops manuais).
         - Foi introduzido no Java 8 junto com lambdas e Optional.

         🛠 Características principais:
         - Trabalha com coleções (listas, conjuntos, mapas, arrays, etc.).
         - Permite aplicar operações como:
             • map → transforma cada elemento (ex: aplica desconto em todos os preços).
             • filter → filtra elementos que atendem a uma condição (ex: só preços acima de 250).
             • forEach → percorre os elementos e executa uma ação (ex: imprimir na tela).
             • collect → junta os resultados em uma nova coleção (ex: nova lista com os preços alterados).
             • reduce → combina todos os elementos em um único resultado (ex: soma de todos os preços).
         - É lazy (preguiçoso): só executa quando você pede o resultado final (ex: collect, sum).
         - Facilita programação funcional em Java, deixando o código mais limpo e expressivo.
        */

        // Lista de preços de produtos (fonte de dados para o Stream)
        List<Double> precos = Arrays.asList(100.0, 200.0, 300.0, 400.0);

        // ✅ Exemplo 1: Usando Stream para aplicar desconto de 10% em todos os preços
        // - .stream() cria o fluxo de dados a partir da lista
        // - .map(p -> p * 0.9) transforma cada preço aplicando 10% de desconto
        // - .collect(Collectors.toList()) junta os resultados em uma nova lista
        List<Double> precosComDesconto = precos.stream()
                .map(p -> p * 0.9)
                .collect(Collectors.toList());

        System.out.println("Preços com desconto: " + precosComDesconto);

        // ✅ Exemplo 2: Usando Stream para filtrar apenas preços acima de 250
        // - .filter(p -> p > 250) mantém apenas os elementos que satisfazem a condição
        // - .collect(Collectors.toList()) cria uma nova lista só com os preços filtrados
        List<Double> precosAltos = precos.stream()
                .filter(p -> p > 250)
                .collect(Collectors.toList());

        System.out.println("Preços acima de 250: " + precosAltos);

        // ✅ Exemplo 3: Usando Stream para somar todos os preços
        // - .mapToDouble(Double::doubleValue) transforma cada elemento em primitivo double
        // - .sum() soma todos os valores do fluxo
        double soma = precos.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        System.out.println("Soma dos preços: " + soma);

        // ✅ Exemplo 4: Usando forEach para percorrer e imprimir cada preço
        // - .forEach executa uma ação para cada elemento do fluxo
        System.out.print("Lista original: ");
        precos.stream().forEach(p -> System.out.print(p + " "));
    }
}