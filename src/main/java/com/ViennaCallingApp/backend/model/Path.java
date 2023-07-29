package com.ViennaCallingApp.backend.model;

import java.util.HashMap;
import java.util.Map;

public class Path {

    private Map<String, Step> paths;

    public Path() {
        paths = new HashMap<>();
    }

    public void addStep(Step step) {
        paths.put(step.getTitle(), step);
    }

    public Map<String, Step> getSteps() {
        return paths;
    }

}
