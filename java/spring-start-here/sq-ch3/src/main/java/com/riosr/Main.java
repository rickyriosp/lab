package com.riosr;

import com.riosr.context.ApplicationConfiguration;
import com.riosr.model.Parrot;
import com.riosr.model.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");

        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        Person person = context.getBean(Person.class);
        //Parrot parrot = context.getBean(Parrot.class);

        System.out.println("Person: " + person.getName());
        //System.out.println("Parrot: " + parrot.getName());

        System.out.println("Person's parrot: " + person.getParrot());
    }
}
