package com.example.myapplication.activity.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.activity.account.LoginManager;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.adapter.StatusOrderAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class StatusOrderActivity extends AppCompatActivity {

    private ImageView backtohome;
    public TabLayout tabLayout;
    public ViewPager2 viewPager;

    StatusOrderAdapter statusOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_order);

        AnhXa();
        ClicktoThing();
        statusOrderAdapter = new StatusOrderAdapter(StatusOrderActivity.this);
        viewPager.setAdapter(statusOrderAdapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Chờ xác nhận");
                    break;
                case 1:
                    tab.setText("Chờ lấy hàng");
                    break;
                case 2:
                    tab.setText("Đang giao");
                    break;
                case 3:
                    tab.setText("Đã giao");
                    break;
                case 4:
                    tab.setText("Bị hủy");
                    break;
                case 5:
                    tab.setText("Chưa đánh giá");
                    break;
            }
        }).attach();
    }

    private void ClicktoThing() {
        backtohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        backtohome = findViewById(R.id.backtohome);
    }
}