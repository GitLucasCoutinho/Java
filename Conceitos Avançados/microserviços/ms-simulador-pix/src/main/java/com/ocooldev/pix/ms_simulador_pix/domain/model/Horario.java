package com.ocooldev.pix.ms_simulador_pix.domain.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.time.LocalDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Horario {
    private LocalDateTime liquidacao; // Data/hora da liquidação
}