package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CheckOut_Address_Activity extends AppCompatActivity {

    ConstraintLayout work_address;
    ConstraintLayout home_address;
    Drawable bgDrawableActi;
    Drawable bgDrawableNoActi;
    ImageView home_image;
    ImageView work_image;
    ImageView tick_work;
    ImageView tick_home;
    TextView label_home;
    TextView label_work;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.checkout_address_activity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        work_address = findViewById(R.id.address_work_layout);
        home_address = findViewById(R.id.home_address_layout);
        home_image = findViewById(R.id.home_img);
        work_image = findViewById(R.id.work_image);
        tick_work = findViewById(R.id.tick_work);
        tick_home = findViewById(R.id.tick_home);
        label_home = findViewById(R.id.label_home);
        label_work = findViewById(R.id.label_work);
        bgDrawableActi = getResources().getDrawable(R.drawable.rounded_corner_acti);
        bgDrawableNoActi = getResources().getDrawable(R.drawable.rounded_corner);
        findViewById(R.id.address_work_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_address.setBackgroundDrawable(bgDrawableNoActi);
                work_address.setBackgroundDrawable(bgDrawableActi);
                tick_home.setVisibility(View.GONE);
                tick_work.setVisibility(View.VISIBLE);
                label_home.setTextColor(getResources().getColor(R.color.text_color));
                label_work.setTextColor(getResources().getColor(R.color.primary_color));
                home_image.setImageResource(R.drawable.home2);
                work_image.setImageResource(R.drawable.workplace2);
            }
        });

        findViewById(R.id.home_address_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_address.setBackgroundDrawable(bgDrawableActi);
                work_address.setBackgroundDrawable(bgDrawableNoActi);
                tick_home.setVisibility(View.VISIBLE);
                tick_work.setVisibility(View.GONE);
                label_home.setTextColor(getResources().getColor(R.color.primary_color));
                label_work.setTextColor(getResources().getColor(R.color.text_color));
                home_image.setImageResource(R.drawable.home);
                work_image.setImageResource(R.drawable.workplace);
            }
        });

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(CheckOut_Address_Activity.this, Payment_Method.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_add_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(CheckOut_Address_Activity.this, Add_Address.class);
                startActivity(intent);
            }
        });
    }
}