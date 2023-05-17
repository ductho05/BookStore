package com.example.myapplication.model;

import java.io.Serializable;

public class EvaluateModel implements Serializable {
    private String _id;
    private double rate;
    private String comment;
    private String product;
    private String user;

    public EvaluateModel() {
    }

    public EvaluateModel(double rate, String comment, String product, String user) {
        this.rate = rate;
        this.comment = comment;
        this.product = product;
        this.user = user;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
