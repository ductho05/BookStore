package com.example.uihome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private  RecyclerView listViewLastBook, listViewForYou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        AnhXa();
        setViewLastBook();
        setViewForYou();
    }

    public void AnhXa() {
        listViewLastBook = findViewById(R.id.view);
        listViewForYou = findViewById(R.id.viewforyou);
    }

    public void setViewLastBook() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listViewLastBook.setLayoutManager(linearLayoutManager);

        ArrayList<Book_in_home_Model> news = new ArrayList<>();
        news.add(new Book_in_home_Model("Đức Anh", "pic1"));
        news.add(new Book_in_home_Model("Đức Anh", "pic2"));
        news.add(new Book_in_home_Model("Đức Anh", "pic3"));
        news.add(new Book_in_home_Model("Đức Anh", "pic4"));
        news.add(new Book_in_home_Model("Đức Anh", "pic1"));
        news.add(new Book_in_home_Model("Đức Anh", "pic2"));
        news.add(new Book_in_home_Model("Đức Anh", "pic3"));
        news.add(new Book_in_home_Model("Đức Anh", "pic4"));

        adapter = new Book_in_home_Adapter(news);
        listViewLastBook.setAdapter(adapter);
    }

    public void setViewForYou() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        listViewForYou.setLayoutManager(layoutManager);
        ArrayList<Book_in_home_Model> news = new ArrayList<>();
        news.add(new Book_in_home_Model("Đức Anh", "pic1"));
        news.add(new Book_in_home_Model("Đức Anh", "pic2"));
        news.add(new Book_in_home_Model("Đức Anh", "pic3"));
        news.add(new Book_in_home_Model("Đức Anh", "pic4"));
        news.add(new Book_in_home_Model("Đức Anh", "pic1"));
        news.add(new Book_in_home_Model("Đức Anh", "pic2"));
        news.add(new Book_in_home_Model("Đức Anh", "pic3"));
        news.add(new Book_in_home_Model("Đức Anh", "pic4"));
        news.add(new Book_in_home_Model("Đức Anh", "pic1"));
        news.add(new Book_in_home_Model("Đức Anh", "pic2"));
        news.add(new Book_in_home_Model("Đức Anh", "pic3"));
        news.add(new Book_in_home_Model("Đức Anh", "pic4"));
        adapter = new Book_in_home_Adapter(news);
        listViewForYou.setAdapter(adapter);
    }
}