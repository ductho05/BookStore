package com.example.myapplication.model;

import java.util.ArrayList;
import java.util.Objects;

<<<<<<< HEAD
public class resObj<T>{
=======
public class resObj<T> implements Serializable {
>>>>>>> 1133d06bfa8a2b244f3710588b41e5f011c23c65
    private String status;
    private String message;
    private T data;

<<<<<<< HEAD
    public resObj() {
    }

=======
>>>>>>> 1133d06bfa8a2b244f3710588b41e5f011c23c65
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

    public Boolean isSuccess() {
        return Objects.equals(status, "OK");
    }
}
