package com.teamdev.samples;

import com.teamdev.demo.RunSample;

import javax.swing.*;

public class ThirdSampleForSecondCategory implements RunSample {
    private JLabel label = new JLabel(this.getClass().getName().toString());

    public void run(JPanel container) {
        container.add(label);
    }

    public void dispose() {

    }
}
