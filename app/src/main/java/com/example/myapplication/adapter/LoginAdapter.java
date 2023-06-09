package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.fragment.LoginFragment;
import com.example.myapplication.fragment.RegisterFragment;

public class LoginAdapter extends FragmentStatePagerAdapter {

    public LoginAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position) {
           case 0:
               return new LoginFragment();
           case 1:
               return new RegisterFragment();
           default:
               return new LoginFragment();
       }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "ĐĂNG NHẬP";
                break;
            case 1:
                title = "ĐĂNG KÝ";

        }
        return title;
    }
}
