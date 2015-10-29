package com.teamdev.demo;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public final class DataProvider {

    public static ArrayList<Category> getCategoriesFromXMl() {

        File sourceFile = new File("data.xml");
        JAXBContext jaxbContext;
        if (sourceFile.exists()) {
            try {
                jaxbContext = JAXBContext.newInstance(Wrapper.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                Wrapper.wrapper = (Wrapper) unmarshaller.unmarshal(sourceFile);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        return Wrapper.wrapper.getCategories();
    }

    public static DefaultMutableTreeNode createTree(ArrayList<Category> categories) {
        DefaultMutableTreeNode treeRoot = new DefaultMutableTreeNode("Categories");
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(category.getName());
            for (int j = 0; j < category.getExamples().size(); j++) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(category.getExamples().get(j).getName());
                treeNode.add(node);
            }
            treeRoot.add(treeNode);
        }
        return treeRoot;
    }

    public static void createInstanceByClassName(String className,JPanel container) {
        try {
            Class test=Class.forName("com.teamdev.demo."+className);
            Method method=test.getDeclaredMethod("run",JPanel.class);
            method.setAccessible(true);
            method.invoke(test.newInstance(), container);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static String getSourceCodeFromTxt(String exampleName) {
        String result="";
        try {
            FileReader fileReader=new FileReader(exampleName+".txt");
            int ch;
            while ((ch=fileReader.read())!=-1) {
                result+=(char)ch;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}