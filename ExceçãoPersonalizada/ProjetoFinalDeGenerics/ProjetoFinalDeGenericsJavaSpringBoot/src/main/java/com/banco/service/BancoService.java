package com.banco.service;

import com.banco.model.ContaBancaria;

// Serviço com regras de negócio do banco
public class BancoService {

    // Método que realiza transferência entre duas contas
    // Recebe conta de origem, conta de destino e valor
    public void transferir(ContaBancaria origem, ContaBancaria destino, Double valor) {
        origem.sacar(valor);      // retira o valor da conta de origem
        destino.depositar(valor); // adiciona o valor na conta de destino
    }
}