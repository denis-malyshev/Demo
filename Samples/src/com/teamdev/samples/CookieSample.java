package com.teamdev.samples;


import com.teamdev.demo.RunConsoleSample;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.Cookie;
import com.teamdev.jxbrowser.chromium.CookieStorage;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;

public class CookieSample implements RunConsoleSample {

    private final Browser browser = new Browser();
    private final BrowserView browserView = new BrowserView(browser);

    @Override
    public void run(JPanel container) {
        container.add(browserView);
        CookieStorage cookieStorage = browser.getCookieStorage();
        // Create and add new cookie
        int oneHourInMilliseconds = 36000000;
        int microsecondsOffset = 1000;
        // Cookie will be alive during one hour starting from now
        long expirationTimeInMicroseconds = (System.currentTimeMillis() +
                oneHourInMilliseconds) * microsecondsOffset;
        cookieStorage.setCookie("http://www.google.com", "mycookie", "myvalue",
                ".google.com", "/", expirationTimeInMicroseconds, false, false);

        java.util.List<Cookie> cookies = cookieStorage.getAllCookies();
        for (Cookie cookie : cookies) {
            System.out.println("cookie = " + cookie);
        }
    }

    @Override
    public void dispose() {
        browser.stop();
        browser.dispose();
    }
}
