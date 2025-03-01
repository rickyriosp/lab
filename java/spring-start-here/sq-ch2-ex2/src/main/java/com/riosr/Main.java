package com.riosr;

import com.riosr.context.ApplicationConfiguration;
import com.riosr.model.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");

        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        Parrot parrot = context.getBean(Parrot.class);
        System.out.println(parrot.getName());

        String s = context.getBean(String.class);
        System.out.println(s);

        Integer i = context.getBean(Integer.class);
        System.out.println(i);
    }
}
