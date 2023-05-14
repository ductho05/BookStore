package com.example.myapplication.activity.productdetail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.cart.CartActivity;
import com.example.myapplication.activity.home.CategoryActivity;
import com.example.myapplication.activity.home.HomeActivity;
import com.example.myapplication.activity.home.SearchActivity;
import com.example.myapplication.adapter.ProductDetailAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.Cart;
import com.example.myapplication.model.CartItem;
import com.example.myapplication.model.CartItemModel;
import com.example.myapplication.model.CartModel;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.User;
import com.example.myapplication.model.resObj;
import com.google.android.material.appbar.AppBarLayout;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView btn_pdetail_back;
    TextView btn_pdetail_search;
    ImageView btn_pdetail_home;
    ImageView btn_pdetail_cart;
    ImageView btn_heart;
    TextView tv_discount_price;
    ImageView btn_add_to_cart;

    ImageView images;
    TextView title;
    TextView price;
    TextView discount;
    ImageView btn_plus;
    ImageView btn_minus;
    TextView quantity, id, author, year_publish, description,title2, btn_seeMoreIntro, btn_seeMoreCare, btn_seeMoreInfo;
    TextView btn_buyNow, quantityCart;
    private RecyclerView rv_productIntro;
    private RecyclerView rv_readerCare;
    private ProductDetailAdapter productDetailAdapter1;
    private ProductDetailAdapter productDetailAdapter2;
    private List<Product> productList1;
    private List<Product> productList2;
    Product product = new Product();
    // Set user cứng:
    String user = "64477d8318a87d6e84a366d0";
    User newUser = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        AnhXa();
        SetFrontEnd();
        Intent intent = getIntent();
        String _id = intent.getStringExtra("_id");
        displayProduct(_id);
        setProductIntro();
        setProductCare(_id);
        setQuantityCart();
        btn_pdetail_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supportFinishAfterTransition();
                ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(ProductDetailActivity.this, R.anim.animation_down, R.anim.no_animation);
                Intent intent = new Intent(ProductDetailActivity.this, SearchActivity.class);
                startActivity(intent, options.toBundle());
            }
        });

        btn_pdetail_home.setOnClickListener(view -> {
            supportFinishAfterTransition();
            Intent intent1 = new Intent(ProductDetailActivity.this, HomeActivity.class);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(ProductDetailActivity.this);
            startActivity(intent1, optionsCompat.toBundle());
        });

        btn_pdetail_back.setOnClickListener(view -> {
            supportFinishAfterTransition();
            Intent intent1 = new Intent(ProductDetailActivity.this, HomeActivity.class);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(ProductDetailActivity.this);
            startActivity(intent1, optionsCompat.toBundle());
        });

        btn_plus.setOnClickListener(view -> {
            int quantityCurrent = Integer.parseInt(quantity.getText().toString());
            quantityCurrent += 1;
            quantity.setText(String.valueOf(quantityCurrent));
            checkQuantity();
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkQuantity();
                int quantityCurrent = Integer.parseInt(quantity.getText().toString());
                quantityCurrent -= 1;
                quantity.setText(String.valueOf(quantityCurrent));
                checkQuantity();
            }
        });
        btn_buyNow.setOnClickListener(view -> { ApiService.apiService.getCartByUser(user).enqueue(new Callback<resObj<CartModel>>() {
            @Override
            public void onResponse(Call<resObj<CartModel>> call, Response<resObj<CartModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Đã có cart
                    Log.e("Kq: ", "Đã có cart");
                    CartModel cart = response.body().getData();
                    CartItem cartItem = getCartItem();
                    cartItem.setCart(cart.get_id());
                    ApiService.apiService.getCartItemByProduct(cartItem.getProduct())
                            .enqueue(new Callback<resObj<CartItemModel>>() {
                                @Override
                                public void onResponse(Call<resObj<CartItemModel>> call, Response<resObj<CartItemModel>> response) {
                                    if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                                        // Đã có sản phẩm trong giỏ hàng
                                        Toast.makeText(getApplicationContext(), "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_LONG).show();
                                    } else {
                                        // Thêm vào giỏ hàng
                                        AddToCartNow(cartItem);
                                    }
                                }
                                @Override
                                public void onFailure(Call<resObj<CartItemModel>> call, Throwable t) {}
                            });
                } else {
                    // Chưa có cart
                    Cart cart = getCart();
                    ApiService.apiService.addCart(cart).enqueue(new Callback<resObj<Cart>>() {
                        @Override
                        public void onResponse(Call<resObj<Cart>> call, Response<resObj<Cart>> response) {
                            if (response.isSuccessful() && response.body().isSuccess() && response.body() != null) {
                                CartItem cartItem = getCartItem();
                                Cart cart = response.body().getData();
                                cartItem.setCart(cart.get_id());
                                AddToCartNow(cartItem);
                            }
                        }
                        @Override
                        public void onFailure(Call<resObj<Cart>> call, Throwable t) {}
                    });
                }
            }
            @Override
            public void onFailure(Call<resObj<CartModel>> call, Throwable t) {
                Log.e("lỗi: ", "Không thành công");
            }
        });

        });
        btn_add_to_cart.setOnClickListener(view -> ApiService.apiService.getCartByUser(user).enqueue(new Callback<resObj<CartModel>>() {
            @Override
            public void onResponse(Call<resObj<CartModel>> call, Response<resObj<CartModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Đã có cart
                    Log.e("Kq: ", "Đã có cart");
                    CartModel cart = response.body().getData();
                    CartItem cartItem = getCartItem();
                    cartItem.setCart(cart.get_id());
                    ApiService.apiService.getCartItemByProduct(cartItem.getProduct())
                            .enqueue(new Callback<resObj<CartItemModel>>() {
                                @Override
                                public void onResponse(Call<resObj<CartItemModel>> call, Response<resObj<CartItemModel>> response) {
                                    if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                                        // Đã có sản phẩm trong giỏ hàng
                                        Toast.makeText(getApplicationContext(), "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_LONG).show();
                                    } else {
                                        // Thêm vào giỏ hàng
                                        AddToCart(cartItem);
                                    }
                                }
                                @Override
                                public void onFailure(Call<resObj<CartItemModel>> call, Throwable t) {
                                    Log.e("Xem sản phẩm có trong giỏ: ", t.getMessage());
                                }
                            });
                } else {
                    // Chưa có cart
                    Cart cart = getCart();
                    ApiService.apiService.addCart(cart).enqueue(new Callback<resObj<Cart>>() {
                        @Override
                        public void onResponse(Call<resObj<Cart>> call, Response<resObj<Cart>> response) {
                            if (response.isSuccessful() && response.body().isSuccess() && response.body() != null) {
                                CartItem cartItem = getCartItem();
                                Cart cart = response.body().getData();
                                cartItem.setCart(cart.get_id());
                                AddToCart(cartItem);
                            }
                        }
                        @Override
                        public void onFailure(Call<resObj<Cart>> call, Throwable t) {}
                    });
                }
            }
            @Override
            public void onFailure(Call<resObj<CartModel>> call, Throwable t) {
                Log.e("lỗi: ", "Không thành công");
            }
        }));
    }

    private void setQuantityCart() {
        ApiService.apiService.getAllCartItemByUser(user).enqueue(new Callback<resObj<List<CartItemModel>>>() {
            @Override
            public void onResponse(Call<resObj<List<CartItemModel>>> call, Response<resObj<List<CartItemModel>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CartItemModel> list = response.body().getData();
                    if (list != null) {
                        String quantityCartItem = String.valueOf(list.size() + 1);
                        quantityCart.setText(quantityCartItem);
                    }
                }
            }

            @Override
            public void onFailure(Call<resObj<List<CartItemModel>>> call, Throwable t) {
                Log.e("QuantityCart Item: ", t.getMessage());
            }
        });
    }

    private void setProductCare(String id) {
        ApiService.apiService.getProductById(id).enqueue(new Callback<resObj<Product>>() {
            @Override
            public void onResponse(Call<resObj<Product>> call, Response<resObj<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Product product = response.body().getData();
                    String categoryId = product.getCategoryId().get_id();

                    ApiService.apiService.getProductByCategory(categoryId, 8).enqueue(new Callback<resObj<List<Product>>>() {
                        @Override
                        public void onResponse(Call<resObj<List<Product>>> call, Response<resObj<List<Product>>> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                productList2 = response.body().getData();
                                productDetailAdapter2 = new ProductDetailAdapter(productList2, ProductDetailActivity.this);
                                rv_readerCare.setHasFixedSize(true);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                                rv_readerCare.setLayoutManager(linearLayoutManager);
                                rv_readerCare.setAdapter(productDetailAdapter2);
                            }
                        }

                        @Override
                        public void onFailure(Call<resObj<List<Product>>> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<resObj<Product>> call, Throwable t) {

            }
        });
    }

    private void setProductIntro() {
        ApiService.apiService.getNewProduct("8").enqueue(new Callback<resObj<ArrayList<Product>>>() {
            @Override
            public void onResponse(Call<resObj<ArrayList<Product>>> call, Response<resObj<ArrayList<Product>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList1 = response.body().getData();
                    productDetailAdapter1 = new ProductDetailAdapter(productList1, ProductDetailActivity.this);
                    rv_productIntro.setHasFixedSize(true);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    rv_productIntro.setLayoutManager(linearLayoutManager);
                    rv_productIntro.setAdapter(productDetailAdapter1);
                }
            }

            @Override
            public void onFailure(Call<resObj<ArrayList<Product>>> call, Throwable t) {

            }
        });
    }

    private void AddToCartNow(CartItem cartItem) {
        ApiService.apiService.addCartItem(cartItem).enqueue(new Callback<resObj<CartItem>>() {
            @Override
            public void onResponse(Call<resObj<CartItem>> call, Response<resObj<CartItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    finish();
                    Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Sản phẩm chưa được mua", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<resObj<CartItem>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi khi mua sản phẩm", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void AddToCart(CartItem cartItem) {
        ApiService.apiService.addCartItem(cartItem).enqueue(new Callback<resObj<CartItem>>() {
            @Override
            public void onResponse(Call<resObj<CartItem>> call, Response<resObj<CartItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    recreate();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    Toast.makeText(getApplicationContext(), "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Sản phẩm chưa được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<resObj<CartItem>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi khi thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Cart getCart() {
        Cart cart = new Cart();
        ApiService.apiService.getUserById(user).enqueue(new Callback<resObj<User>>() {
            @Override
            public void onResponse(Call<resObj<User>> call, Response<resObj<User>> response) {
                if (response.isSuccessful() && response.body().isSuccess() && response.body() != null) {
                    newUser = response.body().getData();
                }
            }

            @Override
            public void onFailure(Call<resObj<User>> call, Throwable t) {

            }
        });
        int quantityCurrent = Integer.parseInt(quantity.getText().toString());
        cart.setTotal(quantityCurrent);
        cart.setUser(newUser.get_id());
        return cart;
    }

    private CartItem getCartItem() {
        CartItem cartItem = new CartItem();
        int quantityCurrent = Integer.parseInt(quantity.getText().toString());
        Intent intent = getIntent();
        String productId = intent.getStringExtra("_id");
        cartItem.setQuantity(quantityCurrent);
        cartItem.setProduct(productId);
        return cartItem;
    }
    private void checkQuantity() {
        int quantityCurrent = Integer.parseInt(quantity.getText().toString());
        if (quantityCurrent <= 1) {
            btn_minus.setEnabled(false);
        }
        if (quantityCurrent >= 1) {
            btn_minus.setEnabled(true);
        }
    }

    private void displayProduct(String _id) {
        ApiService.apiService.getProductById(_id).enqueue(new Callback<resObj<Product>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<resObj<Product>> call, Response<resObj<Product>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Product product = response.body().getData();
                    if (product != null) {
                        Glide.with(getApplicationContext()).load(product.getImages())
                                .into(images);
                        title.setText(product.getTitle());
                        price.setText(NumberFormat.getCurrencyInstance(
                                new Locale("vi", "VN")).format(product.getPrice()));
                        tv_discount_price.setText(NumberFormat.getCurrencyInstance(
                                new Locale("vi", "VN")).format(product.getOld_price()));
                        double number = (( product.getOld_price() - product.getPrice()) / product.getOld_price()) * 100;
                        number = Math.round(number *100) / 100;
                        discount.setText("-" + number + "%");
                        id.setText(product.get_id());
                        author.setText(product.getAuthor());
                        description.setText(product.getDescription());
                        title2.setText(product.getTitle());
                        year_publish.setText(product.getPublished_date());

                        btn_seeMoreInfo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                    }
                }
            }
            @Override
            public void onFailure(Call<resObj<Product>> call, Throwable t) {

            }
        });

        btn_pdetail_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        btn_seeMoreIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supportFinishAfterTransition();
                supportFinishAfterTransition();
                ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(ProductDetailActivity.this, R.anim.animation_down, R.anim.no_animation);
                Intent intent = new Intent(ProductDetailActivity.this, CategoryActivity.class);
                intent.putExtra("Filter", "Mới nhất");
                startActivity(intent, options.toBundle());
            }
        });

    }

    private void SetFrontEnd() {
        btn_pdetail_back.setColorFilter(ContextCompat.getColor(this, R.color.white));
        btn_pdetail_home.setColorFilter(ContextCompat.getColor(this, R.color.white));
        btn_pdetail_cart.setColorFilter(ContextCompat.getColor(this, R.color.white));
        btn_heart.setColorFilter(ContextCompat.getColor(this, R.color.red));
        btn_add_to_cart.setColorFilter(ContextCompat.getColor(this, R.color.white));
        tv_discount_price.setPaintFlags(tv_discount_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void AnhXa() {
        btn_pdetail_back = (ImageView) findViewById(R.id.btn_pdetail_back);
        btn_pdetail_search = findViewById(R.id.btn_pdetail_search);
        btn_pdetail_home = (ImageView) findViewById(R.id.btn_pdetail_home);
        btn_pdetail_cart = (ImageView) findViewById(R.id.btn_pdetail_cart);
        btn_heart = (ImageView) findViewById(R.id.btn_heart);
        tv_discount_price = (TextView) findViewById(R.id.tv_discount_price);
        btn_add_to_cart = (ImageView) findViewById(R.id.btn_add_to_cart);
        rv_readerCare = findViewById(R.id.rv_readerCare);
        rv_productIntro = findViewById(R.id.rv_productIntro);
        images = findViewById(R.id.images);
        title = findViewById(R.id.title);
        price = findViewById(R.id.tv_price);
        discount = findViewById(R.id.tv_discount);
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        quantity = findViewById(R.id.quantity);
        id  = findViewById(R.id.id);
        author = findViewById(R.id.author);
        description = findViewById(R.id.description);
        title2 = findViewById(R.id.title2);
        year_publish = findViewById(R.id.year_publish);
        btn_buyNow = findViewById(R.id.btn_buy_now);
        quantityCart = findViewById(R.id.quantityCart);
        btn_seeMoreIntro = findViewById(R.id.btn_seeMoreIntro);
        btn_seeMoreCare = findViewById(R.id.btn_seeMoreCare);
        btn_seeMoreInfo = findViewById(R.id.btn_seeMoreInfo);
    }
}