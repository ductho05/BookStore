package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class OrderItemModel implements Serializable, Parcelable {
    private String _id;
    private int quantity;
    private float price;

    private String status;
    private Order order;
    private Product product;

    public OrderItemModel() {
    }

    public OrderItemModel(int quantity, float price, Order order, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.product = product;
    }

    protected OrderItemModel(Parcel in) {
        _id = in.readString();
        quantity = in.readInt();
        price = in.readFloat();
        status = in.readString();
        order = in.readParcelable(Order.class.getClassLoader());
        product = in.readParcelable(Product.class.getClassLoader());
    }

    public static final Creator<OrderItemModel> CREATOR = new Creator<OrderItemModel>() {
        @Override
        public OrderItemModel createFromParcel(Parcel in) {
            return new OrderItemModel(in);
        }

        @Override
        public OrderItemModel[] newArray(int size) {
            return new OrderItemModel[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeInt(quantity);
        parcel.writeFloat(price);
        parcel.writeString(status);
        parcel.writeParcelable(order, i);
        parcel.writeParcelable(product, i);
    }
}
