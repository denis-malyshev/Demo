package com.teamdev.demo;



import java.util.ArrayList;

public class Category {
    private final String name;
    private final ArrayList<SampleInfo> sampleInfos;

    public Category(String name, ArrayList<SampleInfo> sampleInfos) {
        this.name = name;
        this.sampleInfos = sampleInfos;
    }

    public String getName() {
        return name;
    }

    public ArrayList<SampleInfo> getSampleInfos() {
        return sampleInfos;
    }
}
