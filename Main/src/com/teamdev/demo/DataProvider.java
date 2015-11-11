package com.teamdev.demo;


import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;
import java.util.ArrayList;

public final class DataProvider {

    public static DefaultMutableTreeNode createRootTreeNode(ArrayList<Category> categories) {
        DefaultMutableTreeNode treeRoot = new DefaultMutableTreeNode("Categories");
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(category.getName());
            for (int j = 0; j < category.getSampleInfo().size(); j++) {
                treeNode.add(new DefaultMutableTreeNode(category.getSampleInfo().get(j).getName()));
            }
            treeRoot.add(treeNode);
        }
        return treeRoot;
    }

    private ArrayList<DefaultMutableTreeNode> createTreeNodesFromCategories(ArrayList<Category> categories) {
        final ArrayList<DefaultMutableTreeNode> categoriesNodes = new ArrayList<>();
        for (Category category : categories) {
            categoriesNodes.add(new DefaultMutableTreeNode(category.getName()));
        }
        return categoriesNodes;
    }

    private ArrayList<DefaultMutableTreeNode> createTreeNodeFromSamples(ArrayList<SampleInfo> samples) {
        final ArrayList<DefaultMutableTreeNode> samplesNode = new ArrayList<>();
        for (SampleInfo sample : samples) {
            samplesNode.add(new DefaultMutableTreeNode(sample.getName()));
        }
        return samplesNode;
    }

    /*public DefaultMutableTreeNode createRootTreeNode(String name) {
        DefaultMutableTreeNode root=new DefaultMutableTreeNode(name);
        return root;
    }*/

    public static String getSourceCode(String exampleName) {
        StringBuilder result = new StringBuilder("");
        try {
            FileReader fileReader = new FileReader("target/src/com/teamdev/samples/" + exampleName + ".java");
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