package com.ocooldev.pix.ms_simulador_pix.domain.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Valor {
    private String original; // Valor em string, ex: "100.00"
}