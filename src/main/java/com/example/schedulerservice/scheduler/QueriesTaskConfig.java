package com.example.schedulerservice.scheduler;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "application")
public class QueriesTaskConfig {
        private List<String> queries;
}
