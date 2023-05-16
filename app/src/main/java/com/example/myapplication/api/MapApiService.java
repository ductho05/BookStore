package com.example.myapplication.api;

import com.example.myapplication.constants.AppConstants;
import com.example.myapplication.model.NominatimResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MapApiService {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    MapApiService apiService = new Retrofit.Builder()
            .baseUrl(AppConstants.API_MAPS)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MapApiService.class);

    @GET("search")
    Call<List<NominatimResult>> getCoordinates(@Query("q") String address,
                                               @Query("format") String format,
                                               @Query("limit") int limit);
}
