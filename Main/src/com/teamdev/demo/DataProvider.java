package com.teamdev.demo;


import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;
import java.util.ArrayList;

public final class DataProvider {

    private static String fromTxt(File file) {
        StringBuilder buff = new StringBuilder("");
        try {
            FileReader fileReader = new FileReader(file);
            int ch;
            while ((ch = fileReader.read()) != -1) {
                buff.append((char) ch);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buff.toString();
    }

    public static ArrayList<Category> getCategoryFromFolders() {
        ArrayList<Category> categories = new ArrayList<>();
        File root = new File("Samples/Categories");
        File[] files = root.listFiles();
        for (int i = 0; i < files.length; i++) {
            File[] temp = files[i].listFiles();
            ArrayList<Sample> samples = new ArrayList<>();
            for (int j = 0; j < temp.length; j++) {
                StringBuilder name = new StringBuilder(temp[j].getName().replace(".txt", ""));
                samples.add(new Sample(name.toString(), fromTxt(temp[i])));
            }
            categories.add(new Category(files[i].getName(), samples));
        }
        return categories;
    }

    public static DefaultMutableTreeNode createTree(ArrayList<Category> categories) {
        DefaultMutableTreeNode treeRoot = new DefaultMutableTreeNode("Categories");
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(category.getName());
            for (int j = 0; j < category.getSamples().size(); j++) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(category.getSamples().get(j).getName());
                treeNode.add(node);
            }
            treeRoot.add(treeNode);
        }
        return treeRoot;
    }

    public static String getSourceCodeFromTxt(String exampleName) {
        String result = "";
        try {
            FileReader fileReader = new FileReader("Samples\\source code\\"+exampleName + ".txt");
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