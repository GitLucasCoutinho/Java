// Esta classe representa uma conta bancária simples.
// É o "modelo" de dado que vamos manipular no sistema.
public class ContaBancaria {
    private String titular; // nome do dono da conta
    private double saldo;   // saldo atual da conta

    // Construtor: cria uma conta com titular e saldo inicial
    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    // Método para depositar dinheiro
    public void depositar(double valor) {
        saldo += valor; // adiciona o valor ao saldo
    }

    // Método para sacar dinheiro
    public void sacar(double valor) {
        if (valor <= saldo) { // só permite sacar se tiver saldo suficiente
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    // Getter para o nome do titular
    public String getTitular() {
        return titular;
    }

    // Getter para o saldo
    public double getSaldo() {
        return saldo;
    }

    // toString: define como a conta será exibida quando impressa
    @Override
    public String toString() {
        return "Conta de " + titular + " - Saldo: R$" + saldo;
    }
}