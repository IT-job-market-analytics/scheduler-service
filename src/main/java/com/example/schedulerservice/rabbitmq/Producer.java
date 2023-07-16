package com.example.schedulerservice.rabbitmq;

import com.example.schedulerservice.dto.AnalyticsBuilderServiceTaskDto;
import com.example.schedulerservice.dto.VacancyImportScheduledTaskDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {

    private final RabbitTemplate template;

    public Producer(RabbitTemplate template) {
        this.template = template;
    }

    public void sendMessage(String routingKey, AnalyticsBuilderServiceTaskDto task) {
        template.convertAndSend(routingKey, task);
    }

    public void sendMessage(String routingKey, VacancyImportScheduledTaskDto task) {
        template.convertAndSend(routingKey, task);
    }

}
