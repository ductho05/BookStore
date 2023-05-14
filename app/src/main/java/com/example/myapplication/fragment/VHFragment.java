package com.example.myapplication.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.home.CategoryActivity;
import com.example.myapplication.adapter.ProductDetailAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.resObj;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VHFragment extends Fragment {


    public static final String MAX = "10";
    public View mView;
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter = null;


    public List<Product> myList;


    public VHFragment() {

    }



    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_vanhoc, container, false);
        AnhXa();
        GetData();
        return mView;

    }

    private void GetData() {
        ApiService.apiService.getProductByCategory("645b0835a916f1a8008b68ce", 0).enqueue(new Callback<resObj<List<Product>>>() {
            @Override
            public void onResponse(Call<resObj<List<Product>>> call, Response<resObj<List<Product>>> response) {
                if (response.isSuccessful()) {
                    resObj<List<Product>> resObj = response.body();
                    myList = resObj.getData();
                    Log.d("myList12", myList.toString());
                    adapter= new ProductDetailAdapter(myList, CategoryActivity.getInstance());
                    recyclerView.setHasFixedSize(true);
                    DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
                    DividerItemDecoration divider2 = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL);
                    recyclerView.addItemDecoration(divider);
                    recyclerView.addItemDecoration(divider2);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(CategoryActivity.getInstance(), 2);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<resObj<List<Product>>> call, Throwable t) {
                Log.d("myList12", "FAIL");
            }
        }   );
    }

    private void AnhXa() {
        recyclerView = mView.findViewById(R.id.rycvanhoc);
    }

}
