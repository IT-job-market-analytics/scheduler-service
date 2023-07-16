package com.example.schedulerservice.service;

import com.example.schedulerservice.dto.VacancyImportScheduledTaskDto;
import com.example.schedulerservice.rabbitmq.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VacancyImportServiceTask {
    @Value("${rabbitmq.routingKey.vacancyImport}")
    private String vacancyImportKey;

    private final Producer producer;

    public VacancyImportServiceTask(Producer producer) {
        this.producer = producer;
    }

    public void send(VacancyImportScheduledTaskDto object) {
        log.info("Sending message with class object -> " + object.getClass().getName()
                + " // routingKey -> " + vacancyImportKey);
        producer.sendMessage(vacancyImportKey, object);

        log.info("Sent message with object -> " + object);
    }
}
