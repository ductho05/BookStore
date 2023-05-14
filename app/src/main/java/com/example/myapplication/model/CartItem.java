package com.example.myapplication.model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String _id;
    private int quantity;
    private String product;
    private String cart;
    public CartItem() {
    }

    public CartItem(int quantity, String product, String cart) {
        this.quantity = quantity;
        this.product = product;
        this.cart = cart;
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }
}
