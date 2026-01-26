package com.ocooldev.pix.ms_simulador_pix.domain.service;

import org.springframework.stereotype.Service;

@Service
public class WebhookService {
    // Simula envio de notificação
    public String enviarNotificacao(String txid, String status) {
        return "Webhook enviado para txid=" + txid + " com status=" + status;
    }
}