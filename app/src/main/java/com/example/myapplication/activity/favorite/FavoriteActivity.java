package com.example.myapplication.activity.favorite;

import static com.example.myapplication.api.ApiService.apiService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.productdetail.ProductDetailActivity;
import com.example.myapplication.adapter.FavoriteAdapter;
import com.example.myapplication.adapter.ProductDetailAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.Favorite;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.resObj;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView rcvData;
    List<Favorite> favorites = new ArrayList<>();

    FavoriteAdapter favoriteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Log.d("TAG13232", "onCreate: ");
        AnhXa();
        Log.d("TAG13w232", "onCreate: ");
        apiService.getFavorite("64477d8318a87d6e84a366d0", null).enqueue(new Callback<resObj<List<Favorite>>>() {
            @Override
            public void onResponse(Call<resObj<List<Favorite>>> call, Response<resObj<List<Favorite>>> response) {
                if (response.isSuccessful()) {
                    favorites = response.body().getData();
                    List<Product> productList = new ArrayList<>();
                    for (int i = 0; i < favorites.size(); i++) {
                        apiService.getProductById(favorites.get(i).getProductid()).enqueue(new Callback<resObj<Product>>() {
                            @Override
                            public void onResponse(Call<resObj<Product>> call, Response<resObj<Product>> response) {
                                if (response.isSuccessful()) {
                                    Product product = response.body().getData();
                                    productList.add(product);
                                    favoriteAdapter = new FavoriteAdapter(productList, FavoriteActivity.this);
                                    rcvData.setHasFixedSize(true);
                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FavoriteActivity.this, LinearLayoutManager.VERTICAL, false);
                                    rcvData.setLayoutManager(linearLayoutManager);
                                    rcvData.setAdapter(favoriteAdapter);
                                } else {
                                    Log.d("loioday", "onResponse: " + response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<resObj<Product>> call, Throwable t) {
                                Log.d("TAG132á3232212", "onResponse: " + t.getMessage());
                            }
                        });
                    }
                }
                else {
                    Log.d("loioday", "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<resObj<List<Favorite>>> call, Throwable t) {
                Log.d("TAG132á3232212", "onResponse: " + t.getMessage());
            }
        });
    }


    private void AnhXa() {
        rcvData = findViewById(R.id.recyclerViewFavorite);

    }
}