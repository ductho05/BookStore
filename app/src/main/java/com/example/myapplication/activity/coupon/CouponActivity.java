package com.example.myapplication.activity.coupon;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CouponAdapter;
import com.example.myapplication.model.CouponModel;

import java.util.ArrayList;

public class CouponActivity extends AppCompatActivity {
    private RecyclerView rvCouponToday,rvCouponAll,rvCouponFreeShip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_coupon);

        AnhXa();
        setCouponToday();
        setCouponAll();
        setCouponFreeShip();
    }
    public void AnhXa() {
        rvCouponToday = findViewById(R.id.rv_CouponToday);
        rvCouponAll = findViewById(R.id.rv_CouponAll);
        rvCouponFreeShip = findViewById(R.id.rv_CouponFreeShip);
    }
    public void setCouponToday() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCouponToday.setLayoutManager(linearLayoutManager);

        ArrayList<CouponModel> news = new ArrayList<>();
        news.add(new CouponModel("HOÀN 10K EPOINT", "ĐH từ 120K", "15/5/2023", "23:00", "DP35FRU7"));
        news.add(new CouponModel("HOÀN 10K EPOINT", "ĐH từ 120K", "15/5/2023", "23:00", "DP35FRU7"));
        news.add(new CouponModel("HOÀN 10K EPOINT", "ĐH từ 120K", "15/5/2023", "23:00", "DP35FRU7"));
        news.add(new CouponModel("HOÀN 10K EPOINT", "ĐH từ 120K", "15/5/2023", "23:00", "DP35FRU7"));
        news.add(new CouponModel("HOÀN 10K EPOINT", "ĐH từ 120K", "15/5/2023", "23:00", "DP35FRU7"));

        RecyclerView.Adapter adapter = new CouponAdapter(news);
        rvCouponToday.setAdapter(adapter);
    }
    public void setCouponAll() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCouponAll.setLayoutManager(linearLayoutManager);

        ArrayList<CouponModel> news = new ArrayList<>();
        news.add(new CouponModel("MÃ GIẢM 10K", "Đơn hàng từ 150K (TOÀN SÀN)", "15/5/2023", "00:00", "DP35FRU7"));
        news.add(new CouponModel("MÃ GIẢM 30K", "Đơn hàng từ 150K (TOÀN SÀN)", "15/5/2023", "00:00", "DP35FRU7"));
        news.add(new CouponModel("MÃ GIẢM 30K SALE THỨ 3", "Đơn hàng từ 150K (TOÀN SÀN)", "15/5/2023", "00:00", "DP35FRU7"));
        news.add(new CouponModel("MÃ GIẢM 50K", "Đơn hàng từ 150K (TOÀN SÀN)", "15/5/2023", "00:00", "DP35FRU7"));
        news.add(new CouponModel("MÃ GIẢM 50K SALE THỨ 3", "Đơn hàng từ 150K (TOÀN SÀN)", "15/5/2023", "00:00", "DP35FRU7"));

        RecyclerView.Adapter adapter = new CouponAdapter(news);
        rvCouponAll.setAdapter(adapter);
    }
    public void setCouponFreeShip() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCouponFreeShip.setLayoutManager(linearLayoutManager);

        ArrayList<CouponModel> news = new ArrayList<>();
        news.add(new CouponModel("FREESHIP 5K", "ĐH từ 100K", "15/5/2023", "23:00", "DP35FRU7"));
        news.add(new CouponModel("FREESHIP 15K", "ĐH từ 120K", "15/5/2023", "23:00", "DP35FRU7"));
        news.add(new CouponModel("FREESHIP 20K", "ĐH từ 150K", "15/5/2023", "23:00", "DP35FRU7"));
        news.add(new CouponModel("FREESHIP 50K", "ĐH từ 300K", "15/5/2023", "23:00", "DP35FRU7"));

        RecyclerView.Adapter adapter = new CouponAdapter(news);
        rvCouponFreeShip.setAdapter(adapter);
    }

}
