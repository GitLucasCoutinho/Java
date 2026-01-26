package com.ocooldev.pix.ms_simulador_pix.domain.service;

import com.ocooldev.pix.ms_simulador_pix.domain.model.*;
import com.ocooldev.pix.ms_simulador_pix.infrastructure.repository.PixTransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service // Marca como componente de serviço
public class PixService {

    private final PixTransactionRepository repository;

    public PixService(PixTransactionRepository repository) {
        this.repository = repository;
    }

    // Autoriza uma nova transação Pix
    public PixTransaction authorize(String chave, String valorOriginal, String solicitacaoPagador) {
        PixTransaction tx = PixTransaction.builder()
                .txid(UUID.randomUUID().toString().replace("-", "").substring(0, 32))
                .chave(chave)
                .valor(new Valor(valorOriginal))
                .calendario(new Calendario(LocalDateTime.now(), 3600))
                .status("ATIVA")
                .horario(new Horario(null))
                .solicitacaoPagador(solicitacaoPagador)
                .build();
        return repository.save(tx);
    }

    public Optional<PixTransaction> getTransaction(String txid) {
        return repository.findById(txid);
    }

    public List<PixTransaction> listTransactions() {
        return repository.findAll();
    }

    public Optional<PixTransaction> refund(String txid) {
        return repository.findById(txid).map(tx -> {
            tx.setStatus("CONCLUIDA");
            tx.setHorario(new Horario(LocalDateTime.now()));
            return repository.save(tx);
        });
    }
}