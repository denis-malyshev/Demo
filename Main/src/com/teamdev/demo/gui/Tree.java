package com.teamdev.demo.gui;

import com.teamdev.demo.SampleInfo;
import com.teamdev.demo.provider.DataProvider;
import com.teamdev.demo.provider.ViewProvider;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.Position;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;

public class Tree extends JTree {

    private final ViewProvider viewProvider;
    private final DataProvider dataProvider = new DataProvider();
    private final RightContainer rightContainer;

    public Tree(ViewProvider viewProvider, RightContainer rightContainer) {
        super(viewProvider.getRootNode());
        this.viewProvider = viewProvider;
        this.rightContainer = rightContainer;

        setRootVisible(false);
        expandRow(0);

        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) getCellRenderer();
        renderer.setLeafIcon(null);
        renderer.setIcon(null);
        renderer.setOpenIcon(null);
        renderer.setClosedIcon(null);

        addSelectionListener();
    }

    public void showDefaultSample(String sampleName) {
        sampleName = viewProvider.getSample(sampleName) != null ? sampleName : "BrowserSample";
        final SampleInfo sample = viewProvider.getSample(sampleName);
        final String sourceCode = dataProvider.getSourceCode(sampleName);
        rightContainer.showSample(sample, sourceCode);
        TreePath treePath = getNextMatch(sampleName, 0, Position.Bias.Forward);
        setSelectionPath(treePath);
    }

    private void addSelectionListener() {
        addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if (e.getPath().getPathCount() == 3) {
                    final String sampleName = e.getPath().getLastPathComponent().toString();
                    final SampleInfo sample = viewProvider.getSample(sampleName);
                    final String sourceCode = dataProvider.getSourceCode(sampleName);
                    rightContainer.showSample(sample, sourceCode);
                } else {
                    rightContainer.showDefaultLabel();
                }
            }
        });
    }
}
