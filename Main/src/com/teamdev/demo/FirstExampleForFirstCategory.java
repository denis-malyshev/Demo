package com.teamdev.demo;


import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;

public class FirstExampleForFirstCategory {

    private void run(JComponent container) {
        Browser browser=new Browser();
        BrowserView browserView=new BrowserView(browser);
        container.add(browserView);
        browser.loadURL("http://www.google.com");
    }
}
