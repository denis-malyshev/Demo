package com.teamdev.samples;

import com.teamdev.demo.ConsoleDemoSample;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.DisposeEvent;
import com.teamdev.jxbrowser.chromium.events.DisposeListener;

import javax.swing.*;

/**
 * This sample demonstrates how to disposeInstance Browser instance.
 * Every Browser instance must be disposed before your Java
 * application exit.
 */
public class DisposeBrowserSample implements ConsoleDemoSample {
    @Override
    public void run(JComponent container) {
        Browser browser = new Browser();
        browser.addDisposeListener(new DisposeListener<Browser>() {
            public void onDisposed(DisposeEvent<Browser> event) {
                System.out.println("Browser is disposed.");
            }
        });
        browser.dispose();
    }

    @Override
    public void disposeInstance() {
        System.setOut(System.out);
    }

}
