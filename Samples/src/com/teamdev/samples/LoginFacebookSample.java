package com.teamdev.samples;


import com.teamdev.demo.RunSample;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.Callback;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMFormControlElement;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;

public class LoginFacebookSample implements RunSample {
    private final Browser browser = new Browser();
    private final BrowserView browserView = new BrowserView(browser);

    @Override
    public void run(JPanel container) {
        container.setLayout(new BorderLayout());
        container.add(browserView);
        // Load https://www.facebook.com/login.php and wait until web page is loaded completely.
        Browser.invokeAndWaitFinishLoadingMainFrame(browser, new Callback<Browser>() {
            @Override
            public void invoke(Browser browser) {
                browser.loadURL("https://www.facebook.com/login.php");
            }
        });
        // Access DOM document of the loaded web page.
        DOMDocument document = browser.getDocument();
        // Find and enter email.
        ((DOMFormControlElement) document.findElement(By.id("email"))).setValue("user@mail.com");
        // Find and enter password.
        ((DOMFormControlElement) document.findElement(By.id("pass"))).setValue("123");
        // Find and click Login button and wait until a new web page is loaded completely.
        Browser.invokeAndWaitFinishLoadingMainFrame(browser, new Callback<Browser>() {
            @Override
            public void invoke(Browser browser) {
                browser.getDocument().findElement(By.id("u_0_1")).click();
            }
        });
    }

    @Override
    public void dispose() {
        browser.stop();
        browser.dispose();
    }
}
