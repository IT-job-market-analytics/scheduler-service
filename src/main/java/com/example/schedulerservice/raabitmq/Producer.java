package com.example.schedulerservice.raabitmq;

import com.example.schedulerservice.dto.VacancyImportScheduledTaskDto;
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

    public void sendMessage(String routingKey, VacancyImportScheduledTaskDto obj) {
        log.info("Sending message to -> %s // with object -> %s", routingKey, obj);
        template.convertAndSend(routingKey, obj);
        log.info("Sent DONE");
    }

}
