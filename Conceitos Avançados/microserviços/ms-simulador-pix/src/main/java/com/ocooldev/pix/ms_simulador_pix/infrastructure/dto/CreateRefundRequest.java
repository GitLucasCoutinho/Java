package com.ocooldev.pix.ms_simulador_pix.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Requisição para solicitar devolução de uma transação Pix")
public class CreateRefundRequest {

    @Schema(
        description = "Valor a ser devolvido em reais. Se omitido, devolve o valor total da transação (devolução total).",
        example = "50.00",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String valor;
}
