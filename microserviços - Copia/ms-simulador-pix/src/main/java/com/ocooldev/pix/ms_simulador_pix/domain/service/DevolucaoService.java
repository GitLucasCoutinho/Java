package com.ocooldev.pix.ms_simulador_pix.domain.service;

import com.ocooldev.pix.ms_simulador_pix.domain.model.Devolucao;
import com.ocooldev.pix.ms_simulador_pix.infrastructure.repository.DevolucaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DevolucaoService {

    private final DevolucaoRepository repository;

    public DevolucaoService(DevolucaoRepository repository) {
        this.repository = repository;
    }

    // Solicita devolução
    public Devolucao solicitarDevolucao(String txid, String valor) {
        Devolucao dev = Devolucao.builder()
                .txid(txid)
                .valor(valor)
                .dataHora(LocalDateTime.now())
                .status("SOLICITADA")
                .build();
        return repository.save(dev);
    }

    // Efetiva devolução
    public Optional<Devolucao> efetivarDevolucao(String id) {
        return repository.findById(id).map(dev -> {
            dev.setStatus("EFETIVADA");
            return repository.save(dev);
        });
    }
}