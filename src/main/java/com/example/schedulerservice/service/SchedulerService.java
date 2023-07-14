package com.example.schedulerservice.service;

import com.example.schedulerservice.dto.AnalyticsBuilderServiceTaskDto;
import com.example.schedulerservice.dto.VacancyImportScheduledTaskDto;
import com.example.schedulerservice.rabbitmq.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SchedulerService {

    @Value("${rabbitmq.routingKey.analyticsBuilder}")
    private String analyticsBuilderKey;

    @Value("${rabbitmq.routingKey.vacancyImport}")
    private String vacancyImportKey;

    private Producer producer;

    public SchedulerService(Producer producer) {
        this.producer = producer;
    }

    public void send(Object object) {
        if (object instanceof AnalyticsBuilderServiceTaskDto) {
            log.info("Sending message with class object -> " + object.getClass().getName()
                    +  " // routingKey -> " + analyticsBuilderKey);
            producer.sendMessage(analyticsBuilderKey, (VacancyImportScheduledTaskDto) object);
        }

        if (object instanceof VacancyImportScheduledTaskDto) {
            log.info("Sending message with class object -> " + object.getClass().getName()
                    +  " // routingKey -> " + vacancyImportKey);
            producer.sendMessage(vacancyImportKey, (VacancyImportScheduledTaskDto) object);
        }

        log.info("Sent message with object -> " + object);
    }
}
