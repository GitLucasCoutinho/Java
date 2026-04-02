package com.ocooldev.pix.ms_simulador_pix.infrastructure.messaging;

import com.ocooldev.pix.ms_simulador_pix.infrastructure.event.PixTransactionEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PixEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE = "pix.events";
    private static final String ROUTING_KEY_PREFIX = "pix.transaction.";

    public PixEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishTransactionCreated(String txid, String chave, String valor) {
        PixTransactionEvent event = PixTransactionEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType("CREATED")
                .txid(txid)
                .chave(chave)
                .valor(valor)
                .timestamp(LocalDateTime.now())
                .description("Nova transação Pix criada")
                .build();
        publish(event, "created");
    }

    public void publishTransactionConfirmed(String txid, String chave, String valor) {
        PixTransactionEvent event = PixTransactionEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType("CONFIRMED")
                .txid(txid)
                .chave(chave)
                .valor(valor)
                .timestamp(LocalDateTime.now())
                .description("Transação Pix confirmada/liquidada")
                .build();
        publish(event, "confirmed");
    }

    public void publishTransactionRefunded(String txid, String valor) {
        PixTransactionEvent event = PixTransactionEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType("REFUNDED")
                .txid(txid)
                .valor(valor)
                .timestamp(LocalDateTime.now())
                .description("Transação Pix devolvida/estornada")
                .build();
        publish(event, "refunded");
    }

    private void publish(PixTransactionEvent event, String eventType) {
        String routingKey = ROUTING_KEY_PREFIX + eventType;
        rabbitTemplate.convertAndSend(EXCHANGE, routingKey, event);
    }
}
