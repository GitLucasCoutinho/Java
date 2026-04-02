package com.ocooldev.pix.ms_simulador_pix.infrastructure.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PixTransactionEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private String eventId;
    private String eventType; // CREATED, CONFIRMED, REFUNDED, EXPIRED
    private String txid;
    private String chave;
    private String valor;
    private String status;
    private LocalDateTime timestamp;
    private String description;
}
