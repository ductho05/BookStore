package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.fragment.AllFragment;
import com.example.myapplication.model.Category;

import java.util.List;

public class ViewPaperAdapter extends FragmentStatePagerAdapter {

    private List<Category> listCate;
    public ViewPaperAdapter(@NonNull FragmentManager fm, int behavior, List<Category> listCate) {
        super(fm, behavior);
        this.listCate = listCate;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {

        for (int i = 0; i < getCategories().length; i++) {
            if (position == i)
                return new AllFragment(getCategories()[i]);
        }
        return new AllFragment(getCategories()[0]);
    }

    @Override
    public int getCount() {
        return getlistcate().length;
    }

    public String[] getCategories() {
        String[] categories = new String[listCate.size()+1];
        categories[0] = null;
        for(int i = 1; i < listCate.size(); i++){
            categories[i] = listCate.get(i).get_id();
        }
        return categories;
    }


    public String[] getlistcate () {
        String[] cates = new String[listCate.size()+1];
        cates[0] = "Tất cả";
        for(int i = 1; i < listCate.size(); i++){
            cates[i] = listCate.get(i).getName();
        }
        return cates;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        for (int i = 0; i < getlistcate().length; i++) {
            if (position == i)
                return getlistcate()[i];
        }
        return "Tất cả";
//        switch (position){
//            case 0:
//                return getCategories().get(0).getName();
//            case 1:
//                return "Kinh Tế";
//            case 2:
//                return "Thiếu Nhi";
//            case 3:
//                return "Văn Học";
//            case 4:
//                return "Khoa Học Kỹ Thuật";
//            case 5:
//                return "Kỹ Năng Sống";
//            default:
//                return "Tất cả";
//        }
    }
}
