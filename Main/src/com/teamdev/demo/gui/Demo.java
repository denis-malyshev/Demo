package com.teamdev.demo.gui;

import com.teamdev.demo.provider.ViewProvider;

import javax.swing.*;
import java.awt.*;

public class Demo extends JFrame {

    public static final String DEFAULT_SAMPLE_NAME = "BrowserSample";
    private final RightContainer rightContainer;
    private final JPanel leftPanel;
    private final Tree treeOfExamples;
    private final String startSampleName;

    public Demo(String sampleName) {
        rightContainer = new RightContainer();
        leftPanel = new JPanel(new BorderLayout());
        treeOfExamples = new Tree(new ViewProvider("data.xml"), rightContainer);

        this.startSampleName = sampleName;

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(800, 500);
        setMinimumSize(new Dimension((int) dimension.getWidth() / 5, (int) dimension.getHeight() / 5));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation((dimension.width - getWidth()) / 2, (dimension.height - getHeight()) / 2);
        setTitle("JxBrowser Demo");
        initMainContainer();
    }

    private void initMainContainer() {
        JSplitPane mainContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainContainer.setDividerSize(5);
        mainContainer.setResizeWeight(0.25);
        mainContainer.add(createLeftContainer(), JSplitPane.LEFT);
        mainContainer.add(rightContainer, JSplitPane.RIGHT);
        add(mainContainer);
        treeOfExamples.showDefaultSample(startSampleName);
    }

    private JScrollPane createLeftContainer() {
        leftPanel.add(treeOfExamples, BorderLayout.CENTER);
        leftPanel.setBackground(Color.white);
        JScrollPane scrollPane = new JScrollPane(leftPanel);
        scrollPane.setMinimumSize(new Dimension(150, 200));
        return scrollPane;
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String startSampleName = args.length > 0 ? args[0] : DEFAULT_SAMPLE_NAME;
                new Demo(startSampleName);
            }
        });
    }

}
