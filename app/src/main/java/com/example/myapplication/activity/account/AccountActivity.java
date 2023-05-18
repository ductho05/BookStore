package com.example.myapplication.activity.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LoginAdapter;

import com.google.android.material.tabs.TabLayout;

public class AccountActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        tabLayout = findViewById(R.id.account_tablayout);
        viewPager = findViewById(R.id.account_viewpaper);
        LoginAdapter pagerAdapter = new LoginAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(pagerAdapter);
        Log.e("tedsssss: ", "Home2");
        tabLayout.setupWithViewPager(viewPager);
    }
}