package com.ocooldev.pix.gateway.config;

/**
 * Gateway Config - Desabilitado
 * Este arquivo era para configuração do API Gateway (Spring Cloud Gateway)
 * que foi removido do projeto para simplicidade do simulador PIX
 *
 * Para usar em uma arquitetura de microserviços com API Gateway,
 * adicione novamente spring-cloud-starter-gateway no pom.xml
 */

/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            String remoteAddr = exchange.getRequest().getRemoteAddress() != null
                ? exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
                : "unknown";

            return Mono.just(authHeader != null ? authHeader : remoteAddr);
        };
    }
}
*/
