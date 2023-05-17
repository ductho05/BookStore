package com.example.myapplication.model;

public class Slide {
    private int resourceId;
    private String resourceDescription;
    private String resourceName;

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Slide(int resourceId, String resourceDescription, String resourceName) {
        this.resourceId = resourceId;
        this.resourceDescription=resourceDescription;
        this.resourceName = resourceName;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}