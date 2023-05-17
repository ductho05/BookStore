package com.example.myapplication.activity.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.account.AccountActivity;
import com.example.myapplication.activity.account.LoginManager;
import com.example.myapplication.activity.cart.CartActivity;
import com.example.myapplication.activity.home.HomeActivity;
import com.example.myapplication.activity.order.DetailOrderItemActivity;
import com.example.myapplication.activity.order.StatusOrderActivity;
import com.example.myapplication.activity.productdetail.ProductDetailActivity;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.User;
import com.example.myapplication.model.resObj;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileActivity extends AppCompatActivity {


    private LinearLayout homepage, imgCart, imgthongbao, imgFavorite, tcchoxacnhan;
    private ImageView civImage;
    private TextView tvUserName, tvProfileName, tvProfileEmail;
    private Button btn_logout;
    LoginManager loginManager;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        loginManager = new LoginManager(sharedPreferences);
        user = sharedPreferences.getString("id", "");
        AnhXa();
        ClickAOneThing();
        displayInfoUser();

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noticeLogout();
            }
        });
    }

    private void noticeLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.notice_logout, null);

        builder.setView(viewDialog);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button button_continue_shopping = viewDialog.findViewById(R.id.btn_OKLogout);
        button_continue_shopping.setOnClickListener(view -> {
            loginManager.logout();
            Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
        });
    }

    private void displayInfoUser() {
        ApiService.apiService.getUserById(user).enqueue(new Callback<resObj<User>>() {
            @Override
            public void onResponse(Call<resObj<User>> call, Response<resObj<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User userData = response.body().getData();
                    Glide.with(ProfileActivity.this).load(userData.getImages()).into(civImage);
                    tvUserName.setText(userData.getUsername());
                    tvProfileName.setText(userData.getFullName());
                    tvProfileEmail.setText(userData.getEmail());
                } else {
                    Log.e("get user loi: ", "no data");
                }
            }

            @Override
            public void onFailure(Call<resObj<User>> call, Throwable t) {
                Log.e("get user loi: ", t.getMessage());
            }
        });
    }

    private void ClickAOneThing() {
        tcchoxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, StatusOrderActivity.class);
                startActivity(intent);
            }
        });
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
//        imgthongbao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
//                startActivity(intent);
//            }
//        });
//        imgFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ProfileActivity.this, CartActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void AnhXa() {

        tcchoxacnhan = findViewById(R.id.tcchoxacnhan);
        homepage = findViewById(R.id.homepage);
        imgCart = findViewById(R.id.imgCart);
        civImage = findViewById(R.id.civImage);
        tvUserName = findViewById(R.id.tvUserName);
        tvProfileName = findViewById(R.id.tvProfileName);
        tvProfileEmail = findViewById(R.id.tvProfileEmail);
        btn_logout = findViewById(R.id.btn_logout);
//        imgthongbao  = findViewById(R.id.imgthongbao);
//        imgFavorite = findViewById(R.id.imgFavorite);
    }
}