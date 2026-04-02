package com.ocooldev.pix.ms_simulador_pix.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "pix.events";
    public static final String QUEUE_CREATED = "pix.transaction.created";
    public static final String QUEUE_CONFIRMED = "pix.transaction.confirmed";
    public static final String QUEUE_REFUNDED = "pix.transaction.refunded";

    @Bean
    public DirectExchange pixExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    public Queue pixTransactionCreatedQueue() {
        return new Queue(QUEUE_CREATED, true);
    }

    @Bean
    public Queue pixTransactionConfirmedQueue() {
        return new Queue(QUEUE_CONFIRMED, true);
    }

    @Bean
    public Queue pixTransactionRefundedQueue() {
        return new Queue(QUEUE_REFUNDED, true);
    }

    @Bean
    public Binding bindingCreated(Queue pixTransactionCreatedQueue, DirectExchange pixExchange) {
        return BindingBuilder.bind(pixTransactionCreatedQueue)
                .to(pixExchange)
                .with("pix.transaction.created");
    }

    @Bean
    public Binding bindingConfirmed(Queue pixTransactionConfirmedQueue, DirectExchange pixExchange) {
        return BindingBuilder.bind(pixTransactionConfirmedQueue)
                .to(pixExchange)
                .with("pix.transaction.confirmed");
    }

    @Bean
    public Binding bindingRefunded(Queue pixTransactionRefundedQueue, DirectExchange pixExchange) {
        return BindingBuilder.bind(pixTransactionRefundedQueue)
                .to(pixExchange)
                .with("pix.transaction.refunded");
    }
}
