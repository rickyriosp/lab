package com.riosr.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riosr.ApplicationLauncher;
import com.riosr.service.InvoiceService;
import com.riosr.service.UserService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
@PropertySource("classpath:/application.properties")
@PropertySource(value = "classpath:/application-${spring.profiles.active}.properties"
                    , ignoreResourceNotFound = true)
public class ApplicationConfiguration {

//    @Bean
//    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public UserService userService() {
//        return new UserService();
//    }

//    @Bean
//    public InvoiceService invoiceService(UserService userService) {
//        return new InvoiceService(userService);
//    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
