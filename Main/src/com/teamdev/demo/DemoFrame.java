package com.teamdev.demo;


import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public final class DemoFrame {
    private final JFrame frame;
    private final JPanel leftPanel = new JPanel();
    private final JPanel rightContainer = new JPanel();
    private final JPanel rightDownSubContainer = new JPanel();
    private final JLabel labelAboutExample = new JLabel("Click on example for showing");
    private final JSplitPane mainContainer = new JSplitPane();
    private final JTabbedPane tabbedPane = new JTabbedPane();
    private final JScrollPane leftScrollPane = new JScrollPane(leftPanel);
    private final JPanel preview = new JPanel();
    private final JTextPane source = new JTextPane();
    private final JScrollPane sourceContainer = new JScrollPane(source);
    private final JTree treeOfExample;
    private final SampleProvider sampleProvider;

    public DemoFrame() {
        sampleProvider=new SampleProvider(preview);
        final JavaHighlighter javaHighlighter = new JavaHighlighter(source, sourceContainer);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame();
        frame.setSize(800, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation((dimension.width - frame.getWidth()) / 2, (dimension.height - frame.getHeight()) / 2);
        frame.setTitle("Demo");

        leftPanel.setMinimumSize(new Dimension(100, frame.getHeight()));
        leftPanel.setBackground(Color.white);

        rightContainer.setLayout(new BoxLayout(rightContainer, BoxLayout.Y_AXIS));
        rightContainer.add(labelAboutExample);
        rightContainer.add(rightDownSubContainer);

        rightDownSubContainer.setLayout(new GridLayout());
        rightDownSubContainer.add(tabbedPane);

        preview.setLayout(new BorderLayout());
        tabbedPane.addTab("Preview", preview);
        tabbedPane.addTab("Source", sourceContainer);
        tabbedPane.setVisible(false);

        mainContainer.setLeftComponent(leftScrollPane);
        mainContainer.setRightComponent(rightContainer);

        frame.add(mainContainer);

        final ArrayList<Category> categories = DataProvider.getCategoryFromFolders();
        treeOfExample = new JTree(DataProvider.createRootTreeNode(categories));
        treeOfExample.setRootVisible(false);
        treeOfExample.expandRow(0);
        leftPanel.add(treeOfExample);
        treeOfExample.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath treePath = e.getPath();
                for (Category category : categories) {
                    for (SampleInfo sampleInfo : category.getSampleInfos()) {
                        if (sampleInfo.getName().toString() == treePath.getLastPathComponent().toString()) {
                            preview.removeAll();
                            preview.revalidate();
                            labelAboutExample.setText(sampleInfo.getDescription());
                            sampleProvider.invokeInstance(sampleInfo.getName());
                            setSourceText(DataProvider.getSourceCodeFromTxt(sampleInfo.getName()));
                            javaHighlighter.highlightCode();
                            tabbedPane.setSelectedIndex(0);
                            tabbedPane.setVisible(true);
                        }
                    }
                }
            }
        });
    }

    private void setSourceText(String text) {
        source.setText(text);
        source.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DemoFrame();
            }
        });
    }

    public static void printOutStream(JTextArea textArea) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArray);
        System.setOut(printStream);
        textArea.append(byteArray.toString());
    }
}
