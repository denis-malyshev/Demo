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

    private void loadClass(String className) {
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
            Method method = currentClass.getDeclaredMethod("run", JPanel.class);
            method.setAccessible(true);
            method.invoke(instance, container);
        } catch (Exception e) {
            throw new IllegalArgumentException("No such method");
        }
    }

    public void invokeInstance(String instanceName) {
        if (isExist)
            disposeInstance();
        loadClass(instanceName);
        createInstance();
        runInstance();
    }

    public void disposeInstance() {
        try {
            Method method = currentClass.getDeclaredMethod("disposeInstance");
            method.setAccessible(true);
            method.invoke(instance);
            isExist = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
