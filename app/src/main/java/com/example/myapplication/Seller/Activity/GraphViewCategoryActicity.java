package com.example.myapplication.Seller.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.myapplication.R;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphViewCategoryActicity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view_category_acticity);
        ScatterChart chart = findViewById(R.id.chart);

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 20f)); // Điểm 1
        entries.add(new Entry(1f, 30)); // Điểm 2
        entries.add(new Entry(2f, 10)); // Điểm 3
        entries.add(new Entry(0f, 20f)); // Điểm 1
        entries.add(new Entry(1f, 30)); // Điểm 2
        entries.add(new Entry(2f, 23)); // Điểm 3
        entries.add(new Entry(0f, 44)); // Điểm 1
        entries.add(new Entry(1f, 1)); // Điểm 2
        entries.add(new Entry(2f, 25f)); // Điểm 3
        entries.add(new Entry(0f, 2)); // Điểm 1
        entries.add(new Entry(1f, 3)); // Điểm 2
        entries.add(new Entry(2f, 25f)); // Điểm 3
        entries.add(new Entry(0f, 3)); // Điểm 1
        entries.add(new Entry(1f, 43)); // Điểm 2
        entries.add(new Entry(2f, 25f)); // Điểm 3
// Thêm các Entry khác nếu cần

        ScatterDataSet dataSet = new ScatterDataSet(entries, "Scatter Data");
        dataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE); // Đặt hình dạng của điểm
        dataSet.setColor(Color.RED); // Đặt màu cho điểm

        ArrayList<IScatterDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        ScatterData data = new ScatterData(dataSets);
        chart.setData(data);

// Cấu hình các thuộc tính khác của ScatterChart theo ý muốn

        chart.invalidate();
    }
}