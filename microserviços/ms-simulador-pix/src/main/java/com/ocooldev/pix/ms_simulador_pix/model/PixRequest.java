package com.ocooldev.pix.ms_simulador_pix.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PixRequest {
    private String key;
    private double amount;
    private String description;
}