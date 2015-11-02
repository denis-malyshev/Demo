package com.teamdev.demo;


import javax.swing.*;

public class SecondExampleForSecondCategory {
    private JLabel label = new JLabel(this.getClass().getName().toString());

    private void run(JPanel container) {
        container.add(label);
    }
}
