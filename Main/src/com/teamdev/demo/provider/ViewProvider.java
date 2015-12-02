package com.teamdev.demo.provider;


import com.teamdev.demo.Category;
import com.teamdev.demo.SampleInfo;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewProvider {
    private final DataProvider dataProvider;
    private Map<String, SampleInfo> samples;
    private final DefaultMutableTreeNode rootNode;
    private final List<Category> categories;

    public ViewProvider(String dataFile) {
        dataProvider = new DataProvider();
        categories = dataProvider.getCategories(dataFile);
        rootNode = dataProvider.createRootTreeNode(categories);
        initSamplesMap();
    }

    public SampleInfo getSample(String sampleName) {
        return samples.get(sampleName);
    }

    public DefaultMutableTreeNode getRootNode() {
        return rootNode;
    }

    private void initSamplesMap() {
        samples = new HashMap<>();
        for (Category category : categories) {
            for (SampleInfo sampleInfo : category.getSampleInfo()) {
                samples.put(sampleInfo.getName(), sampleInfo);
            }
        }
    }
}
