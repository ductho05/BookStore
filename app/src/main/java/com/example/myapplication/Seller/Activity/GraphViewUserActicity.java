package com.example.myapplication.seller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.R;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class GraphViewUserActicity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view_user_acticity);
        BubbleChart chart = findViewById(R.id.chart);

        List<BubbleEntry> entries = new ArrayList<>();
        entries.add(new BubbleEntry(0f, 30f, 10f)); // Bubble 1
        entries.add(new BubbleEntry(1f, 40f, 12f)); // Bubble 2
        entries.add(new BubbleEntry(2f, 20f, 15f)); // Bubble 3
// Thêm các BubbleEntry khác nếu cần

        BubbleDataSet dataSet = new BubbleDataSet(entries, "Bubbles");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS, 255); // Đặt màu cho các bubble

        ArrayList<IBubbleDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        BubbleData data = new BubbleData(dataSets);
        chart.setData(data);

// Cấu hình các thuộc tính khác của BubbleChart theo ý muốn

        chart.invalidate();
    }
}