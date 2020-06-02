package com.ocooldev.pix.ms_simulador_pix.domain.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoAdicional {
    private String nome;  // Nome do campo extra
    private String valor; // Valor correspondente
}