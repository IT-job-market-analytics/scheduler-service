package com.example.schedulerservice.raabitmq;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

//    @Value("${rabbitmq.queue.producer.nameAnalytics}")
//    private String queueAnalytics;
//
//    @Value("${rabbitmq.routing.key }")
//    private String routingKey;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setExchange(exchange);
        return template;
    }
}
