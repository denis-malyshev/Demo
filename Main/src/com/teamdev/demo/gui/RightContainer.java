package com.teamdev.demo.gui;

import com.teamdev.demo.JavaHighlighter;
import com.teamdev.demo.SampleInfo;
import com.teamdev.demo.provider.SampleProvider;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class RightContainer extends JPanel {

    private final SampleTabbedPane tabbedPane;
    private final JLabel labelAboutSample;
    private final JavaHighlighter javaHighlighter;
    private final SampleProvider sampleProvider;
    private final JTextArea console;

    public RightContainer() {
        setLayout(new BorderLayout());
        tabbedPane = new SampleTabbedPane();
        sampleProvider = new SampleProvider();
        javaHighlighter = new JavaHighlighter(tabbedPane.getSource(), tabbedPane.getSourceContainer());
        labelAboutSample = new JLabel();
        console = new JTextArea();
        labelAboutSample.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));
        add(labelAboutSample, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    public void showSample(SampleInfo sample, String sourceCode) {
        String sampleName = sample.getName();
        String description = sample.getDescription();
        updateLabelAboutSample(sampleName, description);
        updateComponents();
        boolean isConsoleSample = isConsoleSample(sampleName);
        sampleProvider.runSample(sampleName, createContainer(isConsoleSample));
        setSourceText(sourceCode);
        javaHighlighter.highlightCode();
    }

    private JComponent createContainer(boolean isConsoleSample) {
        if (isConsoleSample) {
            JComponent container = createContainerForConsoleSample();
            return container;
        }
        return tabbedPane.getPreview();
    }

    private JComponent createContainerForConsoleSample() {
        JPanel topPanel = new JPanel(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerSize(5);
        splitPane.setResizeWeight(0.75);
        splitPane.add(topPanel, JSplitPane.TOP);
        splitPane.add(new JScrollPane(console), JSplitPane.BOTTOM);
        tabbedPane.getPreview().add(splitPane, BorderLayout.CENTER);
        return topPanel;
    }

    public void showDefaultLabel() {
        clear();
        add(new JLabel("<html><h1>Click on the sample in left side for preview</h1></html>"));
    }

    private boolean isConsoleSample(String sampleName) {
        if (sampleProvider.isConsoleSample(sampleName)) {
            redirectOutPutStream();
            return true;
        } else {
            System.setOut(System.out);
            return false;
        }
    }

    private void redirectOutPutStream() {
        JScrollPane scrollPane = new JScrollPane(console);
        console.setRows(10);
        console.setEditable(false);
        console.setFont(new Font("Consolas", Font.PLAIN, 12));
        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                console.append(String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(outputStream));
    }

    private void updateComponents() {
        tabbedPane.getPreview().removeAll();
        tabbedPane.getPreview().validate();
        tabbedPane.getPreview().repaint();
        console.setText("");
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
