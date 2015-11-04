package com.teamdev.demo;


        import javax.swing.*;

public class FirstExampleForSecondCategory implements Sample{
    private JLabel label = new JLabel(this.getClass().getName().toString());

    public void run(JPanel container) {
        container.add(label);
        System.out.println("Hello");
    }

    public void dispose() {

    }
}
