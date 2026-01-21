import exemplos.ExemplosBasicos;
import exemplos.Pessoa;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // O main agora só organiza as chamadas
        entradaDados();
        ExemplosBasicos.mostrarRepeticao();
        ExemplosBasicos.mostrarArrays();
        ExemplosBasicos.mostrarColecoes();
        ExemplosBasicos.mostrarMetodos();
        ExemplosBasicos.mostrarOperadoresLogicos();
    }

    // Método separado para entrada de dados
    public static void entradaDados() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Quantos anos você tem? ");
            int idade = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Você é emancipado? (sim/nao) ");
            String resposta = scanner.nextLine().trim().toLowerCase();
            boolean emancipado = resposta.equals("sim");

            Pessoa pessoa = new Pessoa(idade, emancipado);

            System.out.println("\n--- Entrada de dados ---");
            if (pessoa.podeDirigir()) {
                System.out.println("✔ Sim, você pode dirigir.");
            } else {
                System.out.println("✘ Não, você não pode dirigir.");
            }

            // Condicional usando a classe Pessoa
            ExemplosBasicos.mostrarCondicional(pessoa);
        }
    }
}
