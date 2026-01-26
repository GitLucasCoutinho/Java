package com.ocooldev.pix.ms_simulador_pix.infrastructure.repository;

import com.ocooldev.pix.ms_simulador_pix.domain.model.PixParcelado;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório para PixParcelado
public interface PixParceladoRepository extends JpaRepository<PixParcelado, String> {}
