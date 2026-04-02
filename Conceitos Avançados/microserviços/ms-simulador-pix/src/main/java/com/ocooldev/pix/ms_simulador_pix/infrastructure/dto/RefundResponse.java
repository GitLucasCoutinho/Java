package com.ocooldev.pix.ms_simulador_pix.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Resposta com detalhes de uma devolução Pix")
public class RefundResponse {

    @Schema(
        description = "Identificador único da devolução",
        example = "d1e2f3g4h5i6j7k8l9m0n1o2p3q4r5s6"
    )
    private String id;

    @Schema(
        description = "Identificador da transação original que está sendo devolvida",
        example = "abc123def456ghi789jkl012mno345"
    )
    private String txid;

    @Schema(
        description = "Valor devolvido em reais",
        example = "150.50"
    )
    private BigDecimal valor;

    @Schema(
        description = "Status da devolução (SOLICITADA, EFETIVADA, CANCELADA)",
        example = "EFETIVADA"
    )
    private String status;

    @Schema(
        description = "Data e hora quando a devolução foi solicitada",
        example = "2026-04-02T11:40:00"
    )
    private LocalDateTime dataHora;
}
