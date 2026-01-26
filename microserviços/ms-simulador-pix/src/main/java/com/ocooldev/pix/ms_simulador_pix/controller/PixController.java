package com.ocooldev.pix.ms_simulador_pix.controller;

import com.ocooldev.pix.ms_simulador_pix.model.PixTransaction;
import com.ocooldev.pix.ms_simulador_pix.service.PixService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pix")
public class PixController {

    private final PixService pixService;

    public PixController(PixService pixService) {
        this.pixService = pixService;
    }

    @PostMapping("/authorize")
    public ResponseEntity<PixTransaction> authorize(@RequestParam String chave,
                                                    @RequestParam String valorOriginal) {
        return ResponseEntity.ok(pixService.authorize(chave, valorOriginal));
    }

    @GetMapping("/transaction/{txid}")
    public ResponseEntity<PixTransaction> getTransaction(@PathVariable String txid) {
        return pixService.getTransaction(txid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<PixTransaction>> listTransactions() {
        return ResponseEntity.ok(pixService.listTransactions());
    }

    @PostMapping("/refund/{txid}")
    public ResponseEntity<PixTransaction> refund(@PathVariable String txid) {
        return pixService.refund(txid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}