package com.example.myapplication.activity.Cart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.model.CartModel;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cart);

        AnhXa();
        setViewLastBook();
    }
    public void AnhXa() {
        recyclerView = findViewById(R.id.recycleView);
    }

    public void setViewLastBook() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<CartModel> news = new ArrayList<>();
        news.add(new CartModel("Con meo", "10", "9", "cat", 2));
        news.add(new CartModel("Con meo", "10", "9", "cat", 5));
        news.add(new CartModel("Con meo", "10", "9", "cat", 4));
        news.add(new CartModel("Con meo", "10", "9", "cat", 3));
        news.add(new CartModel("Con meo", "10", "9", "cat", 2));

        adapter = new CartAdapter(news);
        recyclerView.setAdapter(adapter);
    }
}
