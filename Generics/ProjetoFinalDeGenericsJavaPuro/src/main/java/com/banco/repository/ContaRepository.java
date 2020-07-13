package com.banco.repository;

import com.banco.model.ContaBancaria;
import java.util.*;

// Repositório simples em memória para contas bancárias
// Responsável por armazenar e recuperar objetos ContaBancaria
public class ContaRepository {
    // Usamos um Map para associar CPF → ContaBancaria
    private Map<String, ContaBancaria> contas = new HashMap<>();

    // Salva uma conta no repositório
    // Cada CPF pode ter uma conta associada
    public void salvar(String cpf, ContaBancaria conta) {
        contas.put(cpf, conta);
    }

    // Busca uma conta pelo CPF
    // Retorna null se não encontrar
    public ContaBancaria buscarPorCpf(String cpf) {
        return contas.get(cpf);
    }

    // Lista todas as contas cadastradas
    public Collection<ContaBancaria> listarTodas() {
        return contas.values();
    }
}