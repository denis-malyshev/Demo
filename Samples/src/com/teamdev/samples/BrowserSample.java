package com.teamdev.samples;

import com.teamdev.demo.DemoSample;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;

public class BrowserSample implements DemoSample {

    private Browser browser;
    private BrowserView browserView;

    public void run(JPanel container) {
        browser = new Browser();
        browserView = new BrowserView(browser);
        browser.loadURL("http://www.google.com");
        container.add(browserView);
    }

    public void disposeInstance() {
        browser.dispose();
    }
}
