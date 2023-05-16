package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.fragment.StatusOrderFragment;
import com.example.myapplication.model.StatusOrder;

public class StatusOrderAdapter extends FragmentStateAdapter {

    public StatusOrderAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
                case 1:
                    return new StatusOrderFragment(StatusOrder.CHOLAYHANG);
                case 2:
                    return new StatusOrderFragment(StatusOrder.DANGGIAO);
                case 3:
                    return new StatusOrderFragment(StatusOrder.DAGIAO);
                case 4:
                    return new StatusOrderFragment(StatusOrder.HUY);
                case 5:
                    return new StatusOrderFragment(StatusOrder.DANHGIA);
                default:
                    return new StatusOrderFragment(StatusOrder.CHOXACNHAN);
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
