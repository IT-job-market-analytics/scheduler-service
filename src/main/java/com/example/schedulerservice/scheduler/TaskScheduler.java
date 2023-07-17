package com.example.schedulerservice.scheduler;

import com.example.schedulerservice.dto.AnalyticsBuilderServiceTaskDto;
import com.example.schedulerservice.dto.VacancyImportScheduledTaskDto;
import com.example.schedulerservice.rabbitmq.Producer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class TaskScheduler {

    private Producer producer;

    public TaskScheduler(Producer producer) {
        this.producer = producer;
    }

    @Value("${rabbitmq.routingKey.vacancyImport}")
    private String vacancyImportKey;

    @Value("${rabbitmq.routingKey.analyticsBuilder}")
    private String analyticsBuilderKey;

    @Value("${vacancyImportScheduledTaskDto.pageSize}")
    private int pageSize;

    @Value("${vacancyImportScheduledTaskDto.pageNumber}")
    private int pageNumber;

    @Value("#{'${queries}'.split(',')}")
    private List<String> queries;

    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

    @PostConstruct
    public void foo() {

        ScheduledFuture<?> futureVacancyImport = executor.scheduleWithFixedDelay(() -> {

            for (String query : queries) {
                for (int i = 0; i < pageNumber; i++) {
                    VacancyImportScheduledTaskDto vacancyImportScheduledTaskDto = new VacancyImportScheduledTaskDto(pageSize, i, query);
                    producer.sendMessage(vacancyImportKey, vacancyImportScheduledTaskDto);
                }
            }
        }, 0, 1, TimeUnit.HOURS);

        ScheduledFuture<?> futureAnalyticsBuilder = executor.scheduleWithFixedDelay(() -> {

            AnalyticsBuilderServiceTaskDto analyticsBuilderServiceTaskDto = new AnalyticsBuilderServiceTaskDto();
            producer.sendMessage(analyticsBuilderKey, analyticsBuilderServiceTaskDto);
        }, 0, 1, TimeUnit.HOURS);
    }
}
