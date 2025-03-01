package com.riosr.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riosr.ApplicationLauncher;
import com.riosr.service.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
@PropertySource("classpath:/application.properties")
public class ApplicationConfiguration {

//    @Bean
//    public TransactionService transactionService() {
//        return new TransactionService();
//    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
