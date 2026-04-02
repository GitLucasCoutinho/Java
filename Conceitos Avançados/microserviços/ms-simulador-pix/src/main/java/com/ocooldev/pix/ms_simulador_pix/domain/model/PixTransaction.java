package com.ocooldev.pix.ms_simulador_pix.domain.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity // Indica que é uma entidade persistida no banco
@Data // Gera automaticamente getters, setters, equals, hashCode e toString
@NoArgsConstructor // Construtor vazio
@AllArgsConstructor // Construtor com todos os campos
@Builder // Permite criar objetos com o padrão Builder
public class PixTransaction {

    @Id // Chave primária
    private String txid; // Identificador único da transação Pix

    private String chave; // Chave Pix usada (CPF, CNPJ, e-mail, telefone ou EVP)

    @Embedded // Objeto incorporado na tabela
    private com.ocooldev.pix.ms_simulador_pix.domain.model.Calendario calendario; // Datas de criação e expiração

    @Embedded
    private Valor valor; // Valor original da transação

    @Enumerated(EnumType.STRING)
    private StatusTransacao status; // Status da cobrança (ATIVA, CONCLUIDA, etc.)

    @Embedded
    private com.ocooldev.pix.ms_simulador_pix.domain.model.Horario horario; // Data/hora da liquidação

    @ElementCollection // Lista de objetos simples embutidos
    private List<com.ocooldev.pix.ms_simulador_pix.domain.model.InfoAdicional> infoAdicionais; // Pares nome/valor extras

    private String solicitacaoPagador; // Mensagem opcional visível ao pagador
}

