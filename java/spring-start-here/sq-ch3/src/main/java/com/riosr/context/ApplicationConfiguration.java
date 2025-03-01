package com.riosr.context;

import com.riosr.Main;
import com.riosr.model.Parrot;
import com.riosr.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Main.class)
public class ApplicationConfiguration {

    @Bean
    public Parrot parrot1() {
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }

    @Bean
    public Parrot parrot2() {
        Parrot p = new Parrot();
        p.setName("Miki");
        return p;
    }

//    @Bean
//    public Person person(@Qualifier("parrot2") Parrot parrot2) {
//        Person p =  new Person();
//        p.setName("Ella");
//        //p.setParrot(parrot());
//        p.setParrot(parrot2);
//        return p;
//    }
}
