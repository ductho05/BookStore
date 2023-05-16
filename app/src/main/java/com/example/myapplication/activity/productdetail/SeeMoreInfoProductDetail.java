package com.example.myapplication.activity.productdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.resObj;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeMoreInfoProductDetail extends AppCompatActivity {

    TextView id, author, year_publish, price, sold, status, title, description;
    RatingBar rate;
    ImageView btn_back;
    Product product = new Product();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more_info_product_detail);
        AnhXa();
        Intent intent = getIntent();
        String productId = intent.getStringExtra("id");
        displayInfo(productId);
        btn_back.setOnClickListener(view -> {
            String id = product.get_id();
            Intent intent1 = new Intent(SeeMoreInfoProductDetail.this, ProductDetailActivity.class);
            intent1.putExtra("_id", id);
            startActivity(intent1);
            overridePendingTransition(R.anim.slide_down, R.anim.no_animation);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("_id", product.get_id());
        startActivity(intent);
        overridePendingTransition(R.anim.slide_down, R.anim.no_animation);
    }

    private void displayInfo(String productId) {
        ApiService.apiService.getProductById(productId).enqueue(new Callback<resObj<Product>>() {
            @Override
            public void onResponse(Call<resObj<Product>> call, Response<resObj<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    product = response.body().getData();
//                    id, author, year_publish, price, sold, status, title, description;
                    if (product != null) {
                        id.setText(product.get_id());
                        author.setText(product.getAuthor());
                        year_publish.setText(product.getPublished_date());
                        price.setText(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(product.getPrice()));
                        sold.setText(String.valueOf(product.getSold()));
                        status.setText(product.getStatus());
                        rate.setRating((float) product.getRate());
                        title.setText(product.getTitle());
                        description.setText(product.getDescription());
                    }
                }
            }

            @Override
            public void onFailure(Call<resObj<Product>> call, Throwable t) {
                Log.e("Lấy thông tin sản phẩm: ", "Lỗi");
            }
        });
    }

    private void AnhXa() {
        id = findViewById(R.id.id);
        author = findViewById(R.id.author);
        year_publish = findViewById(R.id.year_publish);
        price = findViewById(R.id.price);
        sold = findViewById(R.id.sold);
        status = findViewById(R.id.status);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        rate = findViewById(R.id.rate);
        btn_back = findViewById(R.id.btn_pdetail_back);
    }

}