package com.ocooldev.pix.ms_simulador_pix.domain.service;

import com.ocooldev.pix.ms_simulador_pix.domain.model.*;
import com.ocooldev.pix.ms_simulador_pix.infrastructure.messaging.PixEventPublisher;
import com.ocooldev.pix.ms_simulador_pix.infrastructure.repository.PixTransactionRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service // Marca como componente de serviço
public class PixService {

    private final PixTransactionRepository repository;
    private final IdempotencyService idempotencyService;
    private final QRCodeService qrCodeService;
    private final PixEventPublisher eventPublisher;

    public PixService(PixTransactionRepository repository, IdempotencyService idempotencyService, QRCodeService qrCodeService, PixEventPublisher eventPublisher) {
        this.repository = repository;
        this.idempotencyService = idempotencyService;
        this.qrCodeService = qrCodeService;
        this.eventPublisher = eventPublisher;
    }

    // Autoriza uma nova transação Pix
    @CircuitBreaker(name = "pixService", fallbackMethod = "authorizeFallback")
    public PixTransaction authorize(String chave, String valorOriginal, String solicitacaoPagador, String idempotencyKey) {
        String txid = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
        if (idempotencyKey != null && !idempotencyService.isIdempotent(idempotencyKey, txid)) {
            throw new IllegalArgumentException("Chave de idempotência já utilizada");
        }
        PixTransaction tx = PixTransaction.builder()
                .txid(txid)
                .chave(chave)
                .valor(new Valor(new BigDecimal(valorOriginal)))
                .calendario(new Calendario(LocalDateTime.now(), 3600))
                .status(StatusTransacao.ATIVA)
                .horario(new Horario(null))
                .solicitacaoPagador(solicitacaoPagador)
                .build();
        PixTransaction saved = repository.save(tx);

        // Publicar evento de transação criada
        eventPublisher.publishTransactionCreated(txid, chave, valorOriginal);

        return saved;
    }

    // Fallback method para Circuit Breaker
    public PixTransaction authorizeFallback(String chave, String valorOriginal, String solicitacaoPagador,
                                           String idempotencyKey, Exception e) {
        throw new RuntimeException("Serviço indisponível no momento. Tente novamente mais tarde.", e);
    }

    public Optional<PixTransaction> getTransaction(String txid) {
        return repository.findById(txid);
    }

    public List<PixTransaction> listTransactions() {
        return repository.findAll();
    }

    public Optional<PixTransaction> refund(String txid) {
        return repository.findById(txid).map(tx -> {
            tx.setStatus(StatusTransacao.CONCLUIDA);
            tx.setHorario(new Horario(LocalDateTime.now()));
            PixTransaction saved = repository.save(tx);

            // Publicar evento de transação confirmada
            eventPublisher.publishTransactionConfirmed(txid, tx.getChave(), tx.getValor().getOriginal().toString());

            return saved;
        });
    }

    public byte[] generateQRCode(String txid) throws Exception {
        Optional<PixTransaction> txOpt = repository.findById(txid);
        if (txOpt.isEmpty()) {
            throw new IllegalArgumentException("Transação não encontrada");
        }
        PixTransaction tx = txOpt.get();
        String payload = qrCodeService.generatePixPayload(tx.getChave(), tx.getValor().getOriginal(), tx.getTxid(), "Simulador Pix", "São Paulo");
        return qrCodeService.generateQRCode(payload, 300, 300);
    }
}