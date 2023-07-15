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

    private Producer producer;

    public AnalyticsBuilderServiceTask(Producer producer) {
        this.producer = producer;
    }

    public void send(AnalyticsBuilderServiceTaskDto object) {

        log.info("Sending message with class object -> " + object.getClass().getName()
                + " // routingKey -> " + analyticsBuilderKey);
        producer.sendMessage(analyticsBuilderKey, object);
        log.info("Sent message with object -> " + object);
    }
}
