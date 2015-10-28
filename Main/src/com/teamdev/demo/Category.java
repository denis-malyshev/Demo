package com.teamdev.demo;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlRootElement(name = "category")
@XmlType(propOrder = {"name", "examples"})
public class Category {
    private String name;
    private ArrayList<Example> examples;

    public void setName(String name) {
        this.name = name;
    }

    public void setExamples(ArrayList<Example> examples) {
        this.examples = examples;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Example> getExamples() {
        return examples;
    }
}
