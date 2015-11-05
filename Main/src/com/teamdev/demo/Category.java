package com.teamdev.demo;



import java.util.ArrayList;

public class Category {
    private final String name;
    private final ArrayList<Sample> samples;

    public Category(String name, ArrayList<Sample> samples) {
        this.name = name;
        this.samples = samples;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Sample> getSamples() {
        return samples;
    }
}
