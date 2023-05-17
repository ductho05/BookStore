package com.example.myapplication.Seller.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.Seller.Model.RateModel;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.resObj;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class GraphViewProductActicity extends AppCompatActivity {

    private BarChart chart;
    private ProgressBar progressBar;
    private List<RateModel> rates = new ArrayList<>();
    private int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view_product_acticity);
        chart = findViewById(R.id.chart);
        progressBar = findViewById(R.id.progressBar1);

        GetDataRate();
    }

    private void GetDataRate() {
        ApiService.apiService.countProductByRate().enqueue(new retrofit2.Callback<resObj<List<RateModel>>>() {
            @Override
            public void onResponse(retrofit2.Call<resObj<List<RateModel>>> call, retrofit2.Response<resObj<List<RateModel>>> response) {
                rates = response.body().getData();
                if (rates.size() == 0) {
                    progressBar.setVisibility(View.VISIBLE);
                    chart.setVisibility(View.GONE);
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    chart.setVisibility(View.VISIBLE);
                }
                List<BarEntry> entries = new ArrayList<>();
                List<String> labels = new ArrayList<>();

                for (int i = 0; i < rates.size(); i++) {
                    entries.add(new BarEntry(i, rates.get(i).getCount()));
                    labels.add("Rate: " + rates.get(i).get_id()); // Thêm tên cho từng cột X
                }

                BarDataSet dataSet = new BarDataSet(entries, "Sản phẩm");

                // Cấu hình màu sắc
                dataSet.setColor(Color.BLUE);

                // Tạo dữ liệu biểu đồ
                BarData barData = new BarData(dataSet);
                chart.setData(barData);

                // Cấu hình trục x
                chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels)); // Đặt định dạng và tên cho trục X

                // Cấu hình trục y
                chart.getAxisLeft().setAxisMinimum(0);
                chart.getAxisRight().setEnabled(false);

                // Hiển thị biểu đồ
                chart.invalidate();
            }

            @Override
            public void onFailure(retrofit2.Call<resObj<List<RateModel>>> call, Throwable t) {

            }
        });
    }
}