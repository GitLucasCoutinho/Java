package com.ocooldev.pix.ms_simulador_pix.infrastructure.repository;

import com.ocooldev.pix.ms_simulador_pix.domain.model.PixTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositório para PixTransaction
public interface PixTransactionRepository extends JpaRepository<PixTransaction, String> {}

