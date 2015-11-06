package com.teamdev.demo;


import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;

public abstract class ConsoleDemoSample implements DemoSample {
    @Override
    public abstract void run(JPanel container);

    @Override
    public abstract void disposeInstance();

    public void redirectOutPutStream(JPanel container) {
        final JTextArea textArea=new JTextArea();
        container.add(new JScrollPane(textArea), BorderLayout.PAGE_END);
        System.setOut(new PrintStream(new StreamProvider(textArea)));
    }
}
