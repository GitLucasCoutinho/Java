package com.banco;

import com.banco.model.Cliente;
import com.banco.model.ContaCorrente;
import com.banco.model.ContaPoupanca;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    @Test
    void testDepositoESaqueContaCorrente() {
        Cliente c = new Cliente("Lucas", "111.111.111-11");
        ContaCorrente cc = new ContaCorrente(c, 1000.0);

        cc.depositar(500.0);
        assertEquals(1500.0, cc.getSaldo());

        cc.sacar(200.0);
        assertEquals(1300.0, cc.getSaldo());
    }

    @Test
    void testDepositoESaqueContaPoupanca() {
        Cliente c = new Cliente("Maria", "222.222.222-22");
        ContaPoupanca cp = new ContaPoupanca(c, 500.0);

        cp.depositar(300.0);
        assertEquals(800.0, cp.getSaldo());

        cp.sacar(100.0);
        assertEquals(700.0, cp.getSaldo());
    }
}