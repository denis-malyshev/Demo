package com.teamdev.demo;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;

public class XmlParser {

    public static ArrayList<Category> getCategories(String fileName) {
        JAXBContext context;
        Categories categories;
        try {
            context = JAXBContext.newInstance(Categories.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            categories = (Categories) unmarshaller.unmarshal(new File(fileName));
            return categories.getCategories();
        } catch (JAXBException e) {
            throw new IllegalArgumentException("No such file");
        }
    }
}
