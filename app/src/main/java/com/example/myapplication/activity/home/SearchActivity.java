package com.example.myapplication.activity.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductDetailAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.fragment.AllFragment;
import com.example.myapplication.helper.DebounceTimer;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.resObj;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private ProductDetailAdapter productDetailAdapter;
    private RecyclerView recyclerView, recyclerViewFavorite;
    private RecyclerView.Adapter adapter;

    ImageView backtohome;
    private List<Product> products;

    private List<Product> popularSearch;
    private LinearLayout first_linearLayout, second_linearLayout;

    private TextView tvNoResult, promotion, tvfavorite;
    //private Button btnSearchMore;

    private SearchView searchView;




    private DebounceTimer debounceTimer = new DebounceTimer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search);

        AnhXa();
        setFirstLinearlayout();
        ClickOneAThing();
        searchView.clearFocus();
        recyclerView = findViewById(R.id.recyclerViewSearch);




        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                  //  btnSearchMore.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);

                } else {
                   // btnSearchMore.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(SearchActivity.this, CategoryActivity.class);
                intent.putExtra("title", query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Sử dụng debouncing để tránh gửi quá nhiều request
                debounceTimer.debounce(new Runnable() {
                    @Override
                    public void run() {
                        ApiService.apiService.getProductByTitle(newText, 9).enqueue(new Callback<resObj<List<Product>>>() {
                            @Override
                            public void onResponse(Call<resObj<List<Product>>> call, Response<resObj<List<Product>>> response) {
                                if (response.isSuccessful()) {
                                    products = response.body().getData();
                                    if (products.size() == 0) {
                                        tvNoResult.setVisibility(View.VISIBLE);
                                        recyclerView.setVisibility(View.GONE);
                                    } else {
                                        tvNoResult.setVisibility(View.GONE);
                                        recyclerView.setVisibility(View.VISIBLE);
                                        adapter = new ProductDetailAdapter(products, SearchActivity.this);
                                        recyclerView.setHasFixedSize(true);
                                        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
                                        recyclerView.addItemDecoration(divider);
                                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
                                        recyclerView.setLayoutManager(linearLayoutManager);
                                        recyclerView.setAdapter(adapter);
                                    }
                                }
                            }

                                @Override
                                public void onFailure(Call<resObj<List<Product>>> call, Throwable t) {
                                    Log.d("logg", t.getMessage());
                                }
                            });
                    }

                });

                    return true;
            }
        });
    }

    private void ClickOneAThing() {
        backtohome.setOnClickListener(v -> {
            finish();
        });
    }

    private void setFirstLinearlayout() {
        promotion.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fire, 0, R.drawable.fire, 0);
        tvfavorite.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_favorite_24, 0, 0, 0);


        ApiService.apiService.getFilterProduct("", "rate", 1, 50, "desc", 9, "").enqueue(new Callback<resObj<List<Product>>>() {
            @Override
            public void onResponse(Call<resObj<List<Product>>> call, Response<resObj<List<Product>>> response) {
                if (response.isSuccessful()) {
                    popularSearch = response.body().getData();
                    adapter = new ProductDetailAdapter(popularSearch, SearchActivity.this);
                    recyclerViewFavorite.setHasFixedSize(true);
                    DividerItemDecoration divider = new DividerItemDecoration(recyclerViewFavorite.getContext(), LinearLayoutManager.VERTICAL);
                    recyclerViewFavorite.addItemDecoration(divider);
                    DividerItemDecoration divider2 = new DividerItemDecoration(recyclerViewFavorite.getContext(), LinearLayoutManager.HORIZONTAL);
                    recyclerViewFavorite.addItemDecoration(divider2);
                    LinearLayoutManager layoutManager = new GridLayoutManager(SearchActivity.this, 3);
                    recyclerViewFavorite.setLayoutManager(layoutManager);
                    recyclerViewFavorite.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<resObj<List<Product>>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }

    private void AnhXa() {
        backtohome = findViewById(R.id.backtohome);
        tvfavorite = findViewById(R.id.tvfavorite);
        promotion = findViewById(R.id.promotion);
        tvNoResult = findViewById(R.id.tvNoProduct);
      //  btnSearchMore = findViewById(R.id.btnSearchMore);
        searchView = findViewById(R.id.search_view);
        first_linearLayout = findViewById(R.id.first_linearLayout);
        second_linearLayout = findViewById(R.id.second_linearLayout);
        recyclerViewFavorite = findViewById(R.id.recyclerViewPopularSearch);
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchView.setIconified(false);
        searchView.requestFocus();
    }


}