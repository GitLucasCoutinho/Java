package com.banco;

import com.banco.model.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    void testGetters() {
        Cliente cliente = new Cliente("Lucas", "111.111.111-11");

        assertEquals("Lucas", cliente.getNome());
        assertEquals("111.111.111-11", cliente.getCpf());
    }
}