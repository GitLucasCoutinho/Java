package com.ocooldev.pix.ms_simulador_pix.service;

import com.ocooldev.pix.ms_simulador_pix.model.*;
import com.ocooldev.pix.ms_simulador_pix.repository.PixTransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PixService {

    private final PixTransactionRepository repository;

    public PixService(PixTransactionRepository repository) {
        this.repository = repository;
    }

    public PixTransaction authorize(String chave, String valorOriginal) {
        PixTransaction tx = PixTransaction.builder()
                .txid(UUID.randomUUID().toString().replace("-", "").substring(0, 32)) // formato oficial: até 35 chars
                .chave(chave)
                .valor(Valor.builder().original(valorOriginal).build())
                .calendario(Calendario.builder()
                        .criacao(LocalDateTime.now())
                        .expiracao(3600) // 1 hora
                        .build())
                .status("ATIVA")
                .horario(Horario.builder().liquidacao(null).build())
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
            tx.setHorario(Horario.builder().liquidacao(LocalDateTime.now()).build());
            return repository.save(tx);
        });
    }
}