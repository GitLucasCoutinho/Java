package com.ocooldev.pix.ms_simulador_pix.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Requisição para criar uma nova transação Pix")
public class CreatePixTransactionRequest {

    @NotBlank(message = "Chave Pix é obrigatória")
    @Schema(
        description = "Chave Pix do recebedor (CPF, CNPJ, e-mail, telefone ou EVP aleatória)",
        example = "usuario@email.com",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String chave;

    @NotBlank(message = "Valor é obrigatório")
    @Schema(
        description = "Valor da cobrança em reais (formato: XX.XX)",
        example = "150.50",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String valor;

    @Schema(
        description = "Mensagem opcional que será exibida ao pagador (máximo 140 caracteres)",
        example = "Pagamento da Fatura #12345",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String solicitacaoPagador;

    @Schema(
        description = "Header para idempotência: evita duplicação de transações em caso de retentativas. Use um UUID único por transação.",
        example = "123e4567-e89b-12d3-a456-426614174000",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String idempotencyKey;
}
