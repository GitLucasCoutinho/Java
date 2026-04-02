package com.ocooldev.pix.ms_simulador_pix.infrastructure.controller;

import com.ocooldev.pix.ms_simulador_pix.domain.service.WebhookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
@Tag(name = "Webhooks", description = "Endpoints para simular notificações de eventos do Pix")
public class WebhookController {

    private final WebhookService service;

    public WebhookController(WebhookService service) {
        this.service = service;
    }

    @Operation(
        summary = "Simular envio de webhook de notificação",
        description = "Simula o envio de uma notificação/webhook quando um evento Pix ocorre (ex: transação concluída, devolução processada). No Pix real, webhooks são enviados para URLs pré-configuradas no banco."
    )
    @ApiResponse(responseCode = "200", description = "Webhook enviado com sucesso (simulado)")
    @PostMapping("/enviar")
    public ResponseEntity<String> enviar(
            @Parameter(
                name = "txid",
                description = "Identificador da transação que disparou o evento",
                example = "abc123def456ghi789jkl012mno345",
                required = true
            )
            @RequestParam String txid,

            @Parameter(
                name = "status",
                description = "Status/evento que disparou a notificação (ex: CONCLUIDA, DEVOLVIDA, EXPIRADA)",
                example = "CONCLUIDA",
                required = true
            )
            @RequestParam String status) {
        String resultado = service.enviarNotificacao(txid, status);
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }
}