package com.riosr.sq_ch10.models;

public class Country {

    private String name;
    private int population;

    public static Country of(String name, int population) {
        Country country = new Country();
        country.name = name;
        country.population = population;
        return country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
