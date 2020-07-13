package com.banco.controller;

import com.banco.model.Cliente;
import com.banco.model.ContaCorrente;
import com.banco.model.ContaPoupanca;
import com.banco.service.BancoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/banco")
public class BancoController {

    private final BancoService service = new BancoService();

    // Usado pelo endpoint REST
    @PostMapping("/transferir")
    public String transferir(@RequestParam double valor) {
        ContaCorrente cc = new ContaCorrente(new Cliente("Lucas", "111.111.111-11"), 1000.0);
        ContaPoupanca cp = new ContaPoupanca(new Cliente("Maria", "222.222.222-22"), 500.0);

        service.transferir(cc, cp, valor);
        return "Transferência realizada com sucesso! Saldo origem: " + cc.getSaldo() + ", destino: " + cp.getSaldo();
    }

    // Usado pelos testes unitários
    public String transferir(ContaCorrente origem, ContaPoupanca destino, double valor) {
        service.transferir(origem, destino, valor);
        return "Transferência realizada com sucesso!";
    }
}