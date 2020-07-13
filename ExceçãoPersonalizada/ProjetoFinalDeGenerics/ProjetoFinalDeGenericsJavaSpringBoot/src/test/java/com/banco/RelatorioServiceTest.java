package com.banco;

import com.banco.model.*;
import com.banco.service.RelatorioService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

// Classe de teste para RelatorioService
// Aqui validamos os relatórios que usam Generics e wildcards
public class RelatorioServiceTest {

    // Testa se o cálculo do saldo total está correto
    @Test
    void testCalcularSaldoTotal() {
        // Criamos dois clientes
        Cliente c1 = new Cliente("Lucas", "111.111.111-11");
        Cliente c2 = new Cliente("Maria", "222.222.222-22");

        // Criamos contas para os clientes
        ContaCorrente cc = new ContaCorrente(c1, 1000.0);
        ContaPoupanca cp = new ContaPoupanca(c2, 500.0);

        // Criamos uma lista de contas
        List<Conta<?>> contas = new ArrayList<>();
        contas.add(cc);
        contas.add(cp);

        // Calculamos o saldo total usando o serviço de relatórios
        double total = RelatorioService.calcularSaldoTotal(contas);

        // Validamos que o saldo total é a soma correta (1000 + 500 = 1500)
        assertEquals(1500.0, total);
    }

    // Testa se o relatório imprime contas sem lançar exceções
    @Test
    void testImprimirContas() {
        // Criamos clientes e contas
        Cliente c1 = new Cliente("Lucas", "111.111.111-11");
        Cliente c2 = new Cliente("Maria", "222.222.222-22");

        ContaCorrente cc = new ContaCorrente(c1, 1000.0);
        ContaPoupanca cp = new ContaPoupanca(c2, 500.0);

        List<Conta<?>> contas = Arrays.asList(cc, cp);

        // Chamamos o método de impressão
        // Aqui não validamos saída no console, apenas garantimos que não lança erro
        RelatorioService.imprimirContas(contas);
    }
}