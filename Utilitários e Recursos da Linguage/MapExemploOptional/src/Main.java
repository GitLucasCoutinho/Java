import java.util.Optional;

// Classe principal
public class Main {
    public static void main(String[] args) {
        // 🔎 Simulando busca de produto pelo código
        // Se o código for "ABC123", o método retorna um Produto dentro de um Optional
        // Se não for encontrado, retorna Optional.empty()
        Optional<Produto> produtoEncontrado = buscarProduto("ABC123");
        Optional<Produto> produtoNaoEncontrado = buscarProduto("XYZ999");

        // ✅ Exemplo 1: Usando map para calcular desconto
        // O map só executa se o Optional tiver um Produto dentro.
        // Aqui, p -> p.getPreco() * 0.9 aplica 10% de desconto no preço do produto.
        // Se não tiver Produto (Optional vazio), cai no orElse(0.0) e retorna 0.
        double precoComDesconto = produtoEncontrado
                .map(p -> p.getPreco() * 0.9) // transforma Produto em Double (preço com desconto)
                .orElse(0.0); // valor alternativo se não existir Produto

        System.out.println("Preço com desconto: " + precoComDesconto);

        // ✅ Exemplo 2: Usando map para pegar nome do produto
        // Se o Optional tiver Produto, aplica Produto::getNome (method reference).
        // Se não tiver Produto, retorna "Produto não encontrado".
        String nomeProduto = produtoNaoEncontrado
                .map(Produto::getNome) // transforma Produto em String (nome)
                .orElse("Produto não encontrado"); // valor alternativo se vazio

        System.out.println("Resultado da busca: " + nomeProduto);
    }

    // 🛠 Método que simula busca de produto
    // Retorna Optional<Produto> para representar "pode existir ou não"
    private static Optional<Produto> buscarProduto(String codigo) {
        if ("ABC123".equals(codigo)) {
            // Optional.of(...) cria um Optional com valor presente
            return Optional.of(new Produto("Notebook", 3000.00));
        }
        // Optional.empty() representa ausência de valor
        return Optional.empty();
    }
}