package com.banco.repository;

import com.banco.model.Cliente;
import java.util.*;

// Repositório simples em memória para clientes
// Responsável por armazenar e recuperar objetos Cliente
public class ClienteRepository {
    // Usamos um Map para associar CPF → Cliente
    private Map<String, Cliente> clientes = new HashMap<>();

    // Salva um cliente no repositório
    // Se já existir um cliente com o mesmo CPF, ele será sobrescrito
    public void salvar(Cliente cliente) {
        clientes.put(cliente.getCpf(), cliente);
    }

    // Busca um cliente pelo CPF
    // Retorna null se não encontrar
    public Cliente buscarPorCpf(String cpf) {
        return clientes.get(cpf);
    }

    // Lista todos os clientes cadastrados
    public Collection<Cliente> listarTodos() {
        return clientes.values();
    }
}