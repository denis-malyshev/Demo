package com.teamdev.demo.gui;

import javax.swing.*;
import java.awt.*;

public class SampleTabbedPane extends JTabbedPane {

    private JComponent preview;
    private final JTextPane source;
    private final JScrollPane sourceContainer;

    public SampleTabbedPane() {
        preview = new JPanel(new BorderLayout());
        source = new JTextPane();
        sourceContainer = new JScrollPane(source);

        setVisible(true);
        addTab("Preview", preview);
        addTab("Source", sourceContainer);
    }

    public JComponent getPreview() {
        return preview;
    }

    public JTextPane getSource() {
        return source;
    }

    public JScrollPane getSourceContainer() {
        return sourceContainer;
    }
}
