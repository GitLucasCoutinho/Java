import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainOperadoresDeAtribuicaoELogicos {

    static final String APP_NAME = "Fundamentos Java";

    public static void main(String[] args) {
        System.out.println("=== " + APP_NAME + " ===");

        try (Scanner scanner = new Scanner(System.in)) {
            entradaDados(scanner);
            estruturasCondicionais(20); // exemplo com idade fixa
            estruturasRepeticao();
            trabalharArrays();
            trabalharColecoes();
            usarMetodos();
            operadoresLogicos();
        }
    }

    // ===================== TÓPICO 1: Entrada de dados =====================
    public static void entradaDados(Scanner scanner) {
        System.out.println("\n--- Entrada de dados ---");
        System.out.print("Quantos anos você tem? ");
        int idade = scanner.nextInt();
        scanner.nextLine(); // consome quebra de linha

        System.out.print("Você é emancipado? (sim/nao) ");
        String resposta = scanner.nextLine().trim().toLowerCase();
        boolean emancipado = resposta.equals("sim");

        boolean podeDirigir = idade >= 18 || (emancipado && idade >= 16);

        if (podeDirigir) {
            System.out.println("✅ Sim, você pode dirigir.");
        } else {
            System.out.println("❌ Não, você não pode dirigir.");
        }
    }

    // ===================== TÓPICO 2: Estruturas condicionais =====================
    public static void estruturasCondicionais(int idade) {
        System.out.println("\n--- Estruturas condicionais ---");
        if (idade >= 18) {
            System.out.println("Você é maior de idade.");
        } else {
            System.out.println("Você é menor de idade.");
        }
    }

    // ===================== TÓPICO 3: Estruturas de repetição =====================
    public static void estruturasRepeticao() {
        System.out.println("\n--- Estruturas de repetição ---");

        System.out.print("Contagem (for): ");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        int contador = 0;
        while (contador < 3) {
            System.out.println("Loop while: " + contador);
            contador++;
        }
    }

    // ===================== TÓPICO 4: Arrays =====================
    public static void trabalharArrays() {
        System.out.println("\n--- Arrays ---");
        int[] numeros = {1, 2, 3, 4, 5};
        System.out.println("Primeiro número do array: " + numeros[0]);
    }

    // ===================== TÓPICO 5: Coleções =====================
    public static void trabalharColecoes() {
        System.out.println("\n--- Coleções (List) ---");
        List<String> nomes = new ArrayList<>();
        nomes.add("Lucas");
        nomes.add("Maria");
        nomes.add("João");

        for (String nome : nomes) {
            System.out.println("- " + nome);
        }
    }

    // ===================== TÓPICO 6: Métodos =====================
    public static void usarMetodos() {
        System.out.println("\n--- Métodos ---");
        int resultado = soma(10, 20);
        System.out.println("Resultado da soma: " + resultado);
    }

    public static int soma(int a, int b) {
        return a + b;
    }

    // ===================== TÓPICO 7: Operadores lógicos =====================
    public static void operadoresLogicos() {
        System.out.println("\n--- Operadores lógicos ---");

        System.out.printf("true && true = %s%n", true && true);
        System.out.printf("false && false = %s%n", false && false);
        System.out.printf("true && false = %s%n", true && false);
        System.out.printf("false && true = %s%n", false && true);

        System.out.println("-------------------------------------");

        System.out.printf("true || true = %s%n", true || true);
        System.out.printf("false || false = %s%n", false || false);
        System.out.printf("true || false = %s%n", true || false);
        System.out.printf("false || true = %s%n", false || true);

        System.out.println("-------------------------------------");

        System.out.printf("true | true = %s%n", true | true);
        System.out.printf("true | false = %s%n", true | false);
        System.out.printf("false | true = %s%n", false | true);
        System.out.printf("false | false = %s%n", false | false);
    }
}
