package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.fragment.AllFragment;
import com.example.myapplication.fragment.KhoahockythuatFragment;
import com.example.myapplication.fragment.KinhteFragment;
import com.example.myapplication.fragment.KynagsongFragment;
import com.example.myapplication.fragment.ThieunhiFragment;
import com.example.myapplication.fragment.VanhocFragment;

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
                return new KinhteFragment();
            case 2:
                return new VanhocFragment();
            case 3:
                return new KynagsongFragment();
            case 4:
                return new ThieunhiFragment();
            case 5:
                return new KhoahockythuatFragment();
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
                return "Kinh tế";
            case 2:
                return "Văn học";
            case 3:
                return "Kỹ năng sống";
            case 4:
                return "Thiếu nhi";
            case 5:
                return "Khoa học kỹ thuật";
            default:
                return "Tất cả";
        }
    }
}
