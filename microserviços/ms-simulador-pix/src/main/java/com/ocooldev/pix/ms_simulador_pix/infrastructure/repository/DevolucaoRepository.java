package com.ocooldev.pix.ms_simulador_pix.infrastructure.repository;

import com.ocooldev.pix.ms_simulador_pix.domain.model.Devolucao;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório para Devolucao
public interface DevolucaoRepository extends JpaRepository<Devolucao, String> {}
