package com.example.myapplication.model;

public class CartModel {
    private String nameItem;
    private String priceItem;
    private String salePriceItem;
    private String imageItem;
    private int amount;

    public CartModel(String nameItem, String priceItem, String salePriceItem, String imageItem, int amount) {
        this.nameItem = nameItem;
        this.priceItem = priceItem;
        this.salePriceItem = salePriceItem;
        this.imageItem = imageItem;
        this.amount = amount;
    }

    public String getNameItem() {
        return nameItem;
    }

    public String getPriceItem() {
        return priceItem;
    }

    public String getSalePriceItem() {
        return salePriceItem;
    }

    public String getImageItem() {
        return imageItem;
    }

    public int getAmount() {
        return amount;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public void setPriceItem(String priceItem) {
        this.priceItem = priceItem;
    }

    public void setSalePriceItem(String salePriceItem) {
        this.salePriceItem = salePriceItem;
    }

    public void setImageItem(String imageItem) {
        this.imageItem = imageItem;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
