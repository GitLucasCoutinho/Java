package com.ocooldev.pix.ms_simulador_pix.infrastructure.controller;

import com.ocooldev.pix.ms_simulador_pix.domain.model.PixParcelado;
import com.ocooldev.pix.ms_simulador_pix.domain.service.PixParceladoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pix/parcelado")
public class PixParceladoController {

    private final PixParceladoService service;

    public PixParceladoController(PixParceladoService service) {
        this.service = service;
    }

    @PostMapping("/authorize")
    public ResponseEntity<PixParcelado> authorize(@RequestParam String chave,
                                                  @RequestParam String valorTotal,
                                                  @RequestParam int qtdParcelas,
                                                  @RequestParam(required=false) String solicitacaoPagador) {
        return ResponseEntity.ok(service.authorizeParcelado(chave, valorTotal, qtdParcelas, solicitacaoPagador));
    }
}