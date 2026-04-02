package com.ocooldev.pix.ms_simulador_pix.domain.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class IdempotencyService {

    private final Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(24, TimeUnit.HOURS) // Expira em 24 horas
            .maximumSize(10000)
            .build();

    public boolean isIdempotent(String key, String txid) {
        String existingTxid = cache.getIfPresent(key);
        if (existingTxid != null) {
            return existingTxid.equals(txid);
        }
        cache.put(key, txid);
        return true;
    }
}
