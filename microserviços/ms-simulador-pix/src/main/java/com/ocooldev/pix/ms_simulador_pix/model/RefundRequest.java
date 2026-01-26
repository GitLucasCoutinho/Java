package com.ocooldev.pix.ms_simulador_pix.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefundRequest {
    private String transactionId;
}