package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.fragment.StatusOrderFragment;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.StatusOrder;

import java.util.List;

public class StatusOrderAdapter extends FragmentStatePagerAdapter {

    private boolean sort;
    public StatusOrderAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new StatusOrderFragment(StatusOrder.CHOXACNHAN);
                case 1:
                    return new StatusOrderFragment(StatusOrder.CHOLAYHANG);
                case 2:
                    return new StatusOrderFragment(StatusOrder.DANGGIAO);
                case 3:
                    return new StatusOrderFragment(StatusOrder.DAGIAO);
                case 4:
                    return new StatusOrderFragment(StatusOrder.HUY);
                default:
                    return new StatusOrderFragment(StatusOrder.CHOXACNHAN);

        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {

                switch (position){
                    case 0:
                        return "Chờ xác nhận";
                    case 1:
                        return "Chờ lấy hàng";
                    case 2:
                        return "Đang giao";
                    case 3:
                        return "Đã giao";
                    case 4:
                        return "Đã đánh giá";
                    default:
                        return "Chờ xác nhận";

            }
    }
}
