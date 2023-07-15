package com.example.schedulerservice.scheduler;

import com.example.schedulerservice.dto.AnalyticsBuilderServiceTaskDto;
import com.example.schedulerservice.dto.VacancyImportScheduledTaskDto;
import com.example.schedulerservice.service.AnalyticsBuilderServiceTask;
import com.example.schedulerservice.service.VacancyImportServiceTask;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class TaskScheduler {
    private VacancyImportServiceTask vacancyImportServiceTask;
    private AnalyticsBuilderServiceTask analyticsBuilderServiceTask;

    public TaskScheduler(VacancyImportServiceTask vacancyImportServiceTask, AnalyticsBuilderServiceTask analyticsBuilderServiceTask) {
        this.vacancyImportServiceTask = vacancyImportServiceTask;
        this.analyticsBuilderServiceTask = analyticsBuilderServiceTask;
    }

    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

    ScheduledFuture<?> futureVacancyImport = executor.scheduleWithFixedDelay(new Runnable() {
        @Override
        public void run() {

            for (int i = 0; i < 20; i++) {
                VacancyImportScheduledTaskDto vacancyImportScheduledTaskDto = new VacancyImportScheduledTaskDto(100, i, "Java");
                vacancyImportServiceTask.send(vacancyImportScheduledTaskDto);
            }
        }
    }, 0, 10, TimeUnit.SECONDS);

    ScheduledFuture<?> futureAnalyticsBuilder = executor.scheduleWithFixedDelay(new Runnable() {
        @Override
        public void run() {

            for (int i = 0; i < 20; i++) {
                System.out.println("Analytics builder = " + i);
            }
        }
    }, 0, 10, TimeUnit.SECONDS);

//    ScheduledFuture<?> future3 = executor.scheduleWithFixedDelay(new Runnable() {
//        @Override
//        public void run() {
//
//            for (int i = 1; i < 20; i += 2) {
//                System.out.println("i = " + i);
//            }
//            System.out.println("finish #3  == " + LocalDateTime.now());
//        }
//    }, 0, 10, TimeUnit.SECONDS);
//
//    ScheduledFuture<?> future4 = executor.scheduleWithFixedDelay(new Runnable() {
//        @Override
//        public void run() {
//
//            for (int i = 1; i < 20; i += 2) {
//                System.out.println("j = " + i);
//            }
//            System.out.println("finish #4  == " + LocalDateTime.now());
//        }
//    }, 0, 10, TimeUnit.SECONDS);

}
