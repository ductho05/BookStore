package com.example.myapplication.api;

<<<<<<< HEAD
import com.example.myapplication.model.Cart;
import com.example.myapplication.model.CartItem;
import com.example.myapplication.model.CartItemModel;
import com.example.myapplication.model.CartModel;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.OrderItem;
=======
import com.example.myapplication.model.Category;
>>>>>>> 1133d06bfa8a2b244f3710588b41e5f011c23c65
import com.example.myapplication.model.Product;
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
            .baseUrl("http://192.168.1.123:3000/bookstore/api/v1/") // becoffe
            //.baseUrl("http://192.168.2.13:3000/bookstore/api/v1/") // Cổng dành cho Wifi nhà
            //.baseUrl("http://192.168.47.147:3000/bookstore/api/v1/") // Cổng dành cho Mạng
            //.baseUrl("http://192.168.1.30:3000/bookstore/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @POST("users/")
    Call<resObj<User>> getUserByEmail(@Query("email") String email);
    @POST("users/id")
    Call<resObj<User>> getUserById(@Query("id") String id);

    @POST("orders/insert")
    Call<resObj<Order>> addOrder(@Body Order order);

    @POST("orderitems/insert")
    Call<OrderItem> addOrderItem(@Body OrderItem orderItem);

    @GET("products/{id}")
    Call<resObj<Product>> getProductById(@Path("id") String _id);

    // Trang Home

    // hiển thị 8 sách mới nhất
    @GET("products/new/{num}")
<<<<<<< HEAD
    Call<resObj<ArrayList<Product>>> getNewProduct(@Path("num") String num);

    // hiển thị 8 sách bán chạy nhất
    @GET("products/bestseller/{num})")
    Call<resObj<ArrayList<Product>>> getBestSellerProduct(@Path("num") String num);

    // hiển thị 8 sách bán rẻ nhất
    @GET("products/sale/{num}")
    Call<resObj<ArrayList<Product>>> getLowestProduct(@Path("num") String num);
=======
    Call<resObj<List<Product>>> getNewProduct(@Path("num") String num);

    // hiển thị 8 sách bán chạy nhất
    @GET("products/bestseller/{num})")
    Call<resObj<List<Product>>> getBestSellerProduct(@Path("num") String num);

    // hiển thị 8 sách bán rẻ nhất
    @GET("products/sale/{num}")
    Call<resObj<List<Product>>> getLowestProduct(@Path("num") String num);
>>>>>>> 1133d06bfa8a2b244f3710588b41e5f011c23c65

    // Tìm sản phẩm theo tên:
    @GET("products/title/{title}")
    Call<resObj<List<Product>>> getProductByTitle(@Path("title") String title,
<<<<<<< HEAD
                                                  @Query("num") int num
    );

    //    Lấy  sản phẩm theo category
    @POST("products/category")
    Call<resObj<List<Product>>> getProductByCategory(@Query("category") String categoryId, @Query("limit") int limit);

    // Lọc sản phẩm của từng danh mục
    @GET("products/")
    Call<resObj<List<Product>>> getFilterProduct(@Query("category") String category,
                                                 @Query("filter") String filter,
                                                 @Query("page") int page,
                                                 @Query("perPage") int perPage,
                                                 @Query("sort") String sort,
                                                 @Query("num") int num);
    //    Cart
    @POST("cart/user")
    Call<resObj<CartModel>> getCartByUser(@Query("_id") String user);
=======
                                   @Query("num") int num
                                   );


    // Tìm sản phẩm theo danh mục:
    @GET("products/category/{category}")
    Call<resObj<List<Product>>> getProductByCategory(@Path("category") String category
                                      );

    // Lọc sản phẩm của từng danh mục
    @GET("products")
    Call<resObj<List<Product>>> getFilterProduct(@Query("category") String category,
                                  @Query("filter") String filter,
                                  @Query("page") int page,
                                  @Query("perPage") int perPage,
                                  @Query("sort") String sort,
                                  @Query("num") int num);
>>>>>>> 1133d06bfa8a2b244f3710588b41e5f011c23c65

    @POST("cart/add")
    Call<resObj<Cart>> addCart(@Body Cart cart);

    // Cart Items
    @POST("cartitems/product")
    Call<resObj<CartItemModel>> getCartItemByProduct(@Query("product") String product);

<<<<<<< HEAD
    @POST("cartitems/add")
    Call<resObj<CartItem>> addCartItem(@Body CartItem cartItem);
=======
    // Trang Category
    @GET("categories/")
    Call<resObj<List<Category>>> getAllCategory();
>>>>>>> 1133d06bfa8a2b244f3710588b41e5f011c23c65

    @POST("cartitems/user")
    Call<resObj<List<CartItemModel>>> getAllCartItemByUser(@Query("id") String id);

    @POST("cartitems/delete")
    Call<resObj<String>> deleteCartItem(@Query("id") String id);

    @PUT("cartitems/update/{id}")
    Call<resObj<String>> updateCartItem(@Path("id") String id, @Body CartItemModel cartItem);
}
