package com.example.myapplication.seller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.resObj;
import com.example.myapplication.seller.adapter.ManagerProductAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerProductActivity extends AppCompatActivity {

    private Button btn_addProduct;
    RecyclerView rv_productList;
    List<Product> productList;
    ManagerProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_product);
        AnhXa();
        Setproduct();
        btn_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerProductActivity.this, AddProductActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
            }
        });
    }

    private void Setproduct() {
        ApiService.apiService.getFilterProduct("","published_date", 1, 8, "desc", 8, "")
                .enqueue(new Callback<resObj<List<Product>>>() {
                    @Override
                    public void onResponse(Call<resObj<List<Product>>> call, Response<resObj<List<Product>>> response) {
                        if (response.isSuccessful() && response.body() != null) {

                            productList = response.body().getData();
                            Log.e("get data", String.valueOf(productList.size()));
                            productAdapter = new ManagerProductAdapter(productList, ManagerProductActivity.this);
                            rv_productList.setHasFixedSize(true);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(ManagerProductActivity.this, LinearLayoutManager.VERTICAL, false);
                            rv_productList.setLayoutManager(layoutManager);
                            rv_productList.setAdapter(productAdapter);
                        } else {
                            Log.e("get data", "null");
                        }
                    }

                    @Override
                    public void onFailure(Call<resObj<List<Product>>> call, Throwable t) {
                        Log.e("get data", t.getMessage());
                    }
                });
    }

    private void AnhXa() {
        btn_addProduct = findViewById(R.id.btn_add_product);
        rv_productList = findViewById(R.id.rv_product221);
    }
}