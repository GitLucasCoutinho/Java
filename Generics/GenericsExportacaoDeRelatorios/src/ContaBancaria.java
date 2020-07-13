// Classe base que implementa a interface genérica Conta
public abstract class ContaBancaria implements Conta<Double> {
    private String titular;
    private Double saldo;

    public ContaBancaria(String titular, Double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    @Override
    public String getTitular() {
        return titular;
    }

    @Override
    public Double getSaldo() {
        return saldo;
    }

    @Override
    public void depositar(Double valor) {
        saldo += valor;
    }

    @Override
    public void sacar(Double valor) {
        if (valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    @Override
    public String toString() {
        return "Conta de " + titular + " - Saldo: R$" + saldo;
    }
}