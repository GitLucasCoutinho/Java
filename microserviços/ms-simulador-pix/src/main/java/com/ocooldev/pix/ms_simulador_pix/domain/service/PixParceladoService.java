package com.ocooldev.pix.ms_simulador_pix.domain.service;

import com.ocooldev.pix.ms_simulador_pix.domain.model.*;
import com.ocooldev.pix.ms_simulador_pix.infrastructure.repository.PixParceladoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PixParceladoService {

    private final PixParceladoRepository repository;

    public PixParceladoService(PixParceladoRepository repository) {
        this.repository = repository;
    }

    // Cria cobrança parcelada
    public PixParcelado authorizeParcelado(String chave, String valorTotal, int qtdParcelas, String solicitacaoPagador) {
        List<Parcela> parcelas = new ArrayList<>();
        double valorParcela = Double.parseDouble(valorTotal) / qtdParcelas;

        for (int i = 1; i <= qtdParcelas; i++) {
            parcelas.add(Parcela.builder()
                    .numero(i)
                    .vencimento(LocalDate.now().plusMonths(i))
                    .valor(String.format("%.2f", valorParcela))
                    .status("PENDENTE")
                    .build());
        }

        PixParcelado cobv = PixParcelado.builder()
                .txid(UUID.randomUUID().toString().replace("-", "").substring(0, 32))
                .chave(chave)
                .valorTotal(new Valor(valorTotal))
                .calendario(new Calendario(LocalDateTime.now(), 86400))
                .status("ATIVA")
                .solicitacaoPagador(solicitacaoPagador)
                .parcelas(parcelas)
                .build();

        return repository.save(cobv);
    }
}