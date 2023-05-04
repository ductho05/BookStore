package com.example.myapplication.activity.checkout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.myapplication.R;

public class OrderPlaced_Activity extends AppCompatActivity {

    TextView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_placed);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        findViewById(R.id.btn_back).setOnClickListener(view -> {
            supportFinishAfterTransition();
            Intent intent = new Intent(OrderPlaced_Activity.this, Payment_Method.class);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(OrderPlaced_Activity.this);
            startActivity(intent, optionsCompat.toBundle());
        });
    }
}