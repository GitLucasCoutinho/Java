package com.banco.controller;

import com.banco.model.*;
import com.banco.repository.*;
import com.banco.service.*;

import java.util.*;

// Controller simples para simular operações do banco
public class BancoController {
    private ClienteRepository clienteRepo = new ClienteRepository();
    private ContaRepository contaRepo = new ContaRepository();
    private BancoService bancoService = new BancoService();

    // Método que executa o fluxo principal do sistema
    public void executar() {
        // Criar clientes
        Cliente c1 = new Cliente("Lucas", "111.111.111-11");
        Cliente c2 = new Cliente("Maria", "222.222.222-22");

        clienteRepo.salvar(c1);
        clienteRepo.salvar(c2);

        // Criar contas
        ContaCorrente cc = new ContaCorrente(c1, 1500.0);
        ContaPoupanca cp = new ContaPoupanca(c2, 2500.0);

        contaRepo.salvar(c1.getCpf(), cc);
        contaRepo.salvar(c2.getCpf(), cp);

        // Transferência de valores
        bancoService.transferir(cc, cp, 500.0);

        // Relatórios
        RelatorioService.imprimirContas(contaRepo.listarTodas());
        System.out.println("💰 Saldo total: R$" + RelatorioService.calcularSaldoTotal(contaRepo.listarTodas()));
    }

    // Método que o teste espera
    public String transferir(ContaCorrente origem, ContaPoupanca destino, double valor) {
        bancoService.transferir(origem, destino, valor);
        return "Transferência realizada com sucesso!";
    }
}