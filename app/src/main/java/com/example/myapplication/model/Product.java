package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Product implements Serializable, Parcelable {
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
    public Category categoryId;

    public Product() {
    }

    public Product(String _id, String title, String author, String published_date, double price, double old_price, double rate, int sold, String desciption, String status, String images, Category categoryId) {
        this._id = _id;
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

    protected Product(Parcel in) {
        _id = in.readString();
        title = in.readString();
        author = in.readString();
        published_date = in.readString();
        price = in.readDouble();
        old_price = in.readDouble();
        rate = in.readDouble();
        sold = in.readInt();
        desciption = in.readString();
        status = in.readString();
        images = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    public String getDescription() {
        return desciption;
    }

    public void setDescription(String description) {
        this.desciption = description;
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

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(title);
        parcel.writeString(author);
        parcel.writeString(published_date);
        parcel.writeDouble(price);
        parcel.writeDouble(old_price);
        parcel.writeDouble(rate);
        parcel.writeInt(sold);
        parcel.writeString(desciption);
        parcel.writeString(status);
        parcel.writeString(images);
    }
}