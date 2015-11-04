package com.teamdev.demo;


import javax.swing.*;

public class ThirdExampleForSecondCategory implements Sample{
    private JLabel label = new JLabel(this.getClass().getName().toString());

    public void run(JPanel container) {
        container.add(label);
    }

    public void dispose() {

    }
}
