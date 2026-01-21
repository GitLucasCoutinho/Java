import java.util.Scanner;

public class MainOperadoresDeAtribuicaoELogicos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pergunta a idade
        System.out.print("Quantos anos você tem? ");
        int age = scanner.nextInt();

        // Pergunta se é emancipado
        System.out.print("Você é emancipado? (true/false) ");
        boolean isEmancipated = scanner.nextBoolean();

        // Regra: pode dirigir se tiver 18 anos ou mais,
        // ou se for emancipado e tiver pelo menos 16 anos
        boolean canDrive = age >= 18 || (isEmancipated && age >= 16);

        // Exibe o resultado formatado
        System.out.printf("Você pode dirigir? (%s)%n", canDrive);


        System.out.println("=====================================");
        System.out.println("=====================================");

        // Operador lógico AND (&&)
        System.out.printf("true && true = %s%n", true && true);
        System.out.printf("false && false = %s%n", false && false);
        System.out.printf("true && false = %s%n", true && false);
        System.out.printf("false && true = %s%n", false && true);

        System.out.println("=====================================");

        // Operador lógico OR (||)
        System.out.printf("true || true = %s%n", true || true);
        System.out.printf("false || false = %s%n", false || false);
        System.out.printf("true || false = %s%n", true || false);
        System.out.printf("false || true = %s%n", false || true);

        System.out.println("=====================================");

        // Operador bitwise OR (|)
        System.out.printf("true | true = %s%n", true | true);
        System.out.printf("true | false = %s%n", true | false);
        System.out.printf("false | true = %s%n", false | true);
        System.out.printf("false | false = %s%n", false | false);

        System.out.println("=====================================");

    }
}
