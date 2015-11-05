package com.teamdev.demo;


import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class StreamProvider extends OutputStream {

    private JTextArea jTextArea;

    public StreamProvider(JTextArea jTextArea) {
        this.jTextArea = jTextArea;
    }

    @Override
    public void write(int b) throws IOException {
        jTextArea.append(String.valueOf((char)b));
    }
}
