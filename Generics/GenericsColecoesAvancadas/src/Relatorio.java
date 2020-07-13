import java.util.Collection;

// Classe utilitária para relatórios
public class Relatorio {

    // Relatório genérico de contas
    public static void imprimirContas(Collection<? extends Conta<?>> contas) {
        System.out.println("📋 Relatório de contas:");
        for (Conta<?> conta : contas) {
            System.out.println(conta);
        }
    }

    // Calcula saldo total
    public static double calcularSaldoTotal(Collection<? extends Conta<? extends Number>> contas) {
        double total = 0;
        for (Conta<? extends Number> conta : contas) {
            total += conta.getSaldo().doubleValue();
        }
        return total;
    }

    // Método estático que calcula o saldo total de uma coleção de contas.
    // A assinatura é: public static double calcularSaldoTotal(Collection<? extends Conta<? extends Number>> contas)
    //
    // Vamos detalhar:
    //
    // 1. Collection<? extends Conta<? extends Number>>
    //    - "Collection" significa que o método aceita QUALQUER coleção (pode ser List, Set, etc.).
    //    - O símbolo "?" é chamado de WILDCARD (curinga).
    //      Ele significa "não sei exatamente qual é o tipo, mas sei que ele respeita certas regras".
    //    - "? extends Conta<? extends Number>" significa:
    //        → Aceito qualquer tipo que seja uma SUBCLASSE de Conta (ex: ContaCorrente, ContaPoupanca).
    //        → Dentro de Conta, o saldo também é genérico, mas limitado a Number (Integer, Double, etc.).
    //    - Em resumo: o método aceita uma coleção de qualquer tipo de conta bancária,
    //      desde que o saldo seja um número.
    //
    // 2. double total = 0;
    //    - Variável acumuladora que começa em 0.
    //    - Vai somar todos os saldos das contas recebidas.
    //
    // 3. for (Conta<? extends Number> conta : contas)
    //    - Percorre cada elemento da coleção.
    //    - Aqui usamos "Conta<? extends Number>" porque cada conta tem um saldo que é algum tipo de Number.
    //    - O "?" novamente é um curinga: pode ser Integer, Double, BigDecimal, etc.
    //    - O importante é que todos são subclasses de Number.
    //
    // 4. total += conta.getSaldo().doubleValue();
    //    - Para somar, precisamos converter o saldo para double.
    //    - O método doubleValue() existe em todas as subclasses de Number (Integer, Double, etc.).
    //    - Assim conseguimos somar mesmo que uma conta use Integer e outra use Double.
    //
    // 5. return total;
    //    - No final, retorna o saldo total como double.
    //
    // 📌 Em resumo:
    // - O "?" (wildcard) é um CURINGA que dá flexibilidade.
    // - "? extends Conta<? extends Number>" significa "qualquer tipo que seja uma Conta de números".
    // - Isso permite que o método funcione para diferentes tipos de contas sem precisar duplicar código.
    // - O uso de doubleValue() garante que todos os saldos (sejam Integers ou Doubles) possam ser somados.

}