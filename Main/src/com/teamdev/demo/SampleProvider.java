package com.teamdev.demo;
import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SampleProvider {

    private static Class currentInstance;
    private static JTextArea textArea;

    public static void createInstanceByClassName(String className, JPanel container) {
        try {
            if (currentInstance != null)
                dispose();
            currentInstance = Class.forName("com.teamdev.samples." + className);
            Method method = currentInstance.getDeclaredMethod("run", JPanel.class);
            method.setAccessible(true);
            method.invoke(currentInstance.newInstance(), container);
            if (currentInstance.getInterfaces()[0] == RunConsoleSample.class) {
                textArea=new JTextArea();
                PrintStream printStream=new PrintStream(new StreamProvider(textArea));
                System.setOut(printStream);
                container.add(textArea, BorderLayout.AFTER_LAST_LINE);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void dispose() {
        try {
            Method method = currentInstance.getDeclaredMethod("dispose");
            method.setAccessible(true);
            method.invoke(currentInstance.newInstance());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
