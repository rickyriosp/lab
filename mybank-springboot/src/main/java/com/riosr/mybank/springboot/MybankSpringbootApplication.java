package com.riosr.mybank.springboot;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class MybankSpringbootApplication {

	@Bean
	public InitializingBean runner(DataSource dataSource) {
		return () -> System.out.println("The data source is: " + dataSource);
	}

	public static void main(String[] args) {
		SpringApplication.run(MybankSpringbootApplication.class, args);
	}

}
