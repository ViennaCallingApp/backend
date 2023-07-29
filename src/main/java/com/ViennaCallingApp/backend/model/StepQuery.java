package com.ViennaCallingApp.backend.model;

public class StepQuery {

    String from;
    String startTime;
    String mode;
    String lineNumber;
    String direction;
    String to;
    String endTime;

    public StepQuery() {
    }

    public StepQuery(String from, String startTime, String mode, String lineNumber, String direction, String to, String endTime) {
        this.from = from;
        this.startTime = startTime;
        this.mode = mode;
        this.lineNumber = lineNumber;
        this.direction = direction;
        this.to = to;
        this.endTime = endTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
