package com.ocooldev.pix.ms_simulador_pix.infrastructure.controller;

import com.ocooldev.pix.ms_simulador_pix.domain.service.WebhookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Define como controlador REST
@RequestMapping("/webhook") // Endpoints começam com /webhook
public class WebhookController {

    private final WebhookService service;

    public WebhookController(WebhookService service) {
        this.service = service;
    }

    // Endpoint para simular envio de notificação
    @PostMapping("/enviar")
    public ResponseEntity<String> enviar(@RequestParam String txid, @RequestParam String status) {
        return ResponseEntity.ok(service.enviarNotificacao(txid, status));
    }
}