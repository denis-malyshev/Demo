package com.teamdev.demo.provider;


import com.teamdev.demo.Categories;
import com.teamdev.demo.Category;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.List;

public final class DataProvider {

    public List<Category> getCategories(String fileName) {
        JAXBContext context;
        Categories categories;
        try {
            ClassLoader loader = getClass().getClassLoader();
            InputStream inputStream = loader.getResourceAsStream(fileName);
            context = JAXBContext.newInstance(Categories.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            categories = (Categories) unmarshaller.unmarshal(inputStream);
            return categories.getCategories();
        } catch (Exception e) {
            try {
                File xmlFile = new File(fileName);
                context = JAXBContext.newInstance(Categories.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                categories = (Categories) unmarshaller.unmarshal(xmlFile);
                return categories.getCategories();
            } catch (Exception ex) {
                throw new IllegalArgumentException("No such file" + ex);
            }
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
            ClassLoader loader = getClass().getClassLoader();
            InputStream inputStream = loader.getResourceAsStream("src/com/teamdev/samples/" + exampleName + ".java");
            int ch;
            while ((ch = inputStream.read()) != -1) {
                result.append((char) ch);
            }
            inputStream.close();
        } catch (Exception e) {
            try {
                FileReader reader = new FileReader("./Samples/src/com/teamdev/samples/" + exampleName + ".java");
                int ch;
                while ((ch = reader.read()) != -1) {
                    result.append((char) ch);
                }
                reader.close();

            } catch (Exception ex) {
                throw new IllegalArgumentException("No such example: " + ex);
            }
        }
        return result.toString();
    }
}