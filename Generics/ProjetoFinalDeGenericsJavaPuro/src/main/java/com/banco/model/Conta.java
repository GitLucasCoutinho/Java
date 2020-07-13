package com.banco.model;

// Interface genérica para contas
// <T extends Number> garante que o saldo seja sempre um número (Double, Integer, BigDecimal)
public interface Conta<T extends Number> {

    // Retorna o cliente associado à conta
    Cliente getCliente();

    // Retorna o saldo atual da conta
    T getSaldo();

    // Realiza um depósito na conta
    void depositar(T valor);

    // Realiza um saque na conta
    void sacar(T valor);
}