package com.teamdev.samples;


import com.teamdev.demo.DemoSample;
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
public class LoadListenerSample implements DemoSample {
    private Browser browser;

    @Override
    public void run(JPanel container) {
        browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
        JTextField addressBar = new JTextField("http://google.com");
        JTextArea console = new JTextArea();
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
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(addressBar, BorderLayout.NORTH);
        topPanel.add(browserView, BorderLayout.CENTER);
        final JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(new JScrollPane(console),BorderLayout.CENTER);

        final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.add(topPanel, JSplitPane.TOP);
        splitPane.add(bottomPanel, JSplitPane.BOTTOM);
        splitPane.setResizeWeight(0.8);
        splitPane.setDividerSize(5);

        container.add(splitPane);

        browser.addLoadListener(new LoadListener() {
            @Override
            public void onStartLoadingFrame(StartLoadingEvent event) {
                console.append("Frame has started loading: Frame ID: " + event.getFrameId()
                        + ", Is Main Frame: " + event.isMainFrame() + "\n");
            }

            @Override
            public void onProvisionalLoadingFrame(ProvisionalLoadingEvent event) {
                console.append("Provisional load was committed for a frame: Frame ID: "
                        + event.getFrameId() + ", Is Main Frame: " + event.isMainFrame() + "\n");
            }

            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                console.append("Frame has finished loading: Frame ID: "
                        + event.getFrameId() + ", Is Main Frame: " + event.isMainFrame() + "\n");
            }

            @Override
            public void onFailLoadingFrame(FailLoadingEvent event) {
                NetError errorCode = event.getErrorCode();
                console.append("Frame has failed loading: Frame ID: " + event.getFrameId()
                        + ", Is Main Frame: " + event.isMainFrame() + ", errorCode: " + errorCode + "\n");
            }

            @Override
            public void onDocumentLoadedInFrame(FrameLoadEvent event) {
                console.append("FrameLoad: Frame ID: " + event.getFrameId() + ", Is Main Frame: " + event.isMainFrame() + "\n");
            }

            @Override
            public void onDocumentLoadedInMainFrame(LoadEvent event) {
                console.append("Frame document is loaded." + "\n");
            }
        });
        browser.loadURL("http://www.google.com");
    }

    @Override
    public void disposeInstance() {
        browser.dispose();
    }
}
