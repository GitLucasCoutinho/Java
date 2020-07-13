package com.banco.model;

// Classe abstrata que implementa Conta<Double>
// Serve como base para ContaCorrente e ContaPoupanca
public abstract class ContaBancaria implements Conta<Double> {
    private Cliente cliente;
    private Double saldo;

    // Construtor: inicializa cliente e saldo
    public ContaBancaria(Cliente cliente, Double saldoInicial) {
        this.cliente = cliente;
        this.saldo = saldoInicial;
    }

    // Retorna o cliente da conta
    @Override
    public Cliente getCliente() { return cliente; }

    // Retorna o saldo atual
    @Override
    public Double getSaldo() { return saldo; }

    // Deposita um valor na conta
    @Override
    public void depositar(Double valor) { saldo += valor; }

    // Realiza um saque, verificando se há saldo suficiente
    @Override
    public void sacar(Double valor) {
        if (valor <= saldo) saldo -= valor;
        else System.out.println("Saldo insuficiente!");
    }

    // Representação textual da conta
    @Override
    public String toString() {
        return cliente + " - Saldo: R$" + saldo;
    }
}