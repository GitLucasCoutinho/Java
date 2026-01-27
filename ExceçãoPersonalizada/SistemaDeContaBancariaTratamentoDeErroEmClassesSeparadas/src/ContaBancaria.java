// Classe que representa uma conta bancária
public class ContaBancaria {
    private String titular; // nome do titular da conta
    private double saldo;   // saldo atual da conta

    // Construtor: inicializa a conta com titular e saldo inicial
    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    // Método de saque
    public void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException {
        if (valor <= 0) { // verifica se o valor é inválido
            throw new ValorInvalidoException("Valor de saque inválido: " + valor);
        }
        if (valor > saldo) { // verifica se há saldo suficiente
            throw new SaldoInsuficienteException("Saldo insuficiente para saque de R$" + valor + ". Saldo atual: R$" + saldo);
        }
        saldo -= valor; // realiza o saque
        System.out.println("Saque realizado com sucesso! Novo saldo: R$" + saldo);
    }

    // Método de depósito
    public void depositar(double valor) throws ValorInvalidoException {
        if (valor <= 0) { // verifica se o valor é inválido
            throw new ValorInvalidoException("Valor de depósito inválido: " + valor);
        }
        saldo += valor; // adiciona ao saldo
        System.out.println("Depósito de R$" + valor + " realizado! Novo saldo: R$" + saldo);
    }

    // Método de transferência entre contas
    public void transferir(ContaBancaria destino, double valor) throws SaldoInsuficienteException, ValorInvalidoException, ContaNaoEncontradaException {
        if (destino == null) { // verifica se a conta destino existe
            throw new ContaNaoEncontradaException("Conta destino não encontrada!");
        }
        this.sacar(valor);       // usa o método sacar desta conta
        destino.depositar(valor); // usa o método depositar na conta destino
        System.out.println("Transferência de R$" + valor + " para " + destino.titular + " realizada com sucesso!");
    }

    // Getter para saldo
    public double getSaldo() {
        return saldo;
    }

    // Getter para titular
    public String getTitular() {
        return titular;
    }
}