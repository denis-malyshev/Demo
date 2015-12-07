package com.teamdev.demo.gui;

import com.teamdev.demo.provider.SampleProvider;

import javax.swing.*;
import java.awt.*;

public class Tab extends JTabbedPane {

    private final JPanel preview = new JPanel(new BorderLayout());
    private final JTextPane source = new JTextPane();
    private final JScrollPane sourceContainer = new JScrollPane(source);
    private final SampleProvider sampleProvider = new SampleProvider(preview);

    public Tab() {
        setVisible(true);
        addTab("Preview", preview);
        addTab("Source", sourceContainer);
    }

    public JPanel getPreview() {
        return preview;
    }

    public JTextPane getSource() {
        return source;
    }

    public JScrollPane getSourceContainer() {
        return sourceContainer;
    }
}
