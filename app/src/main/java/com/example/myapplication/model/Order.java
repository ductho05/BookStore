package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Order implements Serializable, Parcelable {
    private String _id;
    private String name;
    private String address;
    private String city;
    private String phone;
    private int quantity;
    private float price;
    private float shippingCost;
    private String deliveryDate;
    private String message;
    private Boolean pid;
    private String user;
    private String payment_method;
    private String shipping_method;
    private String status;
    private String createdAt;
    private String updatedAt;

    public Order() {
    }

    public Order(String name, String address, String city, String phone, int quantity, float price, float shippingCost, String deliveryDate, String message, Boolean pid, String user, String payment_method, String shipping_method, String status, String createdAt, String updatedAt) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.quantity = quantity;
        this.price = price;
        this.shippingCost = shippingCost;
        this.deliveryDate = deliveryDate;
        this.message = message;
        this.pid = pid;
        this.user = user;
        this.payment_method = payment_method;
        this.shipping_method = shipping_method;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected Order(Parcel in) {
        _id = in.readString();
        name = in.readString();
        address = in.readString();
        city = in.readString();
        phone = in.readString();
        quantity = in.readInt();
        price = in.readFloat();
        shippingCost = in.readFloat();
        deliveryDate = in.readString();
        message = in.readString();
        byte tmpPid = in.readByte();
        pid = tmpPid == 0 ? null : tmpPid == 1;
        user = in.readString();
        payment_method = in.readString();
        shipping_method = in.readString();
        status = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getShipping_method() {
        return shipping_method;
    }

    public void setShipping_method(String shipping_method) {
        this.shipping_method = shipping_method;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(float shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getPid() {
        return pid;
    }

    public void setPid(Boolean pid) {
        this.pid = pid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(phone);
        parcel.writeInt(quantity);
        parcel.writeFloat(price);
        parcel.writeFloat(shippingCost);
        parcel.writeString(deliveryDate);
        parcel.writeString(message);
        parcel.writeByte((byte) (pid == null ? 0 : pid ? 1 : 2));
        parcel.writeString(user);
        parcel.writeString(payment_method);
        parcel.writeString(shipping_method);
        parcel.writeString(status);
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
    }
}
