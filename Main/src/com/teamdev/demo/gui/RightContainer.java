package com.teamdev.demo.gui;

import com.teamdev.demo.JavaHighlighter;
import com.teamdev.demo.SampleInfo;
import com.teamdev.demo.provider.SampleProvider;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;

public class RightContainer extends JPanel {

    private final SampleTabbedPane tabbedPane;
    private final JLabel labelAboutSample;
    private final JavaHighlighter javaHighlighter;
    private final SampleProvider sampleProvider;

    public RightContainer() {
        setLayout(new BorderLayout());
        tabbedPane = new SampleTabbedPane();
        sampleProvider = new SampleProvider(tabbedPane.getPreview());
        javaHighlighter = new JavaHighlighter(tabbedPane.getSource(), tabbedPane.getSourceContainer());
        labelAboutSample = new JLabel();
        labelAboutSample.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));
        add(labelAboutSample, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    public void showSample(SampleInfo sample, String sourceCode) {
        updateComponents();
        String sampleName = sample.getName();
        String description = sample.getDescription();
        updateLabelAboutSample(sampleName, description);
        sampleProvider.runSample(sampleName);
        setSourceText(sourceCode);
        javaHighlighter.highlightCode();
    }

    public void showDefaultLabel() {
        clear();
        add(new JLabel("<html><h1>Click on the sample in left side for preview</h1></html>"));
    }

    private void updateComponents() {
        JPanel preview = tabbedPane.getPreview();
        preview.removeAll();
        preview.revalidate();
        clear();
        fill();
    }

    private void clear() {
        removeAll();
        validate();
        repaint();
    }

    private void fill() {
        add(labelAboutSample, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void setSourceText(String text) {
        JTextPane source = tabbedPane.getSource();
        source.setText(text);
        source.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
    }

    private void updateLabelAboutSample(String sampleName, String sampleDescription) {
        labelAboutSample.setText("<html><h4>" + sampleName + "</h4>" + sampleDescription + "</html>");
    }
}
