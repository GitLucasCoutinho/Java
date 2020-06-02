package com.ocooldev.pix.ms_simulador_pix.domain.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.time.LocalDateTime;

@Embeddable // Classe incorporada em outra entidade
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calendario {
    private LocalDateTime criacao; // Data/hora da criação
    private Integer expiracao;     // Tempo de expiração em segundos
}