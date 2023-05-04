package com.example.myapplication.api;

import com.example.myapplication.model.resObj;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    // url api: http://127.0.0.1:3000/bookstore/api/v1/

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://127.0.0.1:3000/bookstore/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @POST("users/")
    Call<resObj> getUserByEmail(@Query("email") String email);
}
