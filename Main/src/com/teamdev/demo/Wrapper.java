package com.teamdev.demo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Wrapper {

    public static Wrapper wrapper;
    @XmlElement
    private ArrayList<Category> categories;

    public Wrapper() {
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
}
