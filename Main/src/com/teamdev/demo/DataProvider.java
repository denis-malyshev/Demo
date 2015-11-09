package com.teamdev.demo;



import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;
import java.util.ArrayList;

public final class DataProvider {


    private static String getSampleDescription(File sample) {
        StringBuilder buff = new StringBuilder("");
        try {
            FileReader fileReader = new FileReader(sample);
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
            ArrayList<SampleInfo> sampleInfos = new ArrayList<>();
            for (int j = 0; j < temp.length; j++) {
                StringBuilder name = new StringBuilder(temp[j].getName().replace(".txt", ""));
                sampleInfos.add(new SampleInfo(name.toString(), getSampleDescription(temp[i])));
            }
            categories.add(new Category(files[i].getName(), sampleInfos));
        }
        return categories;
    }

    public static DefaultMutableTreeNode createRootTreeNode(ArrayList<Category> categories) {
        DefaultMutableTreeNode treeRoot = new DefaultMutableTreeNode("Categories");
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(category.getName());
            for (int j = 0; j < category.getSampleInfo().size(); j++) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(category.getSampleInfo().get(j).getName());
                treeNode.add(node);
            }
            treeRoot.add(treeNode);
        }
        return treeRoot;
    }

    private ArrayList<DefaultMutableTreeNode> createTreeNodesFromCategories(ArrayList<Category> categories) {
        final ArrayList<DefaultMutableTreeNode> categoriesNodes=new ArrayList<>();
        for (Category category : categories) {
           categoriesNodes.add(new DefaultMutableTreeNode(category.getName()));
        }
        return categoriesNodes;
    }

    /*private DefaultMutableTreeNode createRootTreeNode(ArrayList<DefaultMutableTreeNode> treeNodes) {
        final DefaultMutableTreeNode rootTreeNode=new DefaultMutableTreeNode("rootTreeNode");
        treeNodes.forEach(rootTreeNode::add);
        return rootTreeNode;
    }*/

    public static String getSourceCode(String exampleName) {
        StringBuilder result =new StringBuilder("");
        try {
            FileReader fileReader = new FileReader("Samples\\src\\com\\teamdev\\samples\\" + exampleName + ".java");
            int ch;
            while ((ch = fileReader.read()) != -1) {
                result.append((char) ch);
            }
            fileReader.close();
        } catch (Exception e) {
            throw new IllegalArgumentException("No such example: "+e);
        }
        return result.toString();
    }
}