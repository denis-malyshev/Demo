package com.teamdev.samples;


import com.teamdev.demo.DemoSample;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This sample demonstrates how to load custom HTML string into
 * Browser component and display it.
 */
public class LoadHTMLSample implements DemoSample {
    private Browser browser;

    @Override
    public void run(JPanel container) {
        browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
        HTMLArea htmlArea = new HTMLArea();
        browser.loadHTML(htmlArea.htmlArea.getText());

        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(browserView, BorderLayout.CENTER);

        final JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(htmlArea, BorderLayout.CENTER);

        final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.add(topPanel, JSplitPane.TOP);
        splitPane.add(bottomPanel, JSplitPane.BOTTOM);
        splitPane.setResizeWeight(0.6);
        splitPane.setDividerSize(5);

        container.add(splitPane);
    }

    @Override
    public void disposeInstance() {
        browser.dispose();
    }

    private class HTMLArea extends JPanel {
        private JTextArea htmlArea;
        private JScrollPane scrollPane;
        private JButton loadHTMLButton;
        private JButton getHTMLButton;
        private JButton loadGoogleComButton;

        public HTMLArea() {
            htmlArea = new JTextArea("<html><body><h1>Load HTML Sample</h1></body></html>");
            htmlArea.setFont(new Font("Consolas", Font.PLAIN, 12));
            scrollPane = new JScrollPane(htmlArea);
            initLoadHTMLButton();
            initGetHTMLButton();
            initLoadGoogleComButton();
            setLayout(new GridBagLayout());
            add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

            final JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            buttonPanel.add(loadHTMLButton);
            buttonPanel.add(getHTMLButton);
            buttonPanel.add(loadGoogleComButton);

            add(buttonPanel, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
                    GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        }

        private void initLoadHTMLButton() {
            loadHTMLButton = new JButton(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    browser.loadHTML(htmlArea.getText());
                }
            });
            loadHTMLButton.setText("Load html");
        }

        private void initGetHTMLButton() {
            getHTMLButton = new JButton(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    htmlArea.setText(browser.getHTML());
                }
            });
            getHTMLButton.setText("Get html");
        }

        private void initLoadGoogleComButton() {
            loadGoogleComButton = new JButton(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    browser.loadURL("http://www.google.com");
                }
            });
            loadGoogleComButton.setText("Load google.com");
        }
    }
}
