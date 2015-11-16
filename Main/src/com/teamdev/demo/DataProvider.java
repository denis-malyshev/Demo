package com.teamdev.demo;



import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

public final class DataProvider {

    public List<Category> getCategories(String fileName) {
        JAXBContext context;
        Categories categories;
        try {
            context = JAXBContext.newInstance(Categories.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            categories = (Categories) unmarshaller.unmarshal(new File(fileName));
            return categories.getCategories();
        } catch (Exception e) {
            throw new IllegalArgumentException("No such file");
        }
    }

    public DefaultMutableTreeNode createRootTreeNode(List<Category> categories) {
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

    public String getSourceCode(String exampleName) {
        StringBuilder result = new StringBuilder("");
        try {
            ClassLoader loader=getClass().getClassLoader();
            InputStream inputStream=loader.getResourceAsStream("src/com/teamdev/samples/"+exampleName+".java");
            int ch;
            while ((ch = inputStream.read()) != -1) {
                result.append((char) ch);
            }
            inputStream.close();
        } catch (Exception e) {
            throw new IllegalArgumentException("No such example: " + e);
        }
        return result.toString();
    }
}