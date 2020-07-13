package com.banco.model;
// Conta Poupança herda de ContaBancaria
// Pode futuramente ter regras específicas (ex: rendimento mensal).
public class ContaPoupanca extends ContaBancaria {
    public ContaPoupanca(Cliente cliente, Double saldoInicial) {
        super(cliente, saldoInicial);
    }
}