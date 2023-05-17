package com.example.myapplication.model;

public class NotificationModel {
    private String notify;
    private String dateNotify;
    private String descriptionNotify;

    public NotificationModel(String notify, String dateNotify, String descriptionNotify) {
        this.notify = notify;
        this.dateNotify = dateNotify;
        this.descriptionNotify = descriptionNotify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public void setDateNotify(String dateNotify) {
        this.dateNotify = dateNotify;
    }

    public void setDescriptionNotify(String descriptionNotify) {
        this.descriptionNotify = descriptionNotify;
    }

    public String getNotify() {
        return notify;
    }

    public String getDateNotify() {
        return dateNotify;
    }

    public String getDescriptionNotify() {
        return descriptionNotify;
    }
}
