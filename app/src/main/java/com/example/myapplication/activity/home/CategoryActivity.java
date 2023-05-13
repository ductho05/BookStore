package com.example.myapplication.activity.home;

import static com.example.myapplication.api.ApiService.*;

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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    private static final String MAX = "100";
    private ImageView backtohome;
    TextView tvSearch;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    List<Product> productList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_category);
        AnhXa();
        ClickOneAThing();
        ViewPaperAdapter viewPaperAdapter = new ViewPaperAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPaperAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //String filter = getIntent().getStringExtra("Filter");
        //Log.d("filteq1qwr", filter);
//        Bundle bundle = new Bundle();
//        AllFragment myFragment = new AllFragment();
//        bundle.putString("key", "value");
//        //bundle.pu("filter", filter);
////                    bundle.putInt("listKey", 1);
//
//        myFragment.setArguments(bundle);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.viewPager, myFragment);
//        transaction.commit();
        GetDataCategory();

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

    private Call<resObj> GetFilter() {
        String filter = getIntent().getStringExtra("Filter");
        Call<resObj> option = null;
        if (Objects.equals(filter, "NEW"))
            option = apiService.getNewProduct(MAX);
        else if (Objects.equals(filter, "SALE"))
            option = apiService.getBestSellerProduct(MAX);
        else
            option = apiService.getLowestProduct(MAX);
        return option;
    }


    private void GetDataCategory() {
        GetFilter().enqueue(new Callback<resObj>() {
            @Override
            public void onResponse(Call<resObj> call, Response<resObj> response) {
                if (response.isSuccessful()){
                    productList = response.body().getData();
                    ArrayList<Product> products = new ArrayList<>(productList);
                    AllFragment fragment = new AllFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("list", products);
                    fragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.viewPager, fragment).commit();
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