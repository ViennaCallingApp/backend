package com.ViennaCallingApp.backend.model;

public class Step {

    private String title;
    private Box overview;
    private Box route;
    private Box alternative;
    private StepQuery stepQueryFrom;
    private StepQuery stepQueryTo;

    public Step() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Box getOverview() {
        return overview;
    }

    public void setOverview(Box overview) {
        this.overview = overview;
    }

    public Box getRoute() {
        return route;
    }

    public void setRoute(Box route) {
        this.route = route;
    }

    public Box getAlternative() {
        return alternative;
    }

    public void setAlternative(Box alternative) {
        this.alternative = alternative;
    }

    public StepQuery getStepQueryFrom() {
        return stepQueryFrom;
    }

    public void setStepQueryFrom(StepQuery stepQueryFrom) {
        this.stepQueryFrom = stepQueryFrom;
    }

    public StepQuery getStepQueryTo() {
        return stepQueryTo;
    }

    public void setStepQueryTo(StepQuery stepQueryTo) {
        this.stepQueryTo = stepQueryTo;
    }
}
