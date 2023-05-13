package com.example.myapplication.model;

public class CartModel {
    private String nameItem;
    private String originalPriceItem;
    private String salePriceItem;
    private String imageItem;
    private int amount;

    public CartModel(String nameItem, String priceItem, String salePriceItem, String imageItem, int amount) {
        this.nameItem = nameItem;
        this.originalPriceItem = priceItem;
        this.salePriceItem = salePriceItem;
        this.imageItem = imageItem;
        this.amount = amount;
    }

    public String getNameItem() {
        return nameItem;
    }

    public String getOriginalPriceItem() {
        return originalPriceItem;
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

    public void setOriginalPriceItem(String originalPriceItem) {
        this.originalPriceItem = originalPriceItem;
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

    public int getAmount(int amount) {
        return  amount;
    }
}
