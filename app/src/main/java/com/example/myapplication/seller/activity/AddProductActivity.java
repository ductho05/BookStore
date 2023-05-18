package com.example.myapplication.seller.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.ProductModel;
import com.example.myapplication.model.resObj;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductActivity extends AppCompatActivity {

    private EditText Pr_title, Pr_price_old, Pr_price, Pr_author, Pr_published_date, Pr_description;
    private Spinner categoty;
    private Button btn_addProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        AnhXa();
        addProduct();
    }

    private void addProduct() {
        btn_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = Pr_title.getText().toString();
                double price_old = Float.parseFloat(Pr_price_old.getText().toString());
                String author = Pr_author.getText().toString();
                double price = Float.parseFloat(Pr_price.getText().toString());
                String year = Pr_published_date.getText().toString();
                String description = Pr_description.getText().toString();
                String category = "361";
                ProductModel product = new ProductModel();
                product.setTitle(title);
                product.setOld_price(price_old);
                product.setAuthor(author);
                product.setPrice(price);
                product.setPublished_date(year);
                product.setDesciption(description);
                product.setCategoryId(category);

                ApiService.apiService.InsertProduct(product).enqueue(new Callback<resObj<ProductModel>>() {
                    @Override
                    public void onResponse(Call<resObj<ProductModel>> call, Response<resObj<ProductModel>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Log.e("thêm sp: ", "Ok");
                            noticeSuccessInsert();

                        } else {
                            Log.e("thêm sp: ", "faile");
                        }
                    }

                    @Override
                    public void onFailure(Call<resObj<ProductModel>> call, Throwable t) {
                        Log.e("thêm sp: ", t.getMessage());
                    }
                });
            }
        });
    }

    private void noticeSuccessInsert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.notice_login_success, null);

        builder.setView(viewDialog);

        AlertDialog dialog = builder.create();
        dialog.show();
        TextView notice_success = viewDialog.findViewById(R.id.notice_success);
        notice_success.setText("Thêm mới sản phẩm thành công");
    }
    private void AnhXa() {
        Pr_title = findViewById(R.id.Pr_title);
        Pr_price_old = findViewById(R.id.Pr_price_old);
        Pr_price = findViewById(R.id.Pr_price);
        Pr_author = findViewById(R.id.Pr_author);
        Pr_published_date = findViewById(R.id.Pr_published_date);
        Pr_description = findViewById(R.id.Pr_description);
        categoty = findViewById(R.id.categoty);
        btn_addProduct = findViewById(R.id.btn_addProduct);
    }
}