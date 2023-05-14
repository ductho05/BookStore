package com.example.myapplication.activity.cart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.helper.ManagementCart;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    CheckBox allCheckBox;
    TextView totalPrice;
    ArrayList<CartModel> news;
    ManagementCart managementCart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cart);

        AnhXa();
        setViewLastBook();

        allCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allCheckBox.isChecked()) {
                    updatePrice(news);
                }else {
                    totalPrice.setText("0.0");
                }
            }
        });
    }

    public void updatePrice(ArrayList<CartModel> arrayList) {
        totalPrice.setText(priceControl(arrayList));
    }
    public void updateSelected(ArrayList<CartModel> arrayList) {
        allCheckBox.setText("Chọn tất cả " + "(" + arrayList.size()+" sản phẩm"+")");
    }
    public void AnhXa() {
        recyclerView = findViewById(R.id.recycleView);
        allCheckBox = (CheckBox) findViewById(R.id.checkBox);
        totalPrice = findViewById(R.id.totalPrice);
    }

    public void setViewLastBook() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        news = new ArrayList<>();
        news.add(new CartModel("Con meo", "10", "9", "cat", 2));
        news.add(new CartModel("Con meo", "10", "9", "cat", 5));
        news.add(new CartModel("Con meo", "10", "9", "cat", 4));
        news.add(new CartModel("Con meo", "10", "9", "cat", 3));
        news.add(new CartModel("Con meo", "10", "9", "cat", 2));

        updateSelected(news);
        adapter = new CartAdapter(news, managementCart);
        recyclerView.setAdapter(adapter);
    }
    private String priceControl(ArrayList<CartModel> arrayList) {
//        int totalCount = 0;
        double totalPrice = 0;
        for (int i = 0; i < arrayList.size(); i++) {
//            totalCount = totalCount + arrayList.get(i).getAmount();
            double itemPrice = Double.parseDouble(arrayList.get(i).getSalePriceItem()) * arrayList.get(i).getAmount();
            totalPrice += itemPrice;
        }
        return String.valueOf(totalPrice);
    }



}
