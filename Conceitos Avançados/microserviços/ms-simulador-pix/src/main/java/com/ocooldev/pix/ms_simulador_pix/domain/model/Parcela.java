package com.ocooldev.pix.ms_simulador_pix.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // Identificador da parcela

    private Integer numero; // Número da parcela (1, 2, 3...)

    private LocalDate vencimento; // Data de vencimento

    private String valor; // Valor da parcela em string

    @Enumerated(EnumType.STRING)
    private StatusParcela status; // PENDENTE, PAGA, VENCIDA
}

