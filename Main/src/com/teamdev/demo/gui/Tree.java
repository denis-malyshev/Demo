package com.teamdev.demo.gui;

import com.teamdev.demo.SampleInfo;
import com.teamdev.demo.provider.DataProvider;
import com.teamdev.demo.provider.ViewProvider;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.text.Position;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

public class Tree extends JTree {

    private final ViewProvider viewProvider;
    private final DataProvider dataProvider;
    private final RightContainer rightContainer;

    public Tree(ViewProvider viewProvider, RightContainer rightContainer) {
        super(viewProvider.getRootNode());
        this.viewProvider = viewProvider;
        this.rightContainer = rightContainer;
        this.dataProvider = new DataProvider();

        setRootVisible(false);
        expandRow(0);

        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) getCellRenderer();
        renderer.setLeafIcon(null);
        renderer.setIcon(null);
        renderer.setOpenIcon(null);
        renderer.setClosedIcon(null);

        addSelectionListener();

        addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {

            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
                setExpandedState(event.getPath(), false);
            }
        });
    }

    public void showDefaultSample(String sampleName) {
        String validatedSampleName = viewProvider.hasSample(sampleName) ? sampleName : Demo.DEFAULT_SAMPLE_NAME;
        SampleInfo sample = viewProvider.getSample(validatedSampleName);
        String sourceCode = dataProvider.getSourceCode(validatedSampleName);
        rightContainer.showSample(sample, sourceCode);
        TreePath treePath = getNextMatch(validatedSampleName, 0, Position.Bias.Forward);
        setSelectionPath(treePath);
    }

    private void addSelectionListener() {
        addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if (e.getPath().getPathCount() == 3) {
                    String sampleName = e.getPath().getLastPathComponent().toString();
                    SampleInfo sample = viewProvider.getSample(sampleName);
                    String sourceCode = dataProvider.getSourceCode(sampleName);
                    rightContainer.showSample(sample, sourceCode);
                } else {
                    rightContainer.showDefaultLabel();
                }
            }
        });
    }
}
