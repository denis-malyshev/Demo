package com.teamdev.samples;

import com.teamdev.demo.RunSample;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;

public class FirstSampleForFirstCategory implements RunSample {

    private final Browser browser = new Browser();
    private final BrowserView browserView = new BrowserView(browser);

    public void run(JPanel container) {
        browser.loadURL("http://www.google.com");
        container.setLayout(new BorderLayout());
        container.add(browserView);
    }

    public void dispose() {
        browser.stop();
        browser.dispose();
    }
}