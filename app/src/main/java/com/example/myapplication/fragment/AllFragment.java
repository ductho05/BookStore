package com.example.myapplication.fragment;

import static com.example.myapplication.api.ApiService.apiService;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.home.CategoryActivity;
import com.example.myapplication.activity.home.HomeActivity;
import com.example.myapplication.activity.home.SearchActivity;
import com.example.myapplication.adapter.ProductDetailAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.OptionFitler;
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


    public static final String MAX = "10";
    public View mView;
    private Spinner spinner;
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter = null;


    private OptionFitler optionFitler;

    public List<Product> myList;


    public AllFragment() {

    }

//    public AllFragment(OptionFitler optionFitler) {
//        this.optionFitler = optionFitler;
//    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_all, container, false);
        AnhXa();
        SetSpiner();
        return mView;

    }

    private void SetSpiner() {
        List<String> list = new ArrayList<>();
        list.add("Trending");
        list.add("Mới nhất");
        list.add("Bán chạy");
        list.add("Giá thấp");
        list.add("Giá cao");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(CategoryActivity.getInstance(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                GetSelectedFilter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                GetData();
            }
        }   );

    }

    public void GetSelectedFilter () {
        String filter = spinner.getSelectedItem().toString();
        Call<resObj> option = null;
        if (Objects.equals(filter, "Mới nhất"))
            option = apiService.getNewProduct("1");
        else if (Objects.equals(filter, "Bán chạy"))
            option = apiService.getBestSellerProduct("1");
        else if (Objects.equals(filter, "Giá thấp"))
            option = apiService.getLowestProduct("1");
        else if (Objects.equals(filter, "Giá cao"))
            option = apiService.getLowestProduct("1");
        else
            option = apiService.getBestSellerProduct("1");
        option.enqueue(new Callback<resObj>() {
            @Override
            public void onResponse(Call<resObj> call, Response<resObj> response) {
                if (response.isSuccessful()) {
                    resObj resObj = response.body();
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
            public void onFailure(Call<resObj> call, Throwable t) {
                Log.d("myList12", "FAIL");
            }
        }   );
    }

//    private Call<resObj> GetFilter() {
//        String filter = getIntent().getStringExtra("Filter");
//        Call<resObj> option = null;
//        if (Objects.equals(filter, "NEW"))
//            option = apiService.getNewProduct("1");
//        else if (Objects.equals(filter, "SALE"))
//            option = apiService.getBestSellerProduct("1");
//        else
//            option = apiService.getLowestProduct("1");
//        return option;
//    }

    private void GetData() {
        ApiService.apiService.getBestSellerProduct("645b0835a916f1a8008b68d2").enqueue(new Callback<resObj>() {
            @Override
            public void onResponse(Call<resObj> call, Response<resObj> response) {
                if (response.isSuccessful()) {
                    resObj resObj = response.body();
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
            public void onFailure(Call<resObj> call, Throwable t) {
                Log.d("myList12", "FAIL");
            }
        }   );
    }

    private void AnhXa() {
        recyclerView = mView.findViewById(R.id.rycall);
        spinner = mView.findViewById(R.id.spinner);
    }

}
