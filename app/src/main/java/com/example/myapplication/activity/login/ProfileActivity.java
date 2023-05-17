package com.example.myapplication.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.activity.cart.CartActivity;
import com.example.myapplication.activity.home.HomeActivity;
import com.example.myapplication.activity.order.StatusOrderActivity;


public class ProfileActivity extends AppCompatActivity {


    private LinearLayout homepage, imgCart, imgthongbao, imgFavorite, tcchoxacnhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AnhXa();
        ClickAOneThing();
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
//        imgthongbao  = findViewById(R.id.imgthongbao);
//        imgFavorite = findViewById(R.id.imgFavorite);
    }
}