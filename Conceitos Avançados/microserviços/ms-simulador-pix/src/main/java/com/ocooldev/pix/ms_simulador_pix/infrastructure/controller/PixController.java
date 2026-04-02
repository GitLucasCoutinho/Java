package com.ocooldev.pix.ms_simulador_pix.infrastructure.controller;

import com.ocooldev.pix.ms_simulador_pix.domain.model.PixTransaction;
import com.ocooldev.pix.ms_simulador_pix.domain.service.PixService;
import com.ocooldev.pix.ms_simulador_pix.infrastructure.dto.CreatePixTransactionRequest;
import com.ocooldev.pix.ms_simulador_pix.infrastructure.dto.PixTransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pix")
@Validated
@Tag(name = "Transações Pix", description = "Endpoints para gerenciar transações Pix (cobranças, pagamentos e consultas)")
public class PixController {

    private final PixService service;

    public PixController(PixService service) {
        this.service = service;
    }

    @Operation(
        summary = "Criar nova transação Pix",
        description = "Cria uma nova cobrança Pix com valor, chave Pix e mensagem opcional. Retorna o txid e detalhes da transação. Suporta idempotência via header Idempotency-Key."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transação criada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PixTransactionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Parâmetros inválidos ou chave Pix duplicada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/authorize")
    public ResponseEntity<PixTransactionResponse> authorize(
            @Valid @RequestBody CreatePixTransactionRequest request,
            @Parameter(
                name = "Idempotency-Key",
                description = "Chave única para garantir idempotência. Se a mesma chave for enviada duas vezes, a segunda tentativa retornará a mesma transação. Use um UUID.",
                example = "123e4567-e89b-12d3-a456-426614174000"
            )
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {
        PixTransaction tx = service.authorize(
            request.getChave(),
            request.getValor(),
            request.getSolicitacaoPagador(),
            idempotencyKey
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(tx));
    }

    @Operation(
        summary = "Consultar detalhes de uma transação Pix",
        description = "Recupera todos os detalhes de uma transação Pix específica utilizando seu identificador único (txid)."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalhes da transação encontrados",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PixTransactionResponse.class))),
        @ApiResponse(responseCode = "404", description = "Transação não encontrada")
    })
    @GetMapping("/transaction/{txid}")
    public ResponseEntity<PixTransactionResponse> get(
            @Parameter(
                name = "txid",
                description = "Identificador único da transação Pix (32 caracteres hexadecimais gerados automaticamente)",
                example = "abc123def456ghi789jkl012mno345",
                required = true
            )
            @PathVariable String txid) {
        return service.getTransaction(txid)
                .map(tx -> ResponseEntity.ok(mapToResponse(tx)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Listar todas as transações Pix",
        description = "Recupera a lista completa de todas as transações Pix cadastradas no simulador."
    )
    @ApiResponse(responseCode = "200", description = "Lista de transações recuperada com sucesso",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PixTransactionResponse.class)))
    @GetMapping("/transactions")
    public ResponseEntity<List<PixTransactionResponse>> list() {
        List<PixTransaction> transactions = service.listTransactions();
        return ResponseEntity.ok(transactions.stream().map(this::mapToResponse).toList());
    }

    @Operation(
        summary = "Confirmar/Liquidar uma transação Pix",
        description = "Marca uma transação como concluída/liquidada. Simula o pagamento de uma cobrança Pix pelo pagador."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transação liquidada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Transação não encontrada")
    })
    @PostMapping("/refund/{txid}")
    public ResponseEntity<PixTransactionResponse> refund(
            @Parameter(
                name = "txid",
                description = "Identificador da transação a ser liquidada",
                example = "abc123def456ghi789jkl012mno345",
                required = true
            )
            @PathVariable String txid) {
        return service.refund(txid)
                .map(tx -> ResponseEntity.ok(mapToResponse(tx)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Gerar QR Code para uma transação Pix",
        description = "Gera e retorna um QR Code (imagem PNG) contendo o payload Pix da transação. O QR Code pode ser lido por apps de pagamento para realizar a transferência."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "QR Code gerado com sucesso (imagem PNG)"),
        @ApiResponse(responseCode = "400", description = "Erro ao gerar QR Code"),
        @ApiResponse(responseCode = "404", description = "Transação não encontrada")
    })
    @GetMapping("/transaction/{txid}/qrcode")
    public ResponseEntity<byte[]> getQRCode(
            @Parameter(
                name = "txid",
                description = "Identificador da transação para gerar o QR Code",
                example = "abc123def456ghi789jkl012mno345",
                required = true
            )
            @PathVariable String txid) {
        try {
            byte[] qrCode = service.generateQRCode(txid);
            return ResponseEntity.ok()
                    .header("Content-Type", "image/png")
                    .body(qrCode);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private PixTransactionResponse mapToResponse(PixTransaction tx) {
        return PixTransactionResponse.builder()
                .txid(tx.getTxid())
                .chave(tx.getChave())
                .valor(tx.getValor().getOriginal())
                .status(tx.getStatus().name())
                .dataCriacao(tx.getCalendario().getCriacao())
                .tempoExpiracao(tx.getCalendario().getExpiracao())
                .dataLiquidacao(tx.getHorario() != null ? tx.getHorario().getLiquidacao() : null)
                .solicitacaoPagador(tx.getSolicitacaoPagador())
                .build();
    }
}