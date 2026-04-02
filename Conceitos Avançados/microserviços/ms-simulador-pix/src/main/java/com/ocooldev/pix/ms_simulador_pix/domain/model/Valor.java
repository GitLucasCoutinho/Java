package com.ocooldev.pix.ms_simulador_pix.domain.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.math.BigDecimal;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Valor {
    private BigDecimal original; // Valor em BigDecimal para precisão monetária
}