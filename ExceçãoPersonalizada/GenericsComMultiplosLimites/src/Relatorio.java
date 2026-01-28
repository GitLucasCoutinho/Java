import java.util.List;

// Classe utilitária que usa múltiplos limites em Generics
public class Relatorio {

    // Método genérico com múltiplos limites:
    // <T extends ContaBancaria & Comparable<T>>
    // Significa: T deve ser uma ContaBancaria E também implementar Comparable
    public static <T extends ContaBancaria & Comparable<T>> void imprimirMaiorSaldo(List<T> contas) {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta disponível.");
            return;
        }

        T maior = contas.get(0); // assume a primeira como maior
        for (T conta : contas) {
            if (conta.compareTo(maior) > 0) {
                maior = conta; // atualiza se encontrar saldo maior
            }
        }

        System.out.println("🏆 Conta com maior saldo: " + maior);
    }
}