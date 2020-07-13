package com.banco;

import com.banco.model.Cliente;
import com.banco.model.ContaCorrente;
import com.banco.repository.ContaRepository;
import com.banco.service.BancoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class BancoServiceMockitoTest {

    @Test
    void testTransferenciaComMock() {
        // Criamos mocks do repositório
        ContaRepository contaRepoMock = Mockito.mock(ContaRepository.class);

        // Criamos clientes e contas reais
        Cliente c1 = new Cliente("Lucas", "111.111.111-11");
        Cliente c2 = new Cliente("Maria", "222.222.222-22");

        ContaCorrente origem = new ContaCorrente(c1, 1000.0);
        ContaCorrente destino = new ContaCorrente(c2, 500.0);

        // Criamos o serviço usando o mock
        BancoService service = new BancoService();

        // Executamos a transferência
        service.transferir(origem, destino, 200.0);

        // Validamos os saldos
        assertEquals(800.0, origem.getSaldo());
        assertEquals(700.0, destino.getSaldo());

        // Verificamos se o mock foi chamado (exemplo de uso do Mockito)
        Mockito.verifyNoInteractions(contaRepoMock);
    }
}