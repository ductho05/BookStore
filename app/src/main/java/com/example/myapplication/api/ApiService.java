package com.example.myapplication.api;


import com.example.myapplication.Seller.Model.RateModel;
import com.example.myapplication.model.Cart;
import com.example.myapplication.model.CartItem;
import com.example.myapplication.model.CartItemModel;
import com.example.myapplication.model.CartModel;
import com.example.myapplication.model.Evaluate;
import com.example.myapplication.model.EvaluateModel;
import com.example.myapplication.model.Favorite;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.OrderItem;

import com.example.myapplication.model.Category;

import com.example.myapplication.model.OrderItemModel;
import com.example.myapplication.model.OrderModel;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.ProductModel;
import com.example.myapplication.model.User;
import com.example.myapplication.model.resObj;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    // url api: http://127.0.0.1:3000/bookstore/api/v1/

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    ApiService apiService = new Retrofit.Builder()

            //.baseUrl("http://192.168.1.73:3000/bookstore/api/v1/") // becoffe

            //.baseUrl("http://192.168.1.73:3000/bookstore/api/v1/") // becoffe
            //.baseUrl("http://192.168.2.13:3000/bookstore/api/v1/") // Cổng dành cho Wifi nhà

            //.baseUrl("http://192.168.1.73:3000/bookstore/api/v1/") // becoffe
            //.baseUrl("http://192.168.1.123:3000/bookstore/api/v1/") // becoffe
            //.baseUrl("http://192.168.2.13:3000/bookstore/api/v1/") // Cổng dành cho Wifi nhà
            //.baseUrl("http://192.168.43.204:3000/bookstore/api/v1/") // Cổng dành cho Mạng Thọ
            .baseUrl("http://192.168.239.29:3000/bookstore/api/v1/")// Cổng dành cho Mạng

           //.baseUrl("http://192.168.1.73:3000/bookstore/api/v1/") // becoffe
            //.baseUrl("http://192.168.2.13:3000/bookstore/api/v1/") // Cổng dành cho Wifi nhà
            //.baseUrl("http://192.168.43.204:3000/bookstore/api/v1/") // Cổng dành cho Mạng
            //.baseUrl("http://192.168.1.73:3000/bookstore/api/v1/") // becoffe
            //.baseUrl("http://192.168.1.123:3000/bookstore/api/v1/") // becoffe
            //.baseUrl("http://192.168.2.13:3000/bookstore/api/v1/") // Cổng dành cho Wifi nhà
            //.baseUrl("http://192.168.43.204:3000/bookstore/api/v1/") // Cổng dành cho Mạng Thọ
            //.baseUrl("http://192.168.47.147:3000/bookstore/api/v1/")// Cổng dành cho Mạng
            //.baseUrl("http://192.168.1.30:3000/bookstore/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @POST("users/")
    Call<resObj<User>> getUserByEmail(@Query("email") String email);
    @GET("users/{id}")
    Call<resObj<User>> getUserById(@Path("id") String id);

    @POST("users/insert")
    Call<resObj<User>> registerAccount(@Body User User);

    @POST("orders/insert")
    Call<resObj<Order>> addOrder(@Body Order order);

    @POST("orderitems/insert")
    Call<OrderItem> addOrderItem(@Body OrderItem orderItem);

    @POST("orderitems/order")
    Call<resObj<List<OrderItemModel>>> getOrderItemByOrder(@Query("id") String id);



    // update orderitem
    @PUT("orderitems/update/{id}")
    Call<resObj<OrderItem>> updateOrderItem(@Path("id") String id, @Body OrderItem orderItem);

    @GET("products/id/{id}")
    Call<resObj<Product>> getProductById(@Path("id") String _id);

    // FAVORITE ***********************************

    // thêm sản phẩm vào danh sách yêu thích
    @POST("favorites/add")
    Call<resObj<Favorite>> addFavorite(@Body Favorite favorite);

    // Lấy danh sách sản phẩm yêu thích
    @GET("favorites")
    Call<resObj<List<Favorite>>> getFavorite(@Query("userid") String userid, @Query("productid") String productid);




    // Check sản phẩm đã có trong danh sách yêu thích chưa
    @GET("favorites/check")
    Call<resObj<Favorite>> checkFavorite(@Query("userid") String userid, @Query("productid") String productid);
    // Xóa sản phẩm khỏi danh sách yêu thích
    @POST("favorites/delete")
    Call<resObj<Favorite>> deleteFavorite(@Body Favorite favorite);


    // ORDER ******************************************
    @GET("orders")
    Call<resObj<List<OrderModel>>> getAllOrder(@Query("user") String user,
                                               @Query("status") String status);

    @GET("orders/{id}")
    Call<resObj<OrderModel>> getOrderById(@Path("id") String id);
    // HOME ***********************************

    // hiển thị 8 sách mới nhất
    @GET("products/new/{num}")
    Call<resObj<ArrayList<Product>>> getNewProduct(@Path("num") String num);

    // hiển thị 8 sách bán chạy nhất
    @GET("products/bestseller/{num})")
    Call<resObj<ArrayList<Product>>> getBestSellerProduct(@Path("num") String num);

    @POST("products/add")
    Call<resObj<ProductModel>> InsertProduct(@Body ProductModel product);

    // hiển thị 8 sách bán rẻ nhất
    @GET("products/sale/{num}")
    Call<resObj<ArrayList<Product>>> getLowestProduct(@Path("num") String num);


    // Tìm sản phẩm theo tên:
    @GET("products/title/{title}")
    Call<resObj<List<Product>>> getProductByTitle(@Path("title") String title,
                                                  @Query("num") int num
    );

    // ADMIN ***********************************
    // Đếm số lượng sản phẩm có rate = 5
    @GET("products/rate")
    Call<resObj<List<RateModel>>> countProductByRate();

    // Phân loại đơn hàng theo trạng thái
    @GET("orders/status")
    Call<resObj<List<OrderModel>>> countOrderByStatus(
            @Query("status") String status
    );



    //    Lấy  sản phẩm theo category
    @POST("products/category")
    Call<resObj<List<Product>>> getProductByCategory(@Query("category") String categoryId, @Query("limit") int limit);

    // Lọc sản phẩm của từng danh mục
    @GET("products")
    Call<resObj<List<Product>>> getFilterProduct(@Query("category") String category,
                                                 @Query("filter") String filter,
                                                 @Query("page") int page,
                                                 @Query("perPage") int perPage,
                                                 @Query("sort") String sort,
                                                 @Query("num") int num,
                                                 @Query("title") String title);
    //    Cart
    @POST("cart/user")
    Call<resObj<CartModel>> getCartByUser(@Query("_id") String user);



    // Tìm sản phẩm theo danh mục:
    @GET("products/category/{category}")
    Call<resObj<List<Product>>> getProductByCategory(@Path("category") String category
    );

    // Lọc sản phẩm của từng danh mục



    @POST("cart/add")
    Call<resObj<Cart>> addCart(@Body Cart cart);

    // Cart Items
    @POST("cartitems/product")
    Call<resObj<CartItemModel>> getCartItemByProduct(@Query("product") String product);

    @POST("cartitems/add")
    Call<resObj<CartItem>> addCartItem(@Body CartItem cartItem);

    // Trang Category
    @GET("categories/")
    Call<resObj<List<Category>>> getAllCategory();


    @POST("cartitems/user")
    Call<resObj<List<CartItemModel>>> getAllCartItemByUser(@Query("id") String id);

    @POST("cartitems/delete")
    Call<resObj<String>> deleteCartItem(@Query("id") String id);

    @PUT("cartitems/update/{id}")
    Call<resObj<String>> updateCartItem(@Path("id") String id, @Body CartItemModel cartItem);

    // Evaluate
    @POST("evaluates/product")
    Call<resObj<List<Evaluate>>> getEvaluateByProduct(@Query("_id") String id);

    @POST("evaluates/insert")
    Call<resObj<EvaluateModel>> addEvaluate(@Body EvaluateModel evaluate);
}
