// Classe abstrata que implementa a interface Conta<Double>
// Serve como base para diferentes tipos de contas (Corrente, Poupança).
public abstract class ContaBancaria implements Conta<Double> {
    private String titular; // Nome do titular da conta
    private Double saldo;   // Saldo da conta

    // Construtor: inicializa titular e saldo
    public ContaBancaria(String titular, Double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    // Retorna o nome do titular
    @Override
    public String getTitular() {
        return titular;
    }

    // Retorna o saldo atual
    @Override
    public Double getSaldo() {
        return saldo;
    }

    // Deposita um valor na conta
    @Override
    public void depositar(Double valor) {
        saldo += valor;
    }

    // Realiza um saque, verificando se há saldo suficiente
    @Override
    public void sacar(Double valor) {
        if (valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    // Representação textual da conta
    @Override
    public String toString() {
        return "Conta de " + titular + " - Saldo: R$" + saldo;
    }
}