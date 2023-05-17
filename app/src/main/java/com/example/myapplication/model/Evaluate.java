package com.example.myapplication.model;

import java.io.Serializable;

public class Evaluate implements Serializable {
    private String _id;
    private double rate;
    private String comment;
    private Product product;
    private User user;
    private String createdAt;

    public Evaluate() {
    }

    public Evaluate(double rate, String comment, Product product, User user, String createdAt) {
        this.rate = rate;
        this.comment = comment;
        this.product = product;
        this.user = user;
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
