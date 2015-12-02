package com.teamdev.demo;


import com.teamdev.demo.provider.DataProvider;
import com.teamdev.demo.provider.SampleProvider;
import com.teamdev.demo.provider.ViewProvider;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public final class DemoFrame {

    private JScrollPane leftContainer;
    private JPanel leftPanel;
    private JPanel rightContainer;
    private JLabel labelAboutSample;
    private JSplitPane mainContainer;
    private JTabbedPane tabbedPane;
    private JPanel preview;
    private JTextPane source;
    private JScrollPane sourceContainer;
    private SampleProvider sampleProvider;
    private JavaHighlighter javaHighlighter;
    private boolean isPreview;

    public DemoFrame() {
        initFrame();
    }

    private void initFrame() {
        initLeftContainer();
        initTabbedPane();
        initRightContainer();
        initMainContainer();
        initDemoFrame();
        initUtils();
        initJTree();
    }

    private void setSourceText(String text) {
        source.setText(text);
        source.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
    }

    private void initDemoFrame() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame();
        frame.setSize(800, 500);
        frame.setMinimumSize(new Dimension((int) dimension.getWidth() / 5, (int) dimension.getHeight() / 5));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation((dimension.width - frame.getWidth()) / 2, (dimension.height - frame.getHeight()) / 2);
        frame.setTitle("JxBrowser Demo");
        frame.add(mainContainer);
    }

    private void initLeftContainer() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftContainer = new JScrollPane(leftPanel);
        leftPanel.setBackground(Color.white);
    }

    private void initRightContainer() {
        rightContainer = new JPanel();
        rightContainer.setLayout(new BorderLayout());
        labelAboutSample = new JLabel();
        labelAboutSample.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        rightContainer.add(new JLabel("<html><h1>Click on the sample in left side for preview</h1></html>"));
    }

    private void initTabbedPane() {
        preview = new JPanel();
        preview.setLayout(new BorderLayout());

        source = new JTextPane();
        sourceContainer = new JScrollPane(source);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Preview", preview);
        tabbedPane.addTab("Source", sourceContainer);
        tabbedPane.setVisible(false);
    }

    private void initMainContainer() {
        mainContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainContainer.setDividerSize(5);
        mainContainer.add(leftContainer, JSplitPane.LEFT);
        mainContainer.add(rightContainer, JSplitPane.RIGHT);
    }

    private void initJTree() {
        final ViewProvider viewProvider = new ViewProvider("data.xml");
        JTree treeOfExample = new JTree(viewProvider.getRootNode());
        setupJTree(treeOfExample);
        treeOfExample.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if (e.getPath().getPathCount() > 2) {
                    if(!isPreview) {
                        clearRightContainer();
                        fillRightContainer();
                        isPreview=true;
                    }
                    String sampleName = e.getPath().getLastPathComponent().toString();
                    showSample(viewProvider.getSample(sampleName));
                } else {
                    isPreview=false;
                    clearRightContainer();
                    rightContainer.add(new JLabel("<html><h1>Click on the sample in left side for preview</h1></html>"));
                }
            }
        });
        leftPanel.add(treeOfExample);
    }

    private void clearRightContainer() {
        rightContainer.removeAll();
        rightContainer.updateUI();
    }

    private void fillRightContainer() {
        final JPanel rightDownSubContainer = new JPanel();
        rightDownSubContainer.setLayout(new GridLayout());
        rightDownSubContainer.add(tabbedPane);

        rightContainer.add(labelAboutSample, BorderLayout.NORTH);
        rightContainer.add(rightDownSubContainer, BorderLayout.CENTER);
    }

    private void showSample(SampleInfo sampleInfo) {
        DataProvider dataProvider = new DataProvider();
        updatePreview();
        updateTabbedPane();
        updateLabelAboutSample(sampleInfo.getName(), sampleInfo.getDescription());
        sampleProvider.runSample(sampleInfo.getName());
        setSourceText(dataProvider.getSourceCode(sampleInfo.getName()));
        javaHighlighter.highlightCode();
    }

    private void updateLabelAboutSample(String sampleName, String sampleDescription) {
        labelAboutSample.setText("<html><h4>" + sampleName + "</h4>" + sampleDescription + "</html>");
    }

    private void setupJTree(JTree jTree) {
        jTree.setRootVisible(false);
        jTree.expandRow(0);

        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) jTree.getCellRenderer();
        renderer.setLeafIcon(null);
        renderer.setIcon(null);
        renderer.setOpenIcon(null);
        renderer.setClosedIcon(null);
    }

    private void initUtils() {
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

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DemoFrame();
            }
        });
    }
}
