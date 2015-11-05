package com.teamdev.demo;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StreamProvider {

    private  PrintStream printStream;

    public StreamProvider() {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArray);
        System.setOut(printStream);
        //textArea.append(byteArray.toString());

    }

    public  void closeStream() {
        printStream.close();
    }
}
