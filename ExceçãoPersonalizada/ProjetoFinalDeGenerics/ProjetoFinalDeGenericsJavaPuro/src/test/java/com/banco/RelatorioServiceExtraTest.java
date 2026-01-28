package com.banco;

import com.banco.model.Cliente;
import com.banco.model.ContaCorrente;
import com.banco.model.ContaPoupanca;
import com.banco.service.RelatorioService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RelatorioServiceExtraTest {

    @Test
    void testRelatorioComVariasContas() {
        Cliente c1 = new Cliente("Lucas", "111.111.111-11");
        Cliente c2 = new Cliente("Maria", "222.222.222-22");

        ContaCorrente cc = new ContaCorrente(c1, 1000.0);
        ContaPoupanca cp = new ContaPoupanca(c2, 500.0);

        List contas = Arrays.asList(cc, cp);

        double total = RelatorioService.calcularSaldoTotal(contas);
        assertEquals(1500.0, total);
    }
}