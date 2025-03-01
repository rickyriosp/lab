package com.riosr;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        var context = new AnnotationConfigApplicationContext();
        Parrot parrot = new Parrot();
    }
}
