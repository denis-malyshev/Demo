package com.teamdev.demo.provider;


import com.teamdev.demo.ConsoleDemoSample;

import javax.swing.*;
import java.lang.reflect.Method;

public class SampleProvider {

    private Class currentClass;
    private Object instance;
    private boolean isExist;

    public void runSample(String instanceName, JComponent container) {
        loadClass(instanceName);
        createInstance();
        runInstance(container);
    }

    public boolean isConsoleSample(String sampleName) {
        try {
            Object type = Class.forName("com.teamdev.samples." + sampleName).newInstance();
            if(type instanceof ConsoleDemoSample) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void loadClass(String className) {
        if (isExist)
            disposeInstance();
        try {
            currentClass = Class.forName("com.teamdev.samples." + className);
        } catch (Exception e) {
            throw new IllegalArgumentException("No such class");
        }
    }

    private void createInstance() {
        try {
            instance = currentClass.newInstance();
            isExist = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void runInstance(JComponent container) {
        try {
            Method method = currentClass.getMethod("run", JComponent.class);
            method.invoke(instance, container);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
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
