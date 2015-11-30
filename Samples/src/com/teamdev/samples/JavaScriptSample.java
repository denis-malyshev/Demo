package com.teamdev.samples;


import com.teamdev.demo.DemoSample;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaScriptSample implements DemoSample {
    private Browser browser;

    @Override
    public void run(JPanel container) {
        browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
        JSConsole jsConsole = new JSConsole();
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
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(addressBar, BorderLayout.NORTH);
        topPanel.add(browserView, BorderLayout.CENTER);

        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(jsConsole, BorderLayout.CENTER);

        final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.add(topPanel, JSplitPane.TOP);
        splitPane.add(bottomPanel, JSplitPane.BOTTOM);
        splitPane.setResizeWeight(0.8);
        splitPane.setDividerSize(5);

        container.add(splitPane);
        browser.loadURL(addressBar.getText());
    }

    @Override
    public void disposeInstance() {
        browser.dispose();
    }

    private class JSConsole extends JPanel {
        private JTextField jsCodeArea;
        private JTextArea console;
        private JPanel consolePanel;

        public JSConsole() {
            initComponents();
            setLayout(new BorderLayout());
            final JScrollPane consoleContainer = new JScrollPane(consolePanel);
            consoleContainer.setBorder(BorderFactory.createEmptyBorder());
            add(consoleContainer, BorderLayout.CENTER);
            add(jsCodeArea, BorderLayout.SOUTH);
        }

        private void initComponents() {
            initJSCodeArea();
            initConsolePanel();
        }

        private void initConsolePanel() {
            initConsole();
            consolePanel = new JPanel();
            consolePanel.setLayout(new BorderLayout());
            JLabel title = new JLabel("JavaScript Console");
            consolePanel.add(title, BorderLayout.NORTH);
            consolePanel.add(console, BorderLayout.CENTER);
        }

        private void initConsole() {
            console = new JTextArea();
            console.setFont(new Font("Consolas", Font.PLAIN, 12));
            console.setEditable(false);
        }

        private void initJSCodeArea() {
            jsCodeArea = new JTextField();
            jsCodeArea.setFont(new Font("Consolas", Font.PLAIN, 12));
            jsCodeArea.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == 10) {
                        final String script = jsCodeArea.getText();
                        jsCodeArea.setText("");
                        JSValue jsValue = browser.executeJavaScriptAndReturnValue(script);
                        console.append(jsValueToPresentation(jsValue) + "\n");
                    }
                }
            });
        }

        private String jsValueToPresentation(JSValue jsValue) {
            if (jsValue.isNumber())
                return "numberValue = " + jsValue.getNumber();
            if (jsValue.isBoolean())
                return "booleanValue = " + jsValue.getBoolean();
            if (jsValue.isNull())
                return "null";
            if (jsValue.isUndefined())
                return "property is undefined";
            if (jsValue.isString())
                return "stringValue = '" + jsValue.getString() + "'";
            if (jsValue.isObject())
                return "object";
            return jsValue.toString();
        }
    }
}
