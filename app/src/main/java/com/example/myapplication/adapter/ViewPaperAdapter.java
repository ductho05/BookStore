package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.fragment.AllFragment;
import com.example.myapplication.fragment.KHKTFragment;
import com.example.myapplication.fragment.KNSFragment;
import com.example.myapplication.fragment.KTFragment;
import com.example.myapplication.fragment.TNFragment;
import com.example.myapplication.fragment.VHFragment;

public class ViewPaperAdapter extends FragmentStatePagerAdapter {
    public ViewPaperAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AllFragment();
                case 1:
                return new KTFragment();
                case 2:
                return new TNFragment();
                case 3:
                return new VHFragment();
                case 4:
                return new KHKTFragment();
                case 5:
                return new KNSFragment();
            default:
                return new AllFragment();
        }
    }

    @Override
    public int getCount() {
        return 6;
    }


    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Tất cả";
            case 1:
                return "Kinh Tế";
            case 2:
                return "Thiếu Nhi";
            case 3:
                return "Văn Học";
            case 4:
                return "Khoa Học Kỹ Thuật";
            case 5:
                return "Kỹ Năng Sống";
            default:
                return "Tất cả";
        }
    }
}
