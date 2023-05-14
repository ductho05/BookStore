package com.example.myapplication.api;

import com.example.myapplication.model.Product;
import com.example.myapplication.model.resObj;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    // url api: http://127.0.0.1:3000/bookstore/api/v1/

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.131:3000/bookstore/api/v1/") // becoffe
            //.baseUrl("http://192.168.2.13:3000/bookstore/api/v1/") // Cổng dành cho Wifi nhà
            //.baseUrl("http://192.168.47.147:3000/bookstore/api/v1/") // Cổng dành cho Mạng
            //.baseUrl("http://192.168.1.30:3000/bookstore/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    // Trang Home

    // hiển thị 8 sách mới nhất
    @GET("products/new/{num}")
    Call<resObj> getNewProduct(@Path("num") String num);

    // hiển thị 8 sách bán chạy nhất
    @GET("products/bestseller/{num})")
    Call<resObj> getBestSellerProduct(@Path("num") String num);

    // hiển thị 8 sách bán rẻ nhất
    @GET("products/sale/{num}")
    Call<resObj> getLowestProduct(@Path("num") String num);

    // Tìm sản phẩm theo tên:
    @GET("products/title/{title}")
    Call<resObj> getProductByTitle(@Path("title") String title,
                                   @Query("num") int num
                                   );


    // Tìm sản phẩm theo danh mục:
    @GET("products/category/{category}")
    Call<resObj> getProductByCategory(@Path("category") String category
                                      );

    // Lọc sản phẩm của từng danh mục
    @GET("products")
    Call<resObj> getFilterProduct(@Query("category") String category,
                                  @Query("filter") String filter,
                                  @Query("page") int page,
                                  @Query("perPage") int perPage,
                                  @Query("sort") String sort,
                                  @Query("num") int num);



    // Trang Category
    @GET("categories/")
    Call<resObj> getAllCategory();

    @POST("users/")
    Call<resObj> getUserByEmail(@Query("email") String email);
}
