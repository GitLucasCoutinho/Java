import java.util.*;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // Criando contas
        ContaCorrente corrente = new ContaCorrente("Lucas", 1500.0);
        ContaPoupanca poupanca = new ContaPoupanca("Maria", 2500.0);

        // Adicionando contas com CPF
        banco.adicionarConta("111.111.111-11", corrente);
        banco.adicionarConta("222.222.222-22", poupanca);

        // Relatório de contas
        Relatorio.imprimirContas(banco.listarContas());

        // Saldo total
        double total = Relatorio.calcularSaldoTotal(banco.listarContas());
        System.out.println("💰 Saldo total: R$" + total);

        // Listando CPFs
        System.out.println("🆔 CPFs cadastrados: " + banco.listarCpfs());
    }
}