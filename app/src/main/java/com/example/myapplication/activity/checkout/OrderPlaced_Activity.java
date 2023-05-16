package com.example.myapplication.activity.checkout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.activity.cart.CartActivity;
import com.example.myapplication.activity.home.HomeActivity;

public class OrderPlaced_Activity extends AppCompatActivity {

    Button btn_ctn_buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_placed);
        AnhXa();
        btn_ctn_buy.setOnClickListener(view -> {
            Intent intent = new Intent(OrderPlaced_Activity.this, HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
        });
        noticeDeleteCart();
    }

    private void noticeDeleteCart() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.noctice_order_place, null);

        builder.setView(viewDialog);

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void AnhXa() {
        btn_ctn_buy = findViewById(R.id.btn_ctn_buy);
    }
}