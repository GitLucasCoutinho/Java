package com.ocooldev.pix.ms_simulador_pix.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Devolucao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // Identificador da devolução

    private String txid; // Transação original

    private String valor; // Valor devolvido (parcial ou total)

    private LocalDateTime dataHora; // Momento da devolução

    @Enumerated(EnumType.STRING)
    private StatusDevolucao status; // Status da devolução (SOLICITADA, EFETIVADA)
}