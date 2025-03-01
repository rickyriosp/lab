package com.riosr.sq_ch12.config;

import com.riosr.sq_ch12.MainApplication;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = MainApplication.class)
public class ProjectConfiguration {

    @Value("${custom.datasource.url}")
    private String dbUrl;

    @Value("${custom.datasource.username}")
    private String dbUsername;

    @Value("${custom.datasource.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setConnectionTimeout(1000);
        return dataSource;
    }
}
