package com.teamdev.demo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder ={"name","description"})
public class SampleInfo {
    @XmlElement
    private String name;
    @XmlElement
    private String description;

    public SampleInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public SampleInfo() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
