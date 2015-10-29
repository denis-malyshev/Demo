package com.teamdev.demo;


import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;

public class FirstExampleForFirstCategory {

    private void run(JPanel container) {
        Browser browser=new Browser();
        BrowserView browserView=new BrowserView(browser);
        browser.loadURL("http://www.google.com");
        container.setLayout(new BorderLayout());
        container.add(browserView);
    }
}
