package com.teamdev.demo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Categories")
public class Categories {

    private List<Category> category;

    public void setCategories(List<Category> categoryList) {
        this.category = categoryList;
    }

    public List<Category> getCategories() {
        return category;
    }

    public Categories() {
    }
}
