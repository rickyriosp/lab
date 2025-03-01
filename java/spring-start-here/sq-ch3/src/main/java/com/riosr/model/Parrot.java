package com.riosr.model;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Parrot {

    private String name;

    public Parrot() {
        System.out.println("Parrot created");
    }

    @PostConstruct
    public void init() {
        //this.name = "Kiki";
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Parrot destroyed");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Parrot : " + name;
    }
}
