package com.example.myapplication.activity.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.adapter.StatusOrderAdapter;
import com.example.myapplication.fragment.AllFragment;
import com.example.myapplication.fragment.StatusOrderFragment;
import com.example.myapplication.model.StatusOrder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class StatusOrderActivity extends AppCompatActivity {

    private ImageView backtohome;
    public TabLayout tabLayout;
    public ViewPager viewPager;

    public boolean msort = false;

    StatusOrderAdapter statusOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_order);
        AnhXa();
        //senData(msort);
        ClicktoThing();
        statusOrderAdapter = new StatusOrderAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(statusOrderAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void senData(boolean sort) {
        msort = sort;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.viewPager, new StatusOrderFragment(StatusOrder.CHOXACNHAN));
        ft.commit();
    }

    public boolean getSort() {
        return msort;
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