package com.example.myapplication.activity.account;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.order.DetailOrderItemActivity;
import com.example.myapplication.activity.order.StatusOrderActivity;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.EvaluateModel;
import com.example.myapplication.model.OrderItem;
import com.example.myapplication.model.OrderItemModel;
import com.example.myapplication.model.OrderModel;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.resObj;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluateActivity extends AppCompatActivity {

    private ImageView backToOrder, productImage;
    private TextView productName, productPriceDiscount, productDiscount, productPrice, comment;
    private RatingBar rating;
    private Button btn_eval;

    Product product;
    String user;
    LoginManager loginManager;

    OrderItemModel orderItemModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        loginManager = new LoginManager(sharedPreferences);
        user = sharedPreferences.getString("id", "");
        AnhXa();
        Bundle bundle = getIntent().getExtras();
        orderItemModel = bundle.getParcelable("orderItem");
        btn_eval.setEnabled(false);
        btn_eval.setBackgroundColor(Color.rgb(254, 232, 176));
        displayProduct();
        validate(rating);
        evaluate();
        //Log.e("test ordervalid", orderItemModel.toString());
    }

    private void validate(RatingBar rating) {
        rating.setOnRatingBarChangeListener((ratingBar, v, b) -> {
            if (v > 0) {
                btn_eval.setEnabled(true);
                btn_eval.setBackgroundColor(Color.rgb(77,177,136));
            } else {
                btn_eval.setEnabled(false);
                btn_eval.setBackgroundColor(Color.rgb(254, 232, 176));
            }
        });
    }

    private void evaluate() {
        btn_eval.setOnClickListener(view -> {
            float rate = rating.getRating();
            String commentData = comment.getText().toString();
            Intent intent = getIntent();
            String productId = intent.getStringExtra("_id");
            EvaluateModel evaluate = new EvaluateModel();
            evaluate.setRate(rate);
            evaluate.setComment(commentData);
            evaluate.setProduct(productId);
            evaluate.setUser(user);
            ApiService.apiService.addEvaluate(evaluate).enqueue(new Callback<resObj<EvaluateModel>>() {
                @Override
                public void onResponse(Call<resObj<EvaluateModel>> call, Response<resObj<EvaluateModel>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        OrderItem orderItem = new OrderItem(orderItemModel.getQuantity(), orderItemModel.getPrice(), orderItemModel.getOrder().get_id(), orderItemModel.getProduct().get_id());
                        orderItem.set_id(orderItemModel.get_id());
                        orderItem.setStatus("DANHGIA");
                        String orderItemId = orderItem.get_id();
                        Log.e("Order Item test: ", orderItemId);
                        ApiService.apiService.updateOrderItem(orderItemId, orderItem).enqueue(new Callback<resObj<OrderItem>>() {
                            @Override
                            public void onResponse(Call<resObj<OrderItem>> call, Response<resObj<OrderItem>> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    noticeSuccessEvaluate();
                                }
                            }

                            @Override
                            public void onFailure(Call<resObj<OrderItem>> call, Throwable t) {
                                Log.e("update orderItem: ", t.getMessage());
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<resObj<EvaluateModel>> call, Throwable t) {

                }
            });
        });

    }

    private void noticeSuccessEvaluate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.notice_evaluate, null);

        builder.setView(viewDialog);

        AlertDialog dialog = builder.create();
        dialog.show();
        Button btn_OK = viewDialog.findViewById(R.id.buttonOK);
        btn_OK.setOnClickListener(view -> {
            Intent intent = new Intent(EvaluateActivity.this, StatusOrderActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
        });

    }
    private void displayProduct() {
        Intent intent = getIntent();
        String productId = intent.getStringExtra("_id");
        ApiService.apiService.getProductById(productId).enqueue(new Callback<resObj<Product>>() {
            @Override
            public void onResponse(Call<resObj<Product>> call, Response<resObj<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    product = response.body().getData();
                    if (product != null) {
                        Glide.with(EvaluateActivity.this).load(product.getImages()).into(productImage);
                        productName.setText(product.getTitle());
                        productPriceDiscount.setText(NumberFormat.getCurrencyInstance(
                                new Locale("vi", "VN")).format(product.getPrice()));
                        double number = (( product.getOld_price() - product.getPrice()) / product.getOld_price()) * 100;
                        number = Math.round(number *100) / 100;
                        productDiscount.setText("-" + number + "%");
                        productPrice.setText(NumberFormat.getCurrencyInstance(
                                new Locale("vi", "VN")).format(product.getOld_price()));
                    }
                }
            }

            @Override
            public void onFailure(Call<resObj<Product>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {

        backToOrder = findViewById(R.id.backtohome);
        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        productPriceDiscount = findViewById(R.id.productPriceDiscount);
        productDiscount = findViewById(R.id.productDiscount);
        productPrice = findViewById(R.id.productPrice);
        comment = findViewById(R.id.comment);
        rating = findViewById(R.id.rating);
        btn_eval = findViewById(R.id.btn_eval);
    }
}