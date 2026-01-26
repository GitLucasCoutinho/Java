package com.ocooldev.pix.ms_simulador_pix.repository;

import com.ocooldev.pix.ms_simulador_pix.model.PixTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixTransactionRepository extends JpaRepository<PixTransaction, String> {
}