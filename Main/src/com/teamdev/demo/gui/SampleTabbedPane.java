package com.teamdev.demo.gui;

import com.teamdev.demo.provider.SampleProvider;

import javax.swing.*;
import java.awt.*;

public class SampleTabbedPane extends JTabbedPane {

    private final JPanel preview;
    private final JTextPane source;
    private final JScrollPane sourceContainer;
    private final SampleProvider sampleProvider;

    public SampleTabbedPane() {
        preview = new JPanel(new BorderLayout());
        source = new JTextPane();
        sourceContainer = new JScrollPane(source);
        sampleProvider = new SampleProvider(preview);

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
