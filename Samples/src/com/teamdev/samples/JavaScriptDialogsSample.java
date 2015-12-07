package com.teamdev.samples;


import com.teamdev.demo.DemoSample;
import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import com.teamdev.jxbrowser.chromium.swing.DefaultDialogHandler;
import com.teamdev.samples.resources.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * The sample demonstrates how to override default alert dialog. You
 * can register your own DialogHandler where you can implement all
 * the required JavaScript dialogs yourself.
 */
public class JavaScriptDialogsSample implements DemoSample {

    private Browser browser;
    private BrowserView browserView;

    @Override
    public void run(JPanel container) {
        browser = new Browser();
        browserView = new BrowserView(browser);
        Resources resources=new Resources();
        String url= resources.getHTML("javaScriptDialogs.html");
        browser.loadHTML(url);
        PreferenceBar preferenceBar = new PreferenceBar();
        container.setLayout(new BorderLayout());
        container.add(preferenceBar, BorderLayout.NORTH);
        container.add(browserView, BorderLayout.CENTER);
    }

    @Override
    public void disposeInstance() {
        browser.dispose();
    }

    private class PreferenceBar extends JPanel {

        private boolean alertEnabled = true;
        private boolean promptEnabled = true;
        private boolean confirmEnabled = true;

        public PreferenceBar() {
            setDialogHandler();
            setLayout(new FlowLayout(FlowLayout.LEFT));
            add(createPreferencePanel());
        }

        private void setDialogHandler() {
            browser.setDialogHandler(new DefaultDialogHandler(browserView) {
                @Override
                public void onAlert(DialogParams dialogParams) {
                    if (alertEnabled) {
                        String title = browser.getTitle();
                        String message = dialogParams.getMessage();
                        JOptionPane.showMessageDialog(browserView, message, title,
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }

                @Override
                public CloseStatus onPrompt(PromptDialogParams promptDialogParams) {
                    if (promptEnabled) {
                        return super.onPrompt(promptDialogParams);
                    }
                    return null;
                }

                @Override

                public CloseStatus onConfirmation(DialogParams dialogParams) {
                    if (confirmEnabled) {
                        super.onConfirmation(dialogParams);
                    }
                    return null;
                }
            });
        }

        private Component createPreferencePanel() {
            JPanel panel = new JPanel();
            panel.add(createCheckBoxMenuItem("Alert enabled", alertEnabled, new CheckBoxItemCallback() {
                @Override
                public void call(boolean selected) {
                    alertEnabled = selected;
                }
            }));
            panel.add(createCheckBoxMenuItem("Prompt enabled", promptEnabled, new CheckBoxItemCallback() {
                @Override
                public void call(boolean selected) {
                    promptEnabled = selected;
                }
            }));
            panel.add(createCheckBoxMenuItem("Confirm enabled", confirmEnabled, new CheckBoxItemCallback() {
                @Override
                public void call(boolean selected) {
                    confirmEnabled = selected;
                }
            }));
            return panel;
        }

        private Component createCheckBoxMenuItem(String name, boolean selected, CheckBoxItemCallback action) {
            JCheckBox checkBox = new JCheckBox(name, selected);
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    action.call(checkBox.isSelected());
                }
            });
            return checkBox;
        }
    }

    private interface CheckBoxItemCallback {
        void call(boolean selected);
    }

}
