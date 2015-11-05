package com.teamdev.samples;

import com.teamdev.demo.RunSample;

import javax.swing.*;

public class FirstSampleForSecondCategory implements RunSample {
    private JLabel label = new JLabel(this.getClass().getName().toString());

    public void run(JPanel container) {
        container.add(label);
        System.out.println("Hello");
    }

    public void dispose() {

    }
}
