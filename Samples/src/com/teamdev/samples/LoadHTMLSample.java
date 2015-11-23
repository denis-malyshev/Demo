package com.teamdev.samples;


import com.teamdev.demo.DemoSample;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoadHTMLSample implements DemoSample {
    private Browser browser;

    @Override
    public void run(JPanel container) {
        browser=new Browser();
        BrowserView browserView=new BrowserView(browser);
        HTMLArea htmlArea=new HTMLArea();
        browser.loadHTML(htmlArea.htmlArea.getText());
        container.setLayout(new GridBagLayout());
        container.add(browserView, new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));
        container.add(htmlArea,new GridBagConstraints(0,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));
    }

    @Override
    public void disposeInstance() {
        browser.dispose();
    }

    private class HTMLArea extends JPanel {
        private JTextArea htmlArea;
        private JScrollPane scrollPane;
        private JButton loadHTMLButton;

        public HTMLArea() {
            htmlArea=new JTextArea("<html><body><h1>Load HTML Sample</h1></body></html>");
            scrollPane=new JScrollPane(htmlArea);
            initLoadHTMLButton();
            setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
            add(scrollPane);
            add(loadHTMLButton);
        }

        private void initLoadHTMLButton() {
            loadHTMLButton=new JButton(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    browser.loadHTML(htmlArea.getText());
                }
            });
            loadHTMLButton.setText("Load html");
        }
    }
}
