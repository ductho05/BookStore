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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ViewPaperAdapter;
import com.example.myapplication.fragment.AllFragment;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.resObj;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {


    private static CategoryActivity instance;
    private ImageView backtohome;
    TextView tvSearch;

    private String mfilter = "", mtitle = "";

    LinearLayout search_linearLayout, filter_linearLayout;
    public TabLayout tabLayout;
    public ViewPager viewPager, viewPagerSearch;
    //public FrameLayout viewPagerSearch;

    //public List<Product> productList = new ArrayList<>();
    public List<Category> categoryList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        instance = this;
        setContentView(R.layout.activity_category);

        AnhXa();
        String title = getIntent().getStringExtra("title");
        String filter = getIntent().getStringExtra("Filter");
        Log.d("TA121saG", "onCreate: " + filter);
        if (title != null) {
            Log.d("TA121saG", "onCreate dsafa: " + filter);
            filter_linearLayout.setVisibility(View.GONE);
            search_linearLayout.setVisibility(View.VISIBLE);
            senData("Tất cả", title);
            List<Category> categoryList = new ArrayList<>();
            ViewPaperAdapter viewPaperAdapter = new ViewPaperAdapter(getSupportFragmentManager(),
                    FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, categoryList, title);
            viewPagerSearch.setAdapter(viewPaperAdapter);
        }
        else if (filter != null){

            Log.d("TA121ssáaG", "onCreate filet: " + filter);
            filter_linearLayout.setVisibility(View.VISIBLE);
            search_linearLayout.setVisibility(View.GONE);
            GetDataCategory();
            senData(filter, "");
        }
        ClickOneAThing();
    }

    private void senData(String filter, String title) {
        mfilter = filter;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.viewPager, new AllFragment("Tất cả", title));
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

    private void GetDataCategory () {
        apiService.getAllCategory().enqueue(new Callback<resObj<List<Category>>>() {
            @Override
            public void onResponse(@NonNull Call<resObj<List<Category>>> call, @NonNull Response<resObj<List<Category>>> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    List<Category> cates;
                    cates = response.body().getData();
                    categoryList = cates;
                    ViewPaperAdapter viewPaperAdapter = new ViewPaperAdapter(getSupportFragmentManager(),
                            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, categoryList, "");
                    viewPager.setAdapter(viewPaperAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
            }

            @Override
            public void onFailure(Call<resObj<List<Category>>> call, Throwable t) {
                Log.d("Lỗi", t.getMessage());
            }
        });
    }

    private void AnhXa() {

        viewPagerSearch = findViewById(R.id.viewPagerSearch);
        search_linearLayout = findViewById(R.id.linearLayoutSearch);
        filter_linearLayout = findViewById(R.id.linearLayoutFilter);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        backtohome = findViewById(R.id.backtohome);
        tvSearch = findViewById(R.id.tvSearch);
    }
}