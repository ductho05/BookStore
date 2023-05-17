package com.example.myapplication.activity.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class PublisherActivity extends AppCompatActivity {
    public static final String EXTRA_PUBLISHER_DETAIL = "publisher_detail";
    public static final String EXTRA_PUBLISHER_NAME = "publisher_name";
    public static final String EXTRA_PUBLISHER_IMAGE = "publisher_image";

    public static PublisherActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        instance = this;
        setContentView(R.layout.activity_publisher);

        String detailPublisher = getIntent().getStringExtra(EXTRA_PUBLISHER_DETAIL);
        String namePublisher = getIntent().getStringExtra(EXTRA_PUBLISHER_NAME);
        int imageResourceId = getIntent().getIntExtra(EXTRA_PUBLISHER_IMAGE, 0);

        TextView tvNamePublisher = findViewById(R.id.tvNamePublisher);
        TextView tvDesPublisher = findViewById(R.id.tvDesPublisher);
        ImageView imPublisher = findViewById(R.id.imPublisher);

        tvNamePublisher.setText(namePublisher);
        tvDesPublisher.setText(detailPublisher);
        imPublisher.setImageResource(imageResourceId);
    }
}