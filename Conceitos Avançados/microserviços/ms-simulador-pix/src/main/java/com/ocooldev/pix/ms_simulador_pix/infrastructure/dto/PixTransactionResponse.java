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
@Schema(description = "Resposta com detalhes completos de uma transação Pix")
public class PixTransactionResponse {

    @Schema(
        description = "Identificador único da transação Pix no simulador (32 caracteres hexadecimais)",
        example = "abc123def456ghi789jkl012mno345"
    )
    private String txid;

    @Schema(
        description = "Chave Pix utilizada para a transação (CPF, CNPJ, e-mail, telefone ou EVP)",
        example = "usuario@email.com"
    )
    private String chave;

    @Schema(
        description = "Valor da transação em reais",
        example = "150.50"
    )
    private BigDecimal valor;

    @Schema(
        description = "Status atual da transação (ATIVA, CONCLUIDA, CANCELADA, EXPIRADA, DEVOLVIDA)",
        example = "ATIVA"
    )
    private String status;

    @Schema(
        description = "Data e hora da criação da transação",
        example = "2026-04-02T11:30:00"
    )
    private LocalDateTime dataCriacao;

    @Schema(
        description = "Tempo de expiração da cobrança em segundos (padrão: 3600 = 1 hora)",
        example = "3600"
    )
    private Integer tempoExpiracao;

    @Schema(
        description = "Data e hora da liquidação/conclusão da transação (null se ainda não liquidada)",
        example = "2026-04-02T11:35:00"
    )
    private LocalDateTime dataLiquidacao;

    @Schema(
        description = "Mensagem opcional visível ao pagador",
        example = "Pagamento da Fatura #12345"
    )
    private String solicitacaoPagador;
}
