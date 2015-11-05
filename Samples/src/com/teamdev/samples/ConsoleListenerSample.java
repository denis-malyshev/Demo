package com.teamdev.samples;


import com.teamdev.demo.RunConsoleSample;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.ConsoleEvent;
import com.teamdev.jxbrowser.chromium.events.ConsoleListener;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;

public class ConsoleListenerSample implements RunConsoleSample {

    private final Browser browser = new Browser();
    private final BrowserView browserView = new BrowserView(browser);

    @Override
    public void run(JPanel container) {

        container.add(browserView);

        browser.addConsoleListener(new ConsoleListener() {
            public void onMessage(ConsoleEvent event) {
                System.out.println("Level: " + event.getLevel());
                System.out.println("Message: " + event.getMessage());
            }
        });
        browser.executeJavaScript("console.error(\"Error message\");");
    }

    @Override
    public void dispose() {
        browser.stop();
        browser.dispose();
    }
}
