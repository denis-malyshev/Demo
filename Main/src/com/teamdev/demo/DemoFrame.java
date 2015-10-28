package com.teamdev.demo;


import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.ArrayList;

public final class DemoFrame {
    private final JFrame frame;
    private final JPanel leftPanel = new JPanel();
    private final JPanel rightContainer = new JPanel();
    private final JPanel rightDownSubContainer = new JPanel();
    private final JLabel labelAboutExample = new JLabel();
    private final JSplitPane mainContainer = new JSplitPane();
    private final JTabbedPane tabbedPane = new JTabbedPane();
    private final JScrollPane leftScrollPane = new JScrollPane(leftPanel);
    private final JPanel preview=new JPanel();
    private final JTextPane source=new JTextPane();
    private final JScrollPane sourceContainer=new JScrollPane(source);
    private final JTree treeOfExample;

    public DemoFrame(DefaultMutableTreeNode tree) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame();
        frame.setSize(800, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation((dimension.width - frame.getWidth()) / 2, (dimension.height - frame.getHeight()) / 2);
        frame.setTitle("Demo");

        leftPanel.setMinimumSize(new Dimension(100, frame.getHeight()));

        rightDownSubContainer.add(tabbedPane);
        rightDownSubContainer.setLayout(new BoxLayout(rightDownSubContainer, BoxLayout.Y_AXIS));

        source.setEditable(false);
        tabbedPane.addTab("Preview", preview);
        tabbedPane.addTab("Source", sourceContainer);

        rightContainer.setLayout(new BoxLayout(rightContainer, BoxLayout.Y_AXIS));

        rightContainer.add(labelAboutExample);
        rightContainer.add(rightDownSubContainer);

        mainContainer.setLeftComponent(leftScrollPane);
        mainContainer.setRightComponent(rightContainer);

        frame.add(mainContainer);

        treeOfExample = new JTree(tree);
        leftPanel.add(treeOfExample);
        final ArrayList<Category> categories = Wrapper.wrapper.getCategories();
        treeOfExample.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath treePath = e.getPath();
                for (Category category : categories) {
                    for (Example example : category.getExamples()) {
                        if (example.getName().toString() == treePath.getLastPathComponent().toString()) {
                            setLabelAboutExample(example.getDescription());
                            preview.removeAll();
                            DataProvider.createInstanceByClassName(example.getName(),preview);
                            String text=DataProvider.getSourceCodeFromTxt(example.getName());
                            source.setText(text);
                            appendToTextArea(source,"void",Color.CYAN);
                        }
                    }
                }
            }
        });
    }

    private void setLabelAboutExample(String text) {
        labelAboutExample.setText(text);
    }

    private void appendToTextArea(JTextPane textArea, String text, Color color) {
        StyleContext styleContext= StyleContext.getDefaultStyleContext();
        AttributeSet attributeSet=styleContext.addAttribute(SimpleAttributeSet.EMPTY,StyleConstants.Foreground, color);
        attributeSet=styleContext.addAttribute(attributeSet,StyleConstants.FontFamily,"Lucida  Console");
        attributeSet=styleContext.addAttribute(attributeSet,StyleConstants.Alignment,StyleConstants.ALIGN_JUSTIFIED);
        int len=textArea.getDocument().getLength();
        textArea.setCaretPosition(len);
        textArea.setCharacterAttributes(attributeSet,false);
        textArea.replaceSelection(text);
    }
}
