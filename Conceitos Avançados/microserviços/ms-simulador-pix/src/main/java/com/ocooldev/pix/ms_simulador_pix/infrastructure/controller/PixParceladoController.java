package com.ocooldev.pix.ms_simulador_pix.infrastructure.controller;

import com.ocooldev.pix.ms_simulador_pix.domain.model.PixParcelado;
import com.ocooldev.pix.ms_simulador_pix.domain.service.PixParceladoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pix/parcelado")
@Tag(name = "Cobranças Parceladas", description = "Endpoints para gerenciar cobranças Pix parceladas (com múltiplos vencimentos)")
public class PixParceladoController {

    private final PixParceladoService service;

    public PixParceladoController(PixParceladoService service) {
        this.service = service;
    }

    @Operation(
        summary = "Criar cobrança Pix parcelada",
        description = "Cria uma cobrança Pix com múltiplas parcelas. Cada parcela terá uma data de vencimento diferente, distribuídas mensalmente. Útil para simular vendas a prazo."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cobrança parcelada criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Parâmetros inválidos (quantidade de parcelas deve ser >= 2)"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/authorize")
    public ResponseEntity<PixParcelado> authorize(
            @Parameter(
                name = "chave",
                description = "Chave Pix do recebedor (CPF, CNPJ, e-mail, telefone ou EVP)",
                example = "empresa@pix.com",
                required = true
            )
            @RequestParam String chave,

            @Parameter(
                name = "valorTotal",
                description = "Valor total da cobrança parcelada em reais. Será dividido igualmente entre as parcelas.",
                example = "1200.00",
                required = true
            )
            @RequestParam String valorTotal,

            @Parameter(
                name = "qtdParcelas",
                description = "Quantidade de parcelas. Mínimo: 2, Máximo: 12. Cada parcela vence 1 mês após a anterior.",
                example = "3",
                required = true
            )
            @RequestParam int qtdParcelas,

            @Parameter(
                name = "solicitacaoPagador",
                description = "Mensagem opcional que será exibida ao pagador (máximo 140 caracteres)",
                example = "Venda de produtos - 3x sem juros",
                required = false
            )
            @RequestParam(required = false) String solicitacaoPagador) {
        PixParcelado cobv = service.authorizeParcelado(chave, valorTotal, qtdParcelas, solicitacaoPagador);
        return ResponseEntity.status(HttpStatus.CREATED).body(cobv);
    }
}