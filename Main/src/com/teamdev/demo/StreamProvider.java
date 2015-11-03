package com.teamdev.demo;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class StreamProvider {

    private  PrintStream printStream;

    public StreamProvider() {
        try {
            printStream =new PrintStream(new File("temp.txt"));
            System.setOut(printStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  void closeStream() {
        printStream.close();
    }
}
