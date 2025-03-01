package com.riosr.context;

import com.riosr.model.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Parrot parrot() {
        Parrot parrot = new Parrot();
        parrot.setName("Koko");
        return parrot;
    }

    @Bean
    public String hello() {
        return "Hello";
    }

    @Bean
    public Integer ten() {
        return 10;
    }
}
