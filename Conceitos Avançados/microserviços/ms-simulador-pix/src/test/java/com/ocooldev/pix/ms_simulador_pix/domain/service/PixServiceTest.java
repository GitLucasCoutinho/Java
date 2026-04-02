package com.ocooldev.pix.ms_simulador_pix.domain.service;

import com.ocooldev.pix.ms_simulador_pix.domain.model.PixTransaction;
import com.ocooldev.pix.ms_simulador_pix.domain.model.StatusTransacao;
import com.ocooldev.pix.ms_simulador_pix.infrastructure.repository.PixTransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PixServiceTest {

    @Mock
    private PixTransactionRepository repository;

    @Mock
    private IdempotencyService idempotencyService;

    @Mock
    private QRCodeService qrCodeService;

    @InjectMocks
    private PixService service;

    @Test
    void authorize_shouldCreateTransaction() {
        // Given
        String chave = "teste@pix.com";
        String valor = "100.00";
        PixTransaction mockTx = PixTransaction.builder()
                .txid("12345678901234567890123456789012")
                .chave(chave)
                .valor(new com.ocooldev.pix.ms_simulador_pix.domain.model.Valor(BigDecimal.valueOf(100.00)))
                .status(StatusTransacao.ATIVA)
                .build();
        when(repository.save(any(PixTransaction.class))).thenReturn(mockTx);

        // When
        PixTransaction result = service.authorize(chave, valor, null, null);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getChave()).isEqualTo(chave);
        assertThat(result.getValor().getOriginal()).isEqualTo(BigDecimal.valueOf(100.00));
        assertThat(result.getStatus()).isEqualTo(StatusTransacao.ATIVA);
    }

    @Test
    void getTransaction_shouldReturnTransaction() {
        // Given
        String txid = "123";
        PixTransaction mockTx = PixTransaction.builder().txid(txid).build();
        when(repository.findById(txid)).thenReturn(Optional.of(mockTx));

        // When
        Optional<PixTransaction> result = service.getTransaction(txid);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getTxid()).isEqualTo(txid);
    }
}
