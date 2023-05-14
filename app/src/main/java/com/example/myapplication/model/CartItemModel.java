package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CartItemModel implements Parcelable {
    private String _id;
    private int quantity;
    private Product product;
    private Cart cart;
    private String createdAt;
    private String updatedAt;

    private boolean checked;

    public CartItemModel() {
    }

    public CartItemModel(int quantity, Product product, Cart cart, String createdAt, String updatedAt) {
        this.quantity = quantity;
        this.product = product;
        this.cart = cart;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected CartItemModel(Parcel in) {
        _id = in.readString();
        quantity = in.readInt();
        createdAt = in.readString();
        updatedAt = in.readString();
        product = in.readParcelable(Product.class.getClassLoader());
        cart = in.readParcelable(Cart.class.getClassLoader());
    }

    public static final Creator<CartItemModel> CREATOR = new Creator<CartItemModel>() {
        @Override
        public CartItemModel createFromParcel(Parcel in) {
            return new CartItemModel(in);
        }

        @Override
        public CartItemModel[] newArray(int size) {
            return new CartItemModel[size];
        }
    };

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeInt(quantity);
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
        parcel.writeParcelable(product, i);
        parcel.writeParcelable(cart, i);
    }

}