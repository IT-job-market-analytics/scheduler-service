package com.example.schedulerservice.service;

import com.example.schedulerservice.dto.AnalyticsBuilderServiceTaskDto;
import com.example.schedulerservice.rabbitmq.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AnalyticsBuilderServiceTask {
    @Value("${rabbitmq.routingKey.analyticsBuilder}")
    private String analyticsBuilderKey;

    private final Producer producer;

    public AnalyticsBuilderServiceTask(Producer producer) {
        this.producer = producer;
    }

    public void send(AnalyticsBuilderServiceTaskDto task) {
        log.info("Sending message with class object -> " + task.getClass().getName()
                + " // routingKey -> " + analyticsBuilderKey);
        producer.sendMessage(analyticsBuilderKey, task);
        log.info("Sent message with object -> " + task);
    }
}
