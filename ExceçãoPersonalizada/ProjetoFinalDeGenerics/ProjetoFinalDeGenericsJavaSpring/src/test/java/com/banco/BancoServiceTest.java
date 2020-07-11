package com.banco;

import com.banco.model.*;
import com.banco.service.BancoService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Classe de teste para BancoService
// Aqui validamos as regras de negócio, como transferências entre contas
public class BancoServiceTest {

    // Testa se a transferência entre duas contas funciona corretamente
    @Test
    void testTransferencia() {
        // Criamos dois clientes
        Cliente c1 = new Cliente("Lucas", "111.111.111-11");
        Cliente c2 = new Cliente("Maria", "222.222.222-22");

        // Criamos contas para os clientes
        ContaCorrente cc = new ContaCorrente(c1, 1000.0);
        ContaPoupanca cp = new ContaPoupanca(c2, 500.0);

        // Instanciamos o serviço do banco
        BancoService service = new BancoService();

        // Realizamos uma transferência de R$200 da conta corrente para a poupança
        service.transferir(cc, cp, 200.0);

        // Validamos que o saldo da conta corrente foi reduzido corretamente
        assertEquals(800.0, cc.getSaldo());

        // Validamos que o saldo da conta poupança foi aumentado corretamente
        assertEquals(700.0, cp.getSaldo());
    }
}