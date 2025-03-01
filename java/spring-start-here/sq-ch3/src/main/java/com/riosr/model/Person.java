package com.riosr.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Person {

    private String name;

    private final Parrot parrot;

    public Person(@Qualifier("parrot1") Parrot parrot) {
        this.parrot = parrot;
    }

    @PostConstruct
    public void init() {
        this.name = "Ella";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parrot getParrot() {
        return parrot;
    }
}
