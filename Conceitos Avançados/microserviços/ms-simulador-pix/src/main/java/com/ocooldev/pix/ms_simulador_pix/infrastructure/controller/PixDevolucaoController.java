package com.ocooldev.pix.ms_simulador_pix.infrastructure.controller;

import com.ocooldev.pix.ms_simulador_pix.domain.model.Devolucao;
import com.ocooldev.pix.ms_simulador_pix.domain.service.DevolucaoService;
import com.ocooldev.pix.ms_simulador_pix.infrastructure.dto.CreateRefundRequest;
import com.ocooldev.pix.ms_simulador_pix.infrastructure.dto.RefundResponse;
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
@RequestMapping("/pix/devolucao")
@Tag(name = "Devoluções Pix", description = "Endpoints para gerenciar devoluções e estornos de transações Pix")
public class PixDevolucaoController {

    private final DevolucaoService service;

    public PixDevolucaoController(DevolucaoService service) {
        this.service = service;
    }

    @Operation(
        summary = "Solicitar devolução de uma transação Pix",
        description = "Solicita a devolução (estorno) de uma transação Pix. Pode ser devolução total ou parcial do valor. A devolução é processada imediatamente no simulador."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Devolução solicitada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RefundResponse.class))),
        @ApiResponse(responseCode = "400", description = "Parâmetros inválidos (valor maior que o da transação original)"),
        @ApiResponse(responseCode = "404", description = "Transação original não encontrada")
    })
    @PostMapping("/solicitar/{txid}")
    public ResponseEntity<RefundResponse> solicitar(
            @Parameter(
                name = "txid",
                description = "Identificador da transação original que será devolvida",
                example = "abc123def456ghi789jkl012mno345",
                required = true
            )
            @PathVariable String txid,
            @RequestBody(required = false) CreateRefundRequest request) {
        String valor = request != null && request.getValor() != null ? request.getValor() : "0";
        Devolucao dev = service.solicitarDevolucao(txid, valor);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(dev));
    }

    @Operation(
        summary = "Efetivar uma devolução Pix",
        description = "Marca uma devolução solicitada como efetivada/concluída. Simula o processamento do estorno pelo banco."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Devolução efetivada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Devolução não encontrada")
    })
    @PostMapping("/efetivar/{id}")
    public ResponseEntity<RefundResponse> efetivar(
            @Parameter(
                name = "id",
                description = "Identificador único da devolução a ser efetivada",
                example = "d1e2f3g4h5i6j7k8l9m0n1o2p3q4r5s6",
                required = true
            )
            @PathVariable String id) {
        return service.efetivarDevolucao(id)
                .map(dev -> ResponseEntity.ok(mapToResponse(dev)))
                .orElse(ResponseEntity.notFound().build());
    }

    private RefundResponse mapToResponse(Devolucao dev) {
        return RefundResponse.builder()
                .id(dev.getId())
                .txid(dev.getTxid())
                .valor(new java.math.BigDecimal(dev.getValor()))
                .status(dev.getStatus().name())
                .dataHora(dev.getDataHora())
                .build();
    }
}