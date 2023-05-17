package com.example.myapplication.activity.notification;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NotificationAdapter;
import com.example.myapplication.model.NotificationModel;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_notification);

        AnhXa();
        setNotification();
    }
    public void AnhXa() {
        recyclerView = findViewById(R.id.rv_Notification);
    }
    public void setNotification() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<NotificationModel> news = new ArrayList<>();
        news.add(new NotificationModel("Deal khủng sách hay - mang ngay về cho bé", "14/5/2023", "Sách thiếu nhi - sách kiến thức bách khoa - cinema book - Deal sốc đồng giảm lên đến 50%. Dùng ngay coupon tiết kiệm giảm 20k deal thương hiệu + 10k freeship"));
        news.add(new NotificationModel("Deal khủng sách hay - mang ngay về cho bé", "14/5/2023", "Sách thiếu nhi - sách kiến thức bách khoa - cinema book - Deal sốc đồng giảm lên đến 50%. Dùng ngay coupon tiết kiệm giảm 20k deal thương hiệu + 10k freeship"));
        news.add(new NotificationModel("Deal khủng sách hay - mang ngay về cho bé", "14/5/2023", "Sách thiếu nhi - sách kiến thức bách khoa - cinema book - Deal sốc đồng giảm lên đến 50%. Dùng ngay coupon tiết kiệm giảm 20k deal thương hiệu + 10k freeship"));
        news.add(new NotificationModel("Deal khủng sách hay - mang ngay về cho bé", "14/5/2023", "Sách thiếu nhi - sách kiến thức bách khoa - cinema book - Deal sốc đồng giảm lên đến 50%. Dùng ngay coupon tiết kiệm giảm 20k deal thương hiệu + 10k freeship"));
        news.add(new NotificationModel("Deal khủng sách hay - mang ngay về cho bé", "14/5/2023", "Sách thiếu nhi - sách kiến thức bách khoa - cinema book - Deal sốc đồng giảm lên đến 50%. Dùng ngay coupon tiết kiệm giảm 20k deal thương hiệu + 10k freeship"));
        news.add(new NotificationModel("Deal khủng sách hay - mang ngay về cho bé", "14/5/2023", "Sách thiếu nhi - sách kiến thức bách khoa - cinema book - Deal sốc đồng giảm lên đến 50%. Dùng ngay coupon tiết kiệm giảm 20k deal thương hiệu + 10k freeship"));


        adapter = new NotificationAdapter(news);
        recyclerView.setAdapter(adapter);
    }

}
