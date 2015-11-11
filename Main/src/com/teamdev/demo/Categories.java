package com.teamdev.demo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "Categories")
public class Categories {

    private ArrayList<Category> category;

    public void setCategories(ArrayList<Category> categoryList) {
        this.category = categoryList;
    }

    public ArrayList<Category> getCategories() {
        return category;
    }

    public Categories() {
    }
}
