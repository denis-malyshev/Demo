package com.teamdev.samples;

import com.teamdev.demo.DemoSample;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;

public class BrowserSample implements DemoSample {

    private Browser browser;

    public void run(JPanel container) {
        browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
        browser.loadURL("http://www.google.com");
        container.add(browserView);
    }

    public void disposeInstance() {
        browser.dispose();
    }
}
