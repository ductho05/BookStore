package com.example.myapplication.activity.home;

import static com.example.myapplication.api.ApiService.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductDetailAdapter;
import com.example.myapplication.adapter.ViewPaperAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.fragment.AllFragment;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.resObj;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {


    private static CategoryActivity instance;
    private ImageView backtohome;
    TextView tvSearch;

    private String mfilter = "";

    public TabLayout tabLayout;
    public ViewPager viewPager;

    public List<Product> productList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        instance = this;
        setContentView(R.layout.activity_category);
        AnhXa();
        ClickOneAThing();
//      ViewPaperAdapter viewPaperAdapter = new ViewPaperAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ViewPaperAdapter viewPaperAdapter = new ViewPaperAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);


        viewPager.setAdapter(viewPaperAdapter);
        tabLayout.setupWithViewPager(viewPager);



        String filter = getIntent().getStringExtra("Filter");
        Log.d("TA121G", "onCreate: " + filter);
        
        senData(filter);
//        bundle.putString("key", filter);
//
//        Log.d("TA121G", "onCreate: " + bundle);
//        AllFragment fragment = new AllFragment();
//        fragment.setArguments(bundle);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.viewPager, fragment);
//        transaction.commit();

        //GetDataCategory();
    }

    private void senData(String filter) {
        mfilter = filter;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.viewPager, new AllFragment());
        ft.commit();
    }

    public String getFilter() {
        return mfilter;
    }


    @SuppressLint("ClickableViewAccessibility")
    private void ClickOneAThing() {
        backtohome.setOnClickListener(v -> {
            finish();
        });
        tvSearch.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, SearchActivity.class);
            startActivity(intent);
        });
    }



    public static CategoryActivity getInstance() {
        return instance;
    }

    private Call<resObj> GetFilter() {
        String filter = getIntent().getStringExtra("Filter");
        Call<resObj> option = null;
        if (Objects.equals(filter, "NEW"))
            option = apiService.getNewProduct("1");
        else if (Objects.equals(filter, "SALE"))
            option = apiService.getBestSellerProduct("1");
        else
            option = apiService.getLowestProduct("1");
        return option;
    }


    private void GetDataCategory() {
        GetFilter().enqueue(new Callback<resObj>() {
            @Override
            public void onResponse(@NonNull Call<resObj> call, @NonNull Response<resObj> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    productList = response.body().getData();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    //AllFragment fragment = AllFragment.newInstance(productList);
//                    ft.add(R.id.viewPager, fragment);
//                    ft.setReorderingAllowed(true);
//                    ft.commit();
                }
            }

            @Override
            public void onFailure(Call<resObj> call, Throwable t) {
                Log.d("Lỗi", t.getMessage());
            }
        });
    }

    private void AnhXa() {

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        backtohome = findViewById(R.id.backtohome);
        tvSearch = findViewById(R.id.tvSearch);
    }
}