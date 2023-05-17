package com.example.myapplication.Seller.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Seller.Model.RateModel;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.OrderModel;
import com.example.myapplication.model.resObj;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class GraphViewOrderActicity extends AppCompatActivity {

    private PieChart chart;
    private List<OrderModel> rates = new ArrayList<>();

    private int dahuy = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view_order_acticity);
        chart = findViewById(R.id.chart);

        getData();
        //Toast.makeText(GraphViewOrderActicity.this, "Đã giao: " + dahuy, Toast.LENGTH_SHORT).show();
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(20, "Đang xác nhận"));
        entries.add(new PieEntry(10, "Đang lấy hàng"));
        entries.add(new PieEntry(20, "Đang giao hàng"));
        entries.add(new PieEntry(40, "Đã giao hàng"));
        entries.add(new PieEntry(10, "Đã hủy"));

        PieDataSet dataSet = new PieDataSet(entries, "Categories");
        dataSet.setValueTextSize(25f); // Đặt kích thước chữ là 12

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.CYAN);
        colors.add(Color.DKGRAY);
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        chart.setData(data);

        chart.getDescription().setEnabled(false);
        chart.setDrawEntryLabels(false);
        chart.setUsePercentValues(true);
        chart.setHoleRadius(0f);
        chart.setTransparentCircleRadius(0f);
        chart.animateY(1000);

        chart.invalidate();
    }

    private void getData() {
        ApiService.apiService.countOrderByStatus("DAGIAO").enqueue(new retrofit2.Callback<resObj<List<OrderModel>>>() {
            @Override
            public void onResponse(retrofit2.Call<resObj<List<OrderModel>>> call, retrofit2.Response<resObj<List<OrderModel>>> response) {
                if (response.isSuccessful()) {
                    List<OrderModel> list = response.body().getData();
                    dahuy = list.size();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<resObj<List<OrderModel>>> call, Throwable t) {

            }
        }   );
    }
}