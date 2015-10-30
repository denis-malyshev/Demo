package com.teamdev.demo;


import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;

public class FirstExampleForFirstCategory {

    private final Browser browser = new Browser();
    private final BrowserView browserView = new BrowserView(browser);

    private void run(JPanel container) {
        browser.loadURL("http://www.google.com");
        container.setLayout(new BorderLayout());
        container.add(browserView);
    }
}
