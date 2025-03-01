package com.riosr;

import com.riosr.context.ApplicationConfiguration;
import com.riosr.model.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");

        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        Parrot parrot = new Parrot();
        parrot.setName("Ricky");
        Supplier<Parrot> supplier = () -> parrot;
        context.registerBean("parrot1", Parrot.class, supplier, bc -> bc.setPrimary(true));

        Parrot p = context.getBean(Parrot.class);
        System.out.println(p.getName());

        String s = context.getBean(String.class);
        System.out.println(s);

        Integer i = context.getBean(Integer.class);
        System.out.println(i);
    }
}
