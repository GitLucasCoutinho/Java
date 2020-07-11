package com.banco.model;

// Conta Corrente herda de ContaBancaria
// Pode futuramente ter regras específicas (ex: limite de cheque especial).
public class ContaCorrente extends ContaBancaria {
    // Construtor correto: recebe um Cliente e o saldo inicial
    public ContaCorrente(Cliente cliente, Double saldoInicial) {
        super(cliente, saldoInicial);
    }
}