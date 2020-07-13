package com.banco;

import com.banco.controller.BancoController;
import com.banco.model.Cliente;
import com.banco.model.ContaCorrente;
import com.banco.model.ContaPoupanca;
import com.banco.service.BancoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BancoControllerTest {

    @Test
    void testTransferenciaController() {
        Cliente c1 = new Cliente("Lucas", "111.111.111-11");
        Cliente c2 = new Cliente("Maria", "222.222.222-22");

        ContaCorrente cc = new ContaCorrente(c1, 1000.0);
        ContaPoupanca cp = new ContaPoupanca(c2, 500.0);

        BancoController controller = new BancoController();
        String resultado = controller.transferir(cc, cp, 200.0);

        assertEquals("Transferência realizada com sucesso!", resultado);
        assertEquals(800.0, cc.getSaldo());
        assertEquals(700.0, cp.getSaldo());
    }
}