import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//Reduce funcionando com listas simples
            // Lista de preços de produtos
            List<Double> precos = Arrays.asList(100.0, 200.0, 300.0, 400.0);

            // ✅ Usando reduce para somar todos os preços
            // - O primeiro parâmetro (0.0) é o valor inicial da soma
            // - O segundo parâmetro (Double::sum) é a função que combina os elementos
            double soma = precos.stream()
                    .reduce(0.0, Double::sum);

            System.out.println("Soma dos preços: " + soma);

            // ✅ Usando reduce para calcular o produto de todos os preços
            // - Começa em 1.0 (neutro da multiplicação)
            // - Multiplica cada elemento da lista
            double produto = precos.stream()
                    .reduce(1.0, (a, b) -> a * b);

            System.out.println("Produto dos preços: " + produto);

            // ✅ Usando reduce para encontrar o maior preço
            double maiorPreco = precos.stream()
                    .reduce(Double.MIN_VALUE, (a, b) -> a > b ? a : b);

            System.out.println("Maior preço: " + maiorPreco);

            // ✅ Usando reduce para concatenar nomes de produtos
            List<String> nomes = Arrays.asList("Notebook", "Mouse", "Teclado");
            String listaNomes = nomes.stream()
                    .reduce("", (a, b) -> a + " " + b);

            System.out.println("Lista de produtos:" + listaNomes);


//Reduce funcionando com Objetos

            // Lista de produtos
            List<Produto> produtos = Arrays.asList(
                    new Produto("Notebook", 3000.0),
                    new Produto("Mouse", 100.0),
                    new Produto("Teclado", 200.0),
                    new Produto("Monitor", 800.0)
            );

            // ✅ Exemplo 1: Somar todos os preços dos produtos
            // - reduce começa com 0.0
            // - para cada produto, soma o preço
            double somaPrecos = produtos.stream()
                    .map(Produto::getPreco)              // transforma Produto em Double (preço)
                    .reduce(0.0, (a, b) -> a + b);       // soma todos os preços

            System.out.println("Soma dos preços: " + somaPrecos);

            // ✅ Exemplo 2: Encontrar o produto mais caro
            // - reduce compara dois produtos e retorna o mais caro
            Produto maisCaro = produtos.stream()
                    .reduce(produtos.get(0), (p1, p2) -> p1.getPreco() > p2.getPreco() ? p1 : p2);

            System.out.println("Produto mais caro: " + maisCaro.getNome() + " - R$" + maisCaro.getPreco());

            // ✅ Exemplo 3: Concatenar nomes dos produtos em uma única String
            String nomesConcatenados = produtos.stream()
                    .map(Produto::getNome)               // pega só os nomes
                    .reduce("", (a, b) -> a + " " + b);  // concatena todos

            System.out.println("Lista de produtos:" + nomesConcatenados);

            /*
            * 📌 Resumo didático
                    - map(Produto::getPreco) → transforma cada Produto em seu preço (double).
                    - reduce(0.0, (a, b) -> a + b) → soma todos os preços.
                    - reduce(p1, (p1, p2) -> ...) → compara produtos e retorna o mais caro.
                    - reduce("", (a, b) -> a + " " + b) → concatena nomes em uma única string.

            * */

    }
}