package com.ocooldev.pix.ms_simulador_pix.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PixTransaction {

    @Id
    private String txid; // Identificador único da transação

    private String chave; // chave Pix (CPF, CNPJ, e-mail, telefone ou EVP)

    @Embedded
    private Calendario calendario;

    @Embedded
    private Valor valor;

    private String status; // ATIVA, CONCLUIDA, etc.

    @Embedded
    private Horario horario;
}