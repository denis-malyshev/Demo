package com.teamdev.demo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "example")
@XmlType(propOrder = {"name", "description"})
public class Example {
    @XmlElement
    private String name;
    @XmlElement
    private String description;

    public Example(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Example() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
