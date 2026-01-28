// Conta Poupança herda de ContaBancaria
// Pode futuramente ter regras específicas (ex: rendimento mensal).
public class ContaPoupanca extends ContaBancaria {
    public ContaPoupanca(String titular, Double saldoInicial) {
        super(titular, saldoInicial);
    }
}