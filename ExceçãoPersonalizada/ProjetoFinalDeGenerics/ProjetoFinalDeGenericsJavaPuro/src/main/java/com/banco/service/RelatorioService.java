package com.banco.service;

import com.banco.model.Conta;
import java.util.Collection;

// Serviço para relatórios usando Generics
public class RelatorioService {

    // Imprime todas as contas recebidas
    // Usa Collection<? extends Conta<?>> para aceitar qualquer tipo de conta
    public static void imprimirContas(Collection<? extends Conta<?>> contas) {
        System.out.println("📋 Relatório de contas:");
        for (Conta<?> conta : contas) {
            System.out.println(conta);
        }
    }

    // Calcula o saldo total de todas as contas
    // Usa Collection<? extends Conta<? extends Number>> para garantir que o saldo seja numérico
    public static double calcularSaldoTotal(Collection<? extends Conta<? extends Number>> contas) {
        double total = 0;
        for (Conta<? extends Number> conta : contas) {
            total += conta.getSaldo().doubleValue(); // converte saldo para double e soma
        }
        return total;
    }
}