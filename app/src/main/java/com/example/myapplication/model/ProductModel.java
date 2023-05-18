package com.example.myapplication.model;

import java.io.Serializable;

public class ProductModel implements Serializable {
    private String _id;
    public String title;
    public String author;
    public String published_date;
    public double price;
    public double old_price;
    public double rate;
    public int sold;
    public String desciption;
    public String status;
    public String images;
    public String categoryId;

    public ProductModel() {
    }

    public ProductModel(String title, String author, String published_date, double price, double old_price, double rate, int sold, String desciption, String status, String images, String categoryId) {
        this.title = title;
        this.author = author;
        this.published_date = published_date;
        this.price = price;
        this.old_price = old_price;
        this.rate = rate;
        this.sold = sold;
        this.desciption = desciption;
        this.status = status;
        this.images = images;
        this.categoryId = categoryId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOld_price() {
        return old_price;
    }

    public void setOld_price(double old_price) {
        this.old_price = old_price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
