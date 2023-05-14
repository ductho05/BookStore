package com.example.myapplication.activity.home;

import static com.example.myapplication.api.ApiService.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ViewPaperAdapter;
import com.example.myapplication.fragment.AllFragment;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.resObj;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
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
    public List<Category> categoryList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        instance = this;
        setContentView(R.layout.activity_category);
        AnhXa();
        ClickOneAThing();
        GetDataCategory();
        Log.d("TA121saG", "onCreate: " + categoryList);
//      ViewPaperAdapter viewPaperAdapter = new ViewPaperAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);




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
        ft.replace(R.id.viewPager, new AllFragment("Tất cả"));
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

    private Call<resObj<ArrayList<Product>>> GetFilter() {
        String filter = getIntent().getStringExtra("Filter");
        Call<resObj<ArrayList<Product>>> option = null;
        if (Objects.equals(filter, "NEW"))
            option = apiService.getNewProduct("1");
        else if (Objects.equals(filter, "SALE"))
            option = apiService.getBestSellerProduct("1");
        else
            option = apiService.getLowestProduct("1");
        return option;
    }



    private void GetDataCategory () {
        apiService.getAllCategory().enqueue(new Callback<resObj<List<Category>>>() {
            @Override
            public void onResponse(@NonNull Call<resObj<List<Category>>> call, @NonNull Response<resObj<List<Category>>> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    List<Category> cates;
                    cates = response.body().getData();
                    categoryList = cates;
                    ViewPaperAdapter viewPaperAdapter = new ViewPaperAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, categoryList);
                    viewPager.setAdapter(viewPaperAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
            }

            @Override
            public void onFailure(Call<resObj<List<Category>>> call, Throwable t) {
                Log.d("Lỗi", t.getMessage());
            }
        });
        Log.d("TA121G13123", "onCreate: " + categoryList);
    }

    private void AnhXa() {

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        backtohome = findViewById(R.id.backtohome);
        tvSearch = findViewById(R.id.tvSearch);
    }
}