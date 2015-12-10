package com.teamdev.demo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"name", "sampleInfo"})
public class Category {
    @XmlElement
    private String name;

    @XmlElement
    @XmlElementWrapper(name = "Samples")
    private List<SampleInfo> sampleInfo;

    public Category() {
    }

    public Category(String name, List<SampleInfo> sampleInfo) {
        this.name = name;
        this.sampleInfo = new ArrayList<>(sampleInfo);
    }

    public String getName() {
        return name;
    }

    public List<SampleInfo> getSampleInfo() {
        return new ArrayList<>(sampleInfo);
    }
}
