package com.teamdev.demo;


import javax.swing.*;
import java.lang.reflect.Method;

public class SampleProvider {

    private Class currentInstance;
    private final JPanel container;
    private boolean isExist;

    public SampleProvider(JPanel container) {
        this.container = container;
    }

    private Class createInstanceByClassName(String className) {
        try {
            return Class.forName("com.teamdev.samples." + className);
        } catch (Exception e) {
            throw new IllegalArgumentException("No such class");
        }
    }

    private void runInstance() {
        try {
            Method method = currentInstance.getDeclaredMethod("run", JPanel.class);
            method.setAccessible(true);
            method.invoke(currentInstance.newInstance(), container);
        } catch (Exception e) {
            throw new IllegalArgumentException("No such method");
        }
    }

    public void invokeInstance(String instanceName) {
        if(!isExist) {
            currentInstance = createInstanceByClassName(instanceName);
            isExist = true;
            runInstance();
        }
        else
            disposeInstance();
    }

    public void disposeInstance() {
        try {
            Method method = currentInstance.getDeclaredMethod("disposeInstance");
            method.setAccessible(true);
            method.invoke(currentInstance.newInstance());
            isExist=false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
