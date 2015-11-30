package com.teamdev.samples;

import com.teamdev.demo.DemoSample;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.*;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BrowserSample implements DemoSample {

    private Browser browser;

    public void run(JPanel container) {
        browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
        Toolbar toolbar = new Toolbar();
        browser.loadURL(toolbar.addressBar.getText());
        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                super.onFinishLoadingFrame(event);
                toolbar.addressBar.setText(browser.getURL().toString());
                if (event.isMainFrame()) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            toolbar.refreshButton.setEnabled(true);
                            toolbar.stopButton.setEnabled(false);
                            final boolean canGoForward = browser.canGoForward();
                            final boolean canGoBack = browser.canGoBack();
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    toolbar.forwardButton.setEnabled(canGoForward);
                                    toolbar.backwardButton.setEnabled(canGoBack);
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onStartLoadingFrame(StartLoadingEvent event) {
                super.onStartLoadingFrame(event);
                if (event.isMainFrame()) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            toolbar.refreshButton.setEnabled(false);
                            toolbar.stopButton.setEnabled(true);
                        }
                    });
                }
            }
        });
        container.setLayout(new BorderLayout());
        container.add(toolbar, BorderLayout.NORTH);
        container.add(browserView, BorderLayout.CENTER);
    }

    public void disposeInstance() {
        browser.dispose();
    }

    private class Toolbar extends JPanel {

        private JButton backwardButton;
        private JButton forwardButton;
        private JButton refreshButton;
        private JButton stopButton;
        private JTextField addressBar;
        private JPanel actionPanel;

        public Toolbar() {
            addressBar = new JTextField("http://www.google.com");
            addressBar.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    super.keyPressed(e);
                    if (e.getKeyCode() == 10)
                        browser.loadURL(addressBar.getText());
                }
            });
            initButtons();
            initActionPanel();
            setLayout(new GridBagLayout());
            add(actionPanel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
                    GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
            add(addressBar, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 5));
        }

        private void initActionPanel() {
            actionPanel = new JPanel();
            actionPanel.add(backwardButton);
            actionPanel.add(forwardButton);
            actionPanel.add(refreshButton);
            actionPanel.add(stopButton);
        }

        private void initButtons() {
            backwardButton = new JButton(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (browser.canGoBack())
                        browser.goBack();
                }
            });
            backwardButton.setText("backward");
            forwardButton = new JButton(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (browser.canGoForward())
                        browser.goForward();
                }
            });
            forwardButton.setText("forward");
            refreshButton = new JButton(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    browser.reload();
                }
            });
            refreshButton.setText("refresh");
            stopButton = new JButton(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    browser.stop();
                }
            });
            stopButton.setText("stop");
        }
    }
}
