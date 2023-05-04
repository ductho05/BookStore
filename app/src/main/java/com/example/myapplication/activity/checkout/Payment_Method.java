package com.example.myapplication.activity.checkout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.myapplication.R;

public class Payment_Method extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_payment_method);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        findViewById(R.id.btn_back).setOnClickListener(view -> {
            supportFinishAfterTransition();
            Intent intent = new Intent(Payment_Method.this, CheckOut_Address_Activity.class);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(Payment_Method.this);
            startActivity(intent, optionsCompat.toBundle());
        });

        findViewById(R.id.btn_ctn_buy).setOnClickListener(view -> {
            Intent intent = new Intent(Payment_Method.this, OrderPlaced_Activity.class);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(Payment_Method.this);
            startActivity(intent, optionsCompat.toBundle());
        });

        findViewById(R.id.payment_method_list).setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_HOVER_ENTER:
                        // Handle hover enter here
                        break;
                    case MotionEvent.ACTION_HOVER_MOVE:
                        // Handle hover move here
                        break;
                    case MotionEvent.ACTION_HOVER_EXIT:
                        // Handle hover exit here
                        break;
                }
                return true;
            }
        });
    }
}