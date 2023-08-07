package com.example.schedulerservice.rabbitmq;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StartTestConnection {

    private final RabbitTemplate template;
    private final ApplicationContext context;

    public StartTestConnection(RabbitTemplate template, ApplicationContext context) {
        this.template = template;
        this.context = context;
    }

    @PostConstruct
    public void testConnect() {
        log.info("\u001B[34m" + "Test connection @PostConstruct start" + "\u001B[0m");
        try {
            template.convertAndSend("Fake message", "Fake queue");
        } catch (AmqpException e) {
            log.info("\u001B[31m" + "Exception rabbitmq -> " + e.getMessage() + "\u001B[0m");
            log.info("\u001B[34m" + "Test connection @PostConstruct end -> stop application" + "\u001B[0m");

            int exitCode = SpringApplication.exit(context, () -> 13);
            System.exit(exitCode);
        }
    }
}
