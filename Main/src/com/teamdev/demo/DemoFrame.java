package com.teamdev.demo;


import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public final class DemoFrame {
    private JScrollPane leftContainer;
    private JPanel leftPanel;
    private JPanel rightContainer;
    private final JLabel labelAboutExample = new JLabel("Click on example for showing");
    private JSplitPane mainContainer;
    private JTabbedPane tabbedPane;
    private JPanel preview;
    private JTextPane source;
    private JScrollPane sourceContainer;
    private SampleProvider sampleProvider;
    private ArrayList<Category> categories;
    private JavaHighlighter javaHighlighter;

    public DemoFrame() {
        initialize();
    }

    private void initialize() {
        initializeLeftContainer();
        initializeTabbedPane();
        initializeRightContainer();
        initializeMainContainer();
        initializeDemoFrame();
        initializeUtils();
        initializeJTree();
    }

    private void setSourceText(String text) {
        source.setText(text);
        source.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
    }

    private void initializeDemoFrame() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame();
        frame.setSize(800, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation((dimension.width - frame.getWidth()) / 2, (dimension.height - frame.getHeight()) / 2);
        frame.setTitle("Demo");
        frame.add(mainContainer);
    }

    private void initializeLeftContainer() {
        leftPanel = new JPanel();
        leftContainer = new JScrollPane(leftPanel);
        leftPanel.setBackground(Color.white);
    }

    private void initializeRightContainer() {
        rightContainer = new JPanel();
        rightContainer.setLayout(new BorderLayout());

        final JPanel rightDownSubContainer = new JPanel();
        rightDownSubContainer.setLayout(new GridLayout());
        rightDownSubContainer.add(tabbedPane);

        rightContainer.add(labelAboutExample, BorderLayout.NORTH);
        rightContainer.add(rightDownSubContainer, BorderLayout.CENTER);
    }

    private void initializeTabbedPane() {
        preview = new JPanel();
        preview.setLayout(new BorderLayout());

        source = new JTextPane();
        sourceContainer = new JScrollPane(source);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Preview", preview);
        tabbedPane.addTab("Source", sourceContainer);
        tabbedPane.setVisible(false);
    }

    private void initializeMainContainer() {
        mainContainer = new JSplitPane();
        mainContainer.setLeftComponent(leftContainer);
        mainContainer.setRightComponent(rightContainer);
    }

    private void initializeJTree() {
        categories = DataProvider.getCategories("data.xml");
        JTree treeOfExample = new JTree(DataProvider.createRootTreeNode(categories));
        treeOfExample.setRootVisible(false);
        treeOfExample.expandRow(0);
        leftPanel.add(treeOfExample);

        treeOfExample.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath treePath = e.getPath();
                for (Category category : categories) {
                    for (SampleInfo sampleInfo : category.getSampleInfo()) {
                        if (Objects.equals(sampleInfo.getName(), treePath.getLastPathComponent().toString())) {
                            updatePreview();
                            updateTabbedPane();
                            labelAboutExample.setText("<html>" + sampleInfo.getDescription() + "</html>");
                            sampleProvider.invokeInstance(sampleInfo.getName());
                            setSourceText(DataProvider.getSourceCode(sampleInfo.getName()));
                            javaHighlighter.highlightCode();
                        }
                    }
                }
            }
        });
    }

    private void initializeUtils() {
        sampleProvider = new SampleProvider(preview);
        javaHighlighter = new JavaHighlighter(source, sourceContainer);
    }

    private void updatePreview() {
        preview.removeAll();
        preview.revalidate();
    }

    private void updateTabbedPane() {
        tabbedPane.setVisible(true);
        tabbedPane.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DemoFrame();
            }
        });
    }
}
