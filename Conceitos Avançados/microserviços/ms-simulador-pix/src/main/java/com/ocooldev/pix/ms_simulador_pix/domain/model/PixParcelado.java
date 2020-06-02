package com.ocooldev.pix.ms_simulador_pix.domain.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PixParcelado {

    @Id
    private String txid; // Identificador da cobrança parcelada

    private String chave; // Chave Pix

    @Embedded
    private com.ocooldev.pix.ms_simulador_pix.domain.model.Calendario calendario; // Datas de criação/expiração

    @Embedded
    private Valor valorTotal; // Valor total da cobrança

    private String status; // Status da cobrança

    private String solicitacaoPagador; // Mensagem opcional

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<com.ocooldev.pix.ms_simulador_pix.domain.model.Parcela> parcelas; // Lista de parcelas
}