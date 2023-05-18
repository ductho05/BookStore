package com.example.myapplication.activity.productdetail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.account.AccountActivity;
import com.example.myapplication.activity.account.LoginManager;
import com.example.myapplication.activity.cart.CartActivity;
import com.example.myapplication.activity.home.CategoryActivity;
import com.example.myapplication.activity.home.HomeActivity;
import com.example.myapplication.activity.home.SearchActivity;
import com.example.myapplication.adapter.EvaluateAdapter;
import com.example.myapplication.adapter.ProductDetailAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.Cart;
import com.example.myapplication.model.CartItem;
import com.example.myapplication.model.CartItemModel;
import com.example.myapplication.model.CartModel;
import com.example.myapplication.model.Evaluate;
import com.example.myapplication.model.Favorite;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.User;
import com.example.myapplication.model.resObj;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView btn_pdetail_back;
    TextView btn_pdetail_search;
    ImageView btn_pdetail_home;
    ScrollView scrollView2;
    Handler handler = new Handler();
    ImageView btn_pdetail_cart;
    ImageView btn_heart, btn_heart_on;
    TextView tv_discount_price;
    ImageView btn_add_to_cart;

    ProgressBar progressBar;
    ImageView images;
    TextView title;
    TextView price;
    TextView discount;
    ImageView btn_plus;
    ImageView btn_minus;
    TextView quantity, id, author, year_publish, description,title2, btn_seeMoreIntro, btn_seeMoreCare, btn_seeMoreInfo,
            quantity_eval, btn_seeAllEval, rating_num;
    TextView btn_buyNow, quantityCart;
    RatingBar rating_star;
    private RecyclerView rv_productIntro;
    private RecyclerView rv_readerCare;
    private RecyclerView rv_evaluate;
    private ProductDetailAdapter productDetailAdapter1;
    private ProductDetailAdapter productDetailAdapter2;
    private EvaluateAdapter evaluateAdapter;
    private List<Product> productList1;
    private List<Product> productList2;
    private List<Evaluate> evaluateList;
    Product product = new Product();
    LoginManager loginManager;
    String user;
    User newUser = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        loginManager = new LoginManager(sharedPreferences);
        user = sharedPreferences.getString("id", "");
        AnhXa();

        SetFrontEnd();
        Intent intent = getIntent();
        String _id = intent.getStringExtra("_id");
        ClickToThing(_id);
        ckeckFavorite(_id, user);
        displayProduct(_id);
        setProductIntro();
        setProductCare(_id);
        setQuantityCart();
        setEvaluateList();
        btn_pdetail_search.setOnClickListener(view -> {
            supportFinishAfterTransition();
            Intent intent12 = new Intent(ProductDetailActivity.this, SearchActivity.class);
            startActivity(intent12);
            overridePendingTransition(R.anim.slide_down, R.anim.no_animation);
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
        btn_buyNow.setOnClickListener(view -> {
            if (!loginManager.isLoggedIn()) {
                noticeNotLogedIn();
            } else {
                ApiService.apiService.getCartByUser(user).enqueue(new Callback<resObj<CartModel>>() {
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
                                                CartItemModel cartItem = response.body().getData();
                                                int quantity = cartItem.getQuantity();
                                                String cartItemId = cartItem.get_id();
                                                quantity += 1;
                                                cartItem.setQuantity(quantity);
                                                ApiService.apiService.updateCartItem(cartItemId, cartItem).enqueue(new Callback<resObj<String>>() {
                                                    @Override
                                                    public void onResponse(Call<resObj<String>> call, Response<resObj<String>> response) {
                                                        if (response.isSuccessful() && response.body() != null) {
                                                            Intent intent1 = getIntent();
                                                            String productId = intent1.getStringExtra("_id");
                                                            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                                                            intent.putExtra("_id", productId);
                                                            startActivity(intent);
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<resObj<String>> call, Throwable t) {

                                                    }
                                                });
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
            }
        });
        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!loginManager.isLoggedIn()) {
                    noticeNotLogedIn();
                } else {
                    ApiService.apiService.getCartByUser(user).enqueue(new Callback<resObj<CartModel>>() {
                        @Override
                        public void onResponse(Call<resObj<CartModel>> call, Response<resObj<CartModel>> response) {
                            if (response.isSuccessful() && response.body().getData() != null) {
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
                                                    CartItemModel cartItem = response.body().getData();
                                                    int quantity = cartItem.getQuantity();
                                                    String cartItemId = cartItem.get_id();
                                                    quantity += 1;
                                                    cartItem.setQuantity(quantity);
                                                    ApiService.apiService.updateCartItem(cartItemId, cartItem).enqueue(new Callback<resObj<String>>() {
                                                        @Override
                                                        public void onResponse(Call<resObj<String>> call, Response<resObj<String>> response) {
                                                            if (response.isSuccessful() && response.body() != null) {
                                                                noticeAddToCart();
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<resObj<String>> call, Throwable t) {

                                                        }
                                                    });
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
                    });
                }
            }
        });
    }

    Runnable hideProgressBarRunnable = new Runnable() {
        @Override
        public void run() {
            progressBar.setVisibility(View.GONE);
            scrollView2.setAlpha(1);
        }
    };

    private void ckeckFavorite(String id, String user) {
        ApiService.apiService.checkFavorite(user, id).enqueue(new Callback<resObj<Favorite>>() {
            @Override
            public void onResponse(Call<resObj<Favorite>> call, Response<resObj<Favorite>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Favorite favoriteModel = response.body().getData();
                    if (favoriteModel != null) {
                        btn_heart.setVisibility(View.GONE);
                        btn_heart_on.setVisibility(View.VISIBLE);
                    } else {
                        btn_heart.setVisibility(View.VISIBLE);
                        btn_heart_on.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<resObj<Favorite>> call, Throwable t) {

            }
        });
    }


    private void ClickToThing(String id) {
        btn_pdetail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFavorite(getFavorite(id));
                btn_heart.setVisibility(View.GONE);
                btn_heart_on.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                scrollView2.setAlpha(0.5f);
                handler.postDelayed(hideProgressBarRunnable, 500);
            }
        });
        btn_heart_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFavorite(getFavorite(id));
                btn_heart.setVisibility(View.VISIBLE);
                btn_heart_on.setVisibility(View.GONE);
                scrollView2.setAlpha(0.5f);
                progressBar.setVisibility(View.VISIBLE);
                handler.postDelayed(hideProgressBarRunnable, 500);
            }
        });
    }

    private void noticeAddToCart() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.cart_notification_dialog, null);

        builder.setView(viewDialog);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button button_continue_shopping = viewDialog.findViewById(R.id.button_continue_shopping);
        button_continue_shopping.setOnClickListener(view -> {
            Intent intent1 = getIntent();
            String productId = intent1.getStringExtra("_id");
            Intent intent = new Intent(ProductDetailActivity.this, ProductDetailActivity.class);
            intent.putExtra("_id", productId);
            startActivity(intent);
            overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
        });

        Button button_view_cart = viewDialog.findViewById(R.id.button_view_cart);
        button_view_cart.setOnClickListener(view -> {
            Intent intent1 = getIntent();
            String productId = intent1.getStringExtra("_id");
            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
            intent.putExtra("_id", productId);
            startActivity(intent);
        });
    }

    private void noticeNotLogedIn() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.notice_not_logedin, null);

        builder.setView(viewDialog);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button button_continue_shopping = viewDialog.findViewById(R.id.btn_toLogin);
        button_continue_shopping.setOnClickListener(view -> {
            Intent intent = new Intent(ProductDetailActivity.this, AccountActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
        });

    }

    private void setQuantityCart() {
        ApiService.apiService.getAllCartItemByUser(user).enqueue(new Callback<resObj<List<CartItemModel>>>() {
            @Override
            public void onResponse(Call<resObj<List<CartItemModel>>> call, Response<resObj<List<CartItemModel>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CartItemModel> list = response.body().getData();
                    if (list != null) {
                        String quantityCartItem = String.valueOf(list.size());
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

    private Favorite getFavorite(String id){
        Favorite favorite = new Favorite();
        favorite.setProductid(id);
        favorite.setUserid(user);
        return favorite;
    }

    private void addFavorite(Favorite favorite) {
        ApiService.apiService.addFavorite(favorite).enqueue(new Callback<resObj<Favorite>>() {
            @Override
            public void onResponse(Call<resObj<Favorite>> call, Response<resObj<Favorite>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(ProductDetailActivity.this, "Đã thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<resObj<Favorite>> call, Throwable t) {
                Log.e("Add Favorite: ", t.getMessage());
            }
        });
    }

    private void deleteFavorite(Favorite favorite) {
        ApiService.apiService.deleteFavorite(favorite).enqueue(new Callback<resObj<Favorite>>() {
            @Override
            public void onResponse(Call<resObj<Favorite>> call, Response<resObj<Favorite>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(ProductDetailActivity.this, "Đã xóa khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<resObj<Favorite>> call, Throwable t) {
                Log.e("Add Favorite: ", t.getMessage());
            }
        });
    }

    private Favorite GetFavorite() {
        Favorite favorite = new Favorite();
        favorite.setProductid(product.get_id());
        favorite.setUserid(user);
        return favorite;
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

    private void setEvaluateList() {
        Intent intent1 = getIntent();
        String productId = intent1.getStringExtra("_id");
        ApiService.apiService.getEvaluateByProduct(productId).enqueue(new Callback<resObj<List<Evaluate>>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<resObj<List<Evaluate>>> call, Response<resObj<List<Evaluate>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    evaluateList = response.body().getData();
                    evaluateAdapter = new EvaluateAdapter(evaluateList, ProductDetailActivity.this);
                    rv_evaluate.setHasFixedSize(true);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.VERTICAL, false);
                    rv_evaluate.setLayoutManager(linearLayoutManager);
                    rv_evaluate.setAdapter(evaluateAdapter);

                    String num_eval = String.valueOf(evaluateList.size());
                    quantity_eval.setText("Đánh giá("+ num_eval+ ")");
                }
                Log.e("Load đánh giá: ", "OK");
            }

            @Override
            public void onFailure(Call<resObj<List<Evaluate>>> call, Throwable t) {
                Log.e("Load đánh giá thất bại: ", t.getMessage());
            }
        });
    }

    private void AddToCartNow(CartItem cartItem) {
        ApiService.apiService.addCartItem(cartItem).enqueue(new Callback<resObj<CartItem>>() {
            @Override
            public void onResponse(Call<resObj<CartItem>> call, Response<resObj<CartItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Intent intent1 = getIntent();
                    String productId = intent1.getStringExtra("_id");
                    Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                    intent.putExtra("_id", productId);
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
                    noticeAddToCart();
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
                        rating_num.setText(product.getRate() + "/5");
                        rating_star.setRating((float) product.getRate());
                        btn_seeMoreInfo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String id = product.get_id();
                                Intent intent = new Intent(ProductDetailActivity.this, SeeMoreInfoProductDetail.class);
                                intent.putExtra("id", id);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_up, R.anim.no_animation);
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
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        btn_seeMoreIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        progressBar = findViewById(R.id.progressBarDetailProduct);
        btn_pdetail_back = (ImageView) findViewById(R.id.btn_pdetail_back);
        btn_pdetail_search = findViewById(R.id.btn_pdetail_search);
        btn_pdetail_home = (ImageView) findViewById(R.id.btn_pdetail_home);
        btn_pdetail_cart = (ImageView) findViewById(R.id.btn_pdetail_cart);
        btn_heart = (ImageView) findViewById(R.id.btn_heart);
        btn_heart_on = (ImageView) findViewById(R.id.btn_heart_on);
        tv_discount_price = (TextView) findViewById(R.id.tv_discount_price);
        btn_add_to_cart = (ImageView) findViewById(R.id.btn_add_to_cart);
        rv_readerCare = findViewById(R.id.rv_readerCare);
        rv_productIntro = findViewById(R.id.rv_productIntro);
        rv_evaluate = findViewById(R.id.rv_evaluate);
        images = findViewById(R.id.images);
        title = findViewById(R.id.title);
        price = findViewById(R.id.tv_price);
        discount = findViewById(R.id.tv_discount);
        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);
        quantity = findViewById(R.id.quantity);
        scrollView2 = findViewById(R.id.scrollView2);
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
        quantity_eval = findViewById(R.id.quantity_eval);
        btn_seeAllEval = findViewById(R.id.btn_seeAllEval);
        rating_num = findViewById(R.id.rating_num);
        rating_star = findViewById(R.id.rating_star);
    }
}