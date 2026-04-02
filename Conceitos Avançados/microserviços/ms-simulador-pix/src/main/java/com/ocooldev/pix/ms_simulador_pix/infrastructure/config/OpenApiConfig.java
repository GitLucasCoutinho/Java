package com.ocooldev.pix.ms_simulador_pix.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API do Simulador Pix - ms-simulador-pix")
                        .version("1.0.0")
                        .description("""
                                Microserviço open source para simulação de transações do Pix (Sistema de Pagamentos Instantâneos Brasileiro).
                                
                                Este simulador permite testar integrações com o Pix sem depender dos sistemas reais dos bancos.
                                
                                **Funcionalidades:**
                                - Criar cobranças Pix com geração automática de QR Codes
                                - Simular pagamentos e liquidações
                                - Solicitar e processar devoluções (estornos)
                                - Gerenciar cobranças parceladas
                                - Suporte a idempotência para evitar duplicação
                                - Webhooks simulados para notificações
                                
                                **Base URL:** http://localhost:8080
                                
                                **Documentação:** Acesse o Swagger UI em /swagger-ui.html
                                """)
                        .contact(new Contact()
                                .name("ocooldev")
                                .url("https://github.com/ocooldev/ms-simulador-pix")
                                .email("contato@ocooldev.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT"))
                );
    }
}
