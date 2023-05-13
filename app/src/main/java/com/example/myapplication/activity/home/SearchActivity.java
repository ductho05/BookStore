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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private ProductDetailAdapter productDetailAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Product> products;

    private TextView tvNoResult;
    private Button btnSearchMore;

    private SearchView searchView;




    private DebounceTimer debounceTimer = new DebounceTimer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search);
        tvNoResult = findViewById(R.id.tvNoProduct);
        btnSearchMore = findViewById(R.id.btnSearchMore);
        searchView = findViewById(R.id.search_view);
        searchView.clearFocus();
        recyclerView = findViewById(R.id.recyclerViewSearch);




        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    btnSearchMore.setVisibility(View.VISIBLE);
                } else {
                    btnSearchMore.setVisibility(View.GONE);
                }
            }
        });



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Sử dụng debouncing để tránh gửi quá nhiều request
                debounceTimer.debounce(new Runnable() {
                    @Override
                    public void run() {
                        ApiService.apiService.getProductByTitle(newText, 9).enqueue(new Callback<resObj>() {
                            @Override
                            public void onResponse(Call<resObj> call, Response<resObj> response) {
                                if (response.isSuccessful()) {

                                    products = response.body().getData();
                                    if (products.size() == 0) {
                                        tvNoResult.setVisibility(View.VISIBLE);

                                    } else {
                                        tvNoResult.setVisibility(View.GONE);

                                    }
                                    adapter = new ProductDetailAdapter(products, SearchActivity.this);
                                    recyclerView.setHasFixedSize(true);
                                    DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
                                    recyclerView.addItemDecoration(divider);
                                    //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
                                    RecyclerView.LayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                                    recyclerView.setLayoutManager(linearLayoutManager);
                                    recyclerView.setAdapter(adapter);
                                    //productDetailAdapter.notifyDataSetChanged();
                                }

                            }

                            @Override
                            public void onFailure(Call<resObj> call, Throwable t) {
                                Log.d("logg", t.getMessage());
                            }
                        });
                    }
                });
                    return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchView.setIconified(false);
        searchView.requestFocus();
    }


}