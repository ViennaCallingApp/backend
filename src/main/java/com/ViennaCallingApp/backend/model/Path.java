package com.ViennaCallingApp.backend.model;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private List<Step> paths;

    public Path() {
        paths = new ArrayList<>();
    }

    public void addStep(Step step) {
        paths.add(step);
    }

    public List<Step> getSteps() {
        return paths;
    }

}
