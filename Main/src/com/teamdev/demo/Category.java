package com.teamdev.demo;



import java.util.ArrayList;

public class Category {
    private final String name;
    private final ArrayList<SampleInfo> sampleInfo;

    public Category(String name, ArrayList<SampleInfo> sampleInfo) {
        this.name = name;
        this.sampleInfo = sampleInfo;
    }

    public String getName() {
        return name;
    }

    public ArrayList<SampleInfo> getSampleInfo() {
        return sampleInfo;
    }
}
