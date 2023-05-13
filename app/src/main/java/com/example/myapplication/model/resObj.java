package com.example.myapplication.model;

import java.io.Serializable;
import java.util.List;

public class resObj implements Serializable {
    private String status;
    private String message;
    private List<Product> data;

    public resObj(String status, String message, List<Product> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}
