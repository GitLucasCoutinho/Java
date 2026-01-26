package com.ocooldev.pix.ms_simulador_pix.infrastructure.controller;

import com.ocooldev.pix.ms_simulador_pix.domain.model.Devolucao;
import com.ocooldev.pix.ms_simulador_pix.domain.service.DevolucaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pix/devolucao")
public class PixDevolucaoController {

    private final DevolucaoService service;

    public PixDevolucaoController(DevolucaoService service) {
        this.service = service;
    }

    @PostMapping("/solicitar")
    public ResponseEntity<Devolucao> solicitar(@RequestParam String txid, @RequestParam String valor) {
        return ResponseEntity.ok(service.solicitarDevolucao(txid, valor));
    }

    @PostMapping("/efetivar/{id}")
    public ResponseEntity<Devolucao> efetivar(@PathVariable String id) {
        return service.efetivarDevolucao(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}