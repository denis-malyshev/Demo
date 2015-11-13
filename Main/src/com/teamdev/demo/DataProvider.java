package com.teamdev.demo;


import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;
import java.util.jar.JarFile;

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
            FileReader fileReader = new FileReader("src/com/teamdev/samples/"+exampleName + ".java");
            JarFile jarFile=new JarFile("run.jar");
            System.out.println(jarFile.getJarEntry("data.xml").getClass());
            jarFile.close();
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