package com.example.myapplication.model;

public class CouponModel {
    private String nameCoupon;
    private String rangeApply;
    private String dateExpired;
    private String hourExpired;
    private String couponCode;

    public void setNameCoupon(String nameCoupon) {
        this.nameCoupon = nameCoupon;
    }

    public void setRangeApply(String rangeApply) {
        this.rangeApply = rangeApply;
    }

    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }

    public void setHourExpired(String hourExpired) {
        this.hourExpired = hourExpired;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getNameCoupon() {
        return nameCoupon;
    }

    public String getRangeApply() {
        return rangeApply;
    }

    public String getDateExpired() {
        return dateExpired;
    }

    public String getHourExpired() {
        return hourExpired;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public CouponModel(String nameCoupon, String rangeApply, String dateExpired, String hourExpired, String couponCode) {
        this.nameCoupon = nameCoupon;
        this.rangeApply = rangeApply;
        this.dateExpired = dateExpired;
        this.hourExpired = hourExpired;
        this.couponCode = couponCode;
    }
}
