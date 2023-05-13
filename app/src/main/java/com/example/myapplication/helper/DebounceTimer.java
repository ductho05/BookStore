package com.example.myapplication.helper;

import java.util.Timer;
import java.util.TimerTask;

public class DebounceTimer {
    private final long DELAY_MS = 500; // Thời gian debounce là 500ms
    private Timer timer;

    public void debounce(Runnable runnable) {
        // Hủy bỏ timer cũ nếu có
        if (timer != null) {
            timer.cancel();
        }

        // Tạo timer mới và chạy runnable sau khi timer kết thúc
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        }, DELAY_MS);
    }
}

