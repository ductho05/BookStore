package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView btn_pdetail_back;
    ImageView btn_pdetail_search;
    ImageView btn_pdetail_home;
    ImageView btn_pdetail_cart;
    ImageView btn_heart;
    TextView tv_discount_price;

    ImageView btn_add_to_cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btn_pdetail_back = (ImageView) findViewById(R.id.btn_pdetail_back);
        btn_pdetail_search = (ImageView) findViewById(R.id.btn_pdetail_search);
        btn_pdetail_home = (ImageView) findViewById(R.id.btn_pdetail_home);
        btn_pdetail_cart = (ImageView) findViewById(R.id.btn_pdetail_cart);
        btn_heart = (ImageView) findViewById(R.id.btn_heart);
        tv_discount_price = (TextView) findViewById(R.id.tv_discount_price);
        btn_add_to_cart = (ImageView) findViewById(R.id.btn_add_to_cart);
        btn_pdetail_back.setColorFilter(ContextCompat.getColor(this, R.color.white));
        btn_pdetail_search.setColorFilter(ContextCompat.getColor(this, R.color.white));
        btn_pdetail_home.setColorFilter(ContextCompat.getColor(this, R.color.white));
        btn_pdetail_cart.setColorFilter(ContextCompat.getColor(this, R.color.white));
        btn_heart.setColorFilter(ContextCompat.getColor(this, R.color.red));
        btn_add_to_cart.setColorFilter(ContextCompat.getColor(this, R.color.white));
        tv_discount_price.setPaintFlags(tv_discount_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}