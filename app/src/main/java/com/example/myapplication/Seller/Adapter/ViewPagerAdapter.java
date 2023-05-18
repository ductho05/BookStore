package com.example.myapplication.seller.adapter;

import android.os.Message;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.seller.Fragment.CartSellerFragment;
import com.example.myapplication.seller.Fragment.HomeSellerFragment;
import com.example.myapplication.seller.Fragment.ProductSellerFragment;
import com.example.myapplication.seller.Fragment.UserSellerFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new HomeSellerFragment();
            case 1:
                return new ProductSellerFragment();
            case 2:
                return new CartSellerFragment();
            case 3:
                return new UserSellerFragment();
           default:
                return new HomeSellerFragment();
        }
    }

    //trả về số lượng của task
    @Override
    public int getCount() {
        return 4;
    }
}
