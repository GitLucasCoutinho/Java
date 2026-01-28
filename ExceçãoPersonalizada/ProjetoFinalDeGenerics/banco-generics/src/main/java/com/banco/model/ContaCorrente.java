// Conta Corrente herda de ContaBancaria
// Pode futuramente ter regras específicas (ex: limite de cheque especial).
public class ContaCorrente extends ContaBancaria {
    public ContaCorrente(String titular, Double saldoInicial) {
        super(titular, saldoInicial);
    }
}