package com.example.myapplication.fragment;

import static com.example.myapplication.api.ApiService.apiService;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.home.CategoryActivity;
import com.example.myapplication.activity.home.HomeActivity;
import com.example.myapplication.adapter.ProductDetailAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.resObj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllFragment extends Fragment {



    private View mView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Product> myList;

    public AllFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState)     {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
        //Log.d("DAY NE BAN OI", getArguments().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            // Fragment được tạo mới
            Log.d("KIEMTRA!@#","ĐƯỢC TẠO LẠI");
        } else {
            // Fragment được tạo lại
            Log.d("KIEMTRA!@#","KHÔNG ĐƯỢC TẠO LẠI");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_all, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            myList =  bundle.getParcelableArrayList("list");
            Log.d("TAG1112121", "onCreataaaaaaeView: " + myList.get(0).getTitle());
            // Ép kiểu về ArrayList<Product>
            adapter = new ProductDetailAdapter(myList, getContext());
            recyclerView = mView.findViewById(R.id.rycall);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else {
            Log.e("A", "Arguments should not be null");
        }


//        if (bundle != null) {
//            myList = bundle.getParcelableArrayList("list");
//            Log.d("TAG1112121", "onCreateView: " + myList.size());
//        } else {
//            throw new IllegalStateException("Arguments should not be null");
//        }
//        Boolean a = bundle.containsKey("list");
//        Log.d("bundle đã hợp lệ chưa?", a.toString());
        //Bundle bundle = getArguments();
//        if (bundle != null) {
//            ArrayList<Product> productList = bundle.getParcelableArrayList("list");
//            Log.d("TAG1112121", "onCreateView: " + productList.size());
//        } else {
//            throw new IllegalStateException("Arguments should not be null");
//        }
        return mView;
    }


}