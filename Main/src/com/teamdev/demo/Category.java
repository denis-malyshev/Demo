package com.teamdev.demo;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlRootElement
@XmlType(propOrder ={"name","sampleInfo"})
public class Category {
    @XmlElement
    private String name;

    @XmlElement
    @XmlElementWrapper(name = "Samples")
    private ArrayList<SampleInfo> sampleInfo;

    public Category() {
    }

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
