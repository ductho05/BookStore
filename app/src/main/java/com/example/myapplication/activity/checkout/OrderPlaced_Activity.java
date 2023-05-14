package com.example.myapplication.activity.checkout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.activity.home.HomeActivity;

public class OrderPlaced_Activity extends AppCompatActivity {

    ImageView btn_back;

    Button btn_ctn_buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_placed);
        AnhXa();
        btn_back.setOnClickListener(view -> {
            supportFinishAfterTransition();
            Intent intent = new Intent(OrderPlaced_Activity.this, HomeActivity.class);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(OrderPlaced_Activity.this);
            startActivity(intent, optionsCompat.toBundle());
        });

        btn_ctn_buy.setOnClickListener(view -> {
            supportFinishAfterTransition();
            Intent intent = new Intent(OrderPlaced_Activity.this, HomeActivity.class);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(OrderPlaced_Activity.this);
            startActivity(intent, optionsCompat.toBundle());
        });
    }

    private void AnhXa() {
        btn_back = findViewById(R.id.btn_back);
        btn_ctn_buy = findViewById(R.id.btn_ctn_buy);
    }
}