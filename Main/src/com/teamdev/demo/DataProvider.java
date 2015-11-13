package com.teamdev.demo;


import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

public final class DataProvider {

    public static List<Category> getCategories(String fileName) {
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

    public static DefaultMutableTreeNode createRootTreeNode(List<Category> categories) {
        DefaultMutableTreeNode treeRoot = new DefaultMutableTreeNode("Categories");
        for (Category category : categories) {
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(category.getName());
            for (int j = 0; j < category.getSampleInfo().size(); j++) {
                treeNode.add(new DefaultMutableTreeNode(category.getSampleInfo().get(j).getName()));
            }
            treeRoot.add(treeNode);
        }
        return treeRoot;
    }

    public static String getSourceCode(String exampleName) {
        StringBuilder result = new StringBuilder("");
        try {
            FileReader fileReader = new FileReader(exampleName + ".java");
            int ch;
            while ((ch = fileReader.read()) != -1) {
                result.append((char) ch);
            }
            fileReader.close();
        } catch (Exception e) {
            throw new IllegalArgumentException("No such example: " + e);
        }
        return result.toString();
    }
}