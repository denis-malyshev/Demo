package com.teamdev.demo;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public final class DataProvider {

    private static String fromTxt(File file){
        StringBuilder buff=new StringBuilder("");
        try {
            FileReader fileReader=new FileReader(file);
            int ch;
            while ((ch=fileReader.read())!=-1) {
                buff.append((char)ch);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buff.toString();
    }

    public static ArrayList<Category> getCategoryFromFolders() {
        ArrayList<Category> categories=new ArrayList<Category>();
        File root = new File("Examples");
        File[] files = root.listFiles();
        for (int i = 0; i < files.length; i++) {
            File[] temp=files[i].listFiles();
            ArrayList<Example> examples=new ArrayList<Example>();
            for(int j=0;j<temp.length;j++) {
                StringBuilder name=new StringBuilder(temp[j].getName().replace(".txt",""));
                examples.add(new Example(name.toString(),fromTxt(temp[i])));
            }
            categories.add(new Category(files[i].getName(),examples));
        }
        return categories;
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

    public static void createInstanceByClassName(String className, JPanel container) {
        try {
            Class test = Class.forName("com.teamdev.demo." + className);
            Method method = test.getDeclaredMethod("run", JPanel.class);
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
        String result = "";
        try {
            FileReader fileReader = new FileReader(exampleName + ".txt");
            int ch;
            while ((ch = fileReader.read()) != -1) {
                result += (char) ch;
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