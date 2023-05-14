package com.example.myapplication.model;

import java.io.Serializable;
import java.util.List;

public class resObj<T> implements Serializable {
    private String status;
    private String message;
    private T data;

    public resObj(String status, String message, T data) {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
