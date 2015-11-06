package com.teamdev.samples;


import com.teamdev.demo.ConsoleDemoSample;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.ConsoleEvent;
import com.teamdev.jxbrowser.chromium.events.ConsoleListener;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;

public class ConsoleListenerSample  extends ConsoleDemoSample  {
    @Override
    public void redirectOutPutStream(JPanel container) {
        super.redirectOutPutStream(container);
    }

    private Browser browser;
    private BrowserView browserView;

    @Override
    public void run(JPanel container) {
        browser = new Browser();
        browserView = new BrowserView(browser);

        container.add(browserView);

        redirectOutPutStream(container);
        browser.addConsoleListener(new ConsoleListener() {
            public void onMessage(ConsoleEvent event) {
                System.out.println("Level: " + event.getLevel());
                System.out.println("Message: " + event.getMessage());
            }
        });
        browser.executeJavaScript("console.error(\"Error message\");");
    }

    @Override
    public void disposeInstance() {
        browser.dispose();
    }


}
