package com.riosr.context;

import com.riosr.Main;
import com.riosr.model.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackageClasses = Main.class)
public class ApplicationConfiguration {

    @Bean
    public String hello() {
        return "Hello";
    }

    @Bean
    public Integer ten() {
        return 10;
    }
}
