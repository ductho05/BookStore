package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Category implements Parcelable {
    public String _id;
    public String name;
    public String status;
    public int __v;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public Category(String _id, String name, String status, int __v) {
        this._id = _id;
        this.name = name;
        this.status = status;
        this.__v = __v;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeString(status);
        dest.writeInt(__v);
    }

    protected Category(Parcel in) {
        _id = in.readString();
        name = in.readString();
        status = in.readString();
        __v = in.readInt();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
