package com.teamdev.demo;



import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Example> examples;

    public Category(String name, ArrayList<Example> examples) {
        this.name = name;
        this.examples = examples;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Example> getExamples() {
        return examples;
    }
}
