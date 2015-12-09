package com.teamdev.demo.gui;

import com.teamdev.demo.provider.ViewProvider;

import javax.swing.*;
import java.awt.*;

public class Demo extends JFrame {

    private final RightContainer rightContainer = new RightContainer();
    private final JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final Tree treeOfExamples = new Tree(new ViewProvider("data.xml"), rightContainer);
    private final String startSampleName;

    public Demo(String sampleName) {
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
        final JSplitPane mainContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainContainer.setDividerSize(5);
        mainContainer.setResizeWeight(0.2);
        mainContainer.add(createLeftContainer(), JSplitPane.LEFT);
        mainContainer.add(rightContainer, JSplitPane.RIGHT);
        add(mainContainer);
        treeOfExamples.showDefaultSample(startSampleName);
    }

    private JScrollPane createLeftContainer() {
        final JScrollPane leftContainer = new JScrollPane(leftPanel);
        leftPanel.add(treeOfExamples);
        leftPanel.setBackground(Color.white);
        return leftContainer;
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final String startSampleName = args.length > 0 ? args[0] : "BrowserSample";
                new Demo(startSampleName);
            }
        });
    }

}
