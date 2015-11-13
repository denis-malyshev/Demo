package com.teamdev.demo;


import javax.swing.*;
import java.lang.reflect.Method;

public class SampleProvider {

    private Class currentClass;
    private final JPanel container;
    private Object instance;
    private boolean isExist;

    public SampleProvider(JPanel container) {
        this.container = container;
    }

    public void runSample(String instanceName) {
        loadClass(instanceName);
        createInstance();
        runInstance();
    }

    private void loadClass(String className) {
        if (isExist)
            disposeInstance();
        try {
            currentClass =Class.forName("com.teamdev.samples." + className);
        } catch (Exception e) {
            throw new IllegalArgumentException("No such class");
        }
    }

    private void createInstance() {
        try {
            instance= currentClass.newInstance();
            isExist = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void runInstance() {
        try {
            Method method = currentClass.getMethod("run", JPanel.class);
            method.invoke(instance, container);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void disposeInstance() {
        try {
            Method method = currentClass.getMethod("disposeInstance");
            method.invoke(instance);
            isExist = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
