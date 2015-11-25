package com.teamdev.samples;


import com.teamdev.demo.ConsoleDemoSample;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.*;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The sample demonstrates how to listen to different load events such as
 * 'start loading frame', 'finish loading frame', 'fail loading frame',
 * 'document loaded in frame' and 'document loaded in main frame'.
 */
public class LoadListenerSample extends ConsoleDemoSample {
    private Browser browser;

    @Override
    public void run(JPanel container) {
        super.run(container);
        browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
        JTextField addressBar = new JTextField("http://google.com");
        addressBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    browser.loadURL(addressBar.getText());
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            addressBar.setText(browser.getURL());
                        }
                    });
                }
            }
        });
        container.add(addressBar, BorderLayout.NORTH);
        container.add(browserView, BorderLayout.CENTER);
        browser.addLoadListener(new LoadListener() {
            @Override
            public void onStartLoadingFrame(StartLoadingEvent event) {
                System.out.println("Frame has started loading: Frame ID: " + event.getFrameId()
                        + ", Is Main Frame: " + event.isMainFrame());
            }

            @Override
            public void onProvisionalLoadingFrame(ProvisionalLoadingEvent event) {
                System.out.println("Provisional load was committed for a frame: Frame ID: "
                        + event.getFrameId() + ", Is Main Frame: " + event.isMainFrame());
            }

            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                System.out.println("Frame has finished loading: Frame ID: "
                        + event.getFrameId() + ", Is Main Frame: " + event.isMainFrame());
            }

            @Override
            public void onFailLoadingFrame(FailLoadingEvent event) {
                NetError errorCode = event.getErrorCode();
                System.out.println("Frame has failed loading: Frame ID: " + event.getFrameId()
                        + ", Is Main Frame: " + event.isMainFrame() + ", errorCode: " + errorCode);
            }

            @Override
            public void onDocumentLoadedInFrame(FrameLoadEvent event) {
                System.out.println("FrameLoad: Frame ID: " + event.getFrameId() + ", Is Main Frame: " + event.isMainFrame());
            }

            @Override
            public void onDocumentLoadedInMainFrame(LoadEvent event) {
                System.out.println("Frame document is loaded.");
            }
        });
        browser.loadURL("http://www.google.com");
    }

    @Override
    public void disposeInstance() {
        browser.dispose();
        System.setOut(System.out);
    }
}
