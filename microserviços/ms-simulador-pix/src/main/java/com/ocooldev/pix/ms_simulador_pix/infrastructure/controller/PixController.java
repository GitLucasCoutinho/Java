package com.ocooldev.pix.ms_simulador_pix.infrastructure.controller;

import com.ocooldev.pix.ms_simulador_pix.domain.model.PixTransaction;
import com.ocooldev.pix.ms_simulador_pix.domain.service.PixService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Define que esta classe expõe endpoints REST
@RequestMapping("/pix") // Todos os endpoints começam com /pix
public class PixController {

    private final PixService service;

    // Construtor com injeção de dependência do serviço PixService
    public PixController(PixService service) {
        this.service = service;
    }

    // Endpoint para autorizar uma nova transação Pix
    // Exemplo: POST /pix/authorize?chave=teste@pix.com&valor=100.00&solicitacaoPagador=Pedido123
    @PostMapping("/authorize")
    public ResponseEntity<PixTransaction> authorize(@RequestParam String chave,
                                                    @RequestParam String valor,
                                                    @RequestParam(required = false) String solicitacaoPagador) {
        return ResponseEntity.ok(service.authorize(chave, valor, solicitacaoPagador));
    }

    // Endpoint para consultar uma transação pelo txid
    // Exemplo: GET /pix/transaction/abc123
    @GetMapping("/transaction/{txid}")
    public ResponseEntity<PixTransaction> get(@PathVariable String txid) {
        return service.getTransaction(txid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para listar todas as transações Pix
    // Exemplo: GET /pix/transactions
    @GetMapping("/transactions")
    public List<PixTransaction> list() {
        return service.listTransactions();
    }

    // Endpoint para estornar/refundar uma transação
    // Exemplo: POST /pix/refund/abc123
    @PostMapping("/refund/{txid}")
    public ResponseEntity<PixTransaction> refund(@PathVariable String txid) {
        return service.refund(txid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}