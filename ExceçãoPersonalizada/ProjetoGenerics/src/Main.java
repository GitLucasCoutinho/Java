// Classe principal para testar o projeto com Generics
public class Main {
    public static void main(String[] args) {
        // Testando a classe Caixa
        // Aqui criamos uma Caixa que guarda Strings
        Caixa<String> caixaTexto = new Caixa<>();
        caixaTexto.setValor("Olá, Lucas!");
        System.out.println("Caixa de texto: " + caixaTexto.getValor());

        // Agora criamos uma Caixa que guarda Integers
        Caixa<Integer> caixaNumero = new Caixa<>();
        caixaNumero.setValor(42);
        System.out.println("Caixa de número: " + caixaNumero.getValor());

        // Testando a classe Par
        // Criamos um par com chave String e valor Integer
        Par<String, Integer> idade = new Par<>("Lucas", 25);
        System.out.println("Par: " + idade.getChave() + " tem " + idade.getValor() + " anos.");

        // Testando o método genérico imprimirArray
        String[] nomes = {"Lucas", "Maria", "João"};
        Integer[] numeros = {1, 2, 3, 4};

        System.out.println("\nImprimindo array de nomes:");
        Util.imprimirArray(nomes); // imprime todos os nomes

        System.out.println("\nImprimindo array de números:");
        Util.imprimirArray(numeros); // imprime todos os números
    }
}