package com.example.schedulerservice.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {

    private RabbitTemplate template;

    public Producer(RabbitTemplate template) {
        this.template = template;
    }

    public void sendMessage(String routingKey, Object object) {
        template.convertAndSend(routingKey, object);
    }

}
