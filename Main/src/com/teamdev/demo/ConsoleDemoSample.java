package com.teamdev.demo;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public abstract class ConsoleDemoSample implements DemoSample {
    @Override
    public void run(JPanel container) {
        redirectOutPutStream(container);
    }

    @Override
    public abstract void disposeInstance();

    private void redirectOutPutStream(JPanel container) {
        final JTextArea textArea = new JTextArea();
        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textArea.append(String.valueOf((char) b));
            }
        };
        container.add(new JScrollPane(textArea), BorderLayout.PAGE_END);
        System.setOut(new PrintStream(outputStream));
    }
}
