package com.teamdev.samples;


import com.teamdev.demo.DemoSample;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.Callback;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMFormControlElement;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;

public class LoginFacebookSample implements DemoSample {
    private Browser browser;

    @Override
    public void run(JPanel container) {
        browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
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
                browser.getDocument().findElement(By.id("u_0_2")).click();
            }
        });
    }

    @Override
    public void disposeInstance() {
        browser.dispose();
    }
}
