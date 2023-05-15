package com.example.myapplication.fragment;

import static com.example.myapplication.api.ApiService.apiService;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
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
import com.example.myapplication.helper.PaginationScroll;
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
import retrofit2.http.Query;


public class AllFragment extends Fragment {


    private String optionfilter = null;
    private String sort = null;
    private boolean isLoading;
    private boolean isLastPage;
    private int currentPage = 1;
    private int totalPage = 150;

    private ProgressBar progressBar;

    public static final int MAX = 8;
    public View mView;
    private Spinner spinner;
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter = null;

    private CategoryActivity categoryActivity;

    private OptionFitler optionFitler;

    public List<Product> myList;
    String _categoryId;
    String _searchText;




    public AllFragment(String categoryId, String searchText) {
        _categoryId = categoryId;
        _searchText = searchText;
    }



    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_all, container, false);
        AnhXa();
        Log.d("tâsasaest", "onCreateView: " + _categoryId + " " + _searchText);
        SetSpiner(categoryActivity.getFilter());
        return mView;
    }

    private void SetSpiner(String filter) {
        List<String> list = new ArrayList<>();
        list.add("Trending");
        list.add("Mới nhất");
        list.add("Bán chạy nhất");
        list.add("Giá thấp nhất");
        list.add("Giá cao nhất");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(CategoryActivity.getInstance(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (filter != null) {
            int spinnerPosition = adapter.getPosition(filter);
            spinner.setSelection(spinnerPosition);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if ("Trending".equals(spinner.getSelectedItem().toString()))
                    GetData();
                else
                    GetSelectedFilter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //GetData();
            }
        }   );

    }

    public void GetSelectedFilter () {
        String filter = spinner.getSelectedItem().toString();
        Call<resObj<List<Product>>> option = null;

        if (Objects.equals(filter, "Mới nhất")) {
            optionfilter = "published_date";
            sort = "desc";
        }
        else if (Objects.equals(filter, "Bán chạy nhất")) {
            optionfilter = "sold";
            sort = "desc";
        }
        else if (Objects.equals(filter, "Giá thấp nhất"))
        {
            optionfilter = "price";
            sort = "asc";
        }
        else
        {
            optionfilter = "price";
            sort = "desc";
        }
        option = apiService.getFilterProduct(_categoryId, optionfilter, 1, MAX, sort, 0, _searchText);
        Log.d("myLis23t1221", "myList.toString()");
        option.enqueue(new Callback<resObj<List<Product>>>() {
            @Override
            public void onResponse(Call<resObj<List<Product>>> call, Response<resObj<List<Product>>> response) {
                if (response.isSuccessful()) {
                    resObj<List<Product>> resObj = response.body();
                    myList = resObj.getData();
                    Log.d("myList123232", myList.toString());
                    adapter= new ProductDetailAdapter(myList, CategoryActivity.getInstance());
                    recyclerView.setHasFixedSize(true);
                    DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
                    DividerItemDecoration divider2 = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL);
                    recyclerView.addItemDecoration(divider);
                    recyclerView.addItemDecoration(divider2);
                    LinearLayoutManager layoutManager = new GridLayoutManager(CategoryActivity.getInstance(), 2);
                    //LinearLayoutManager layoutManager = new LinearLayoutManager(CategoryActivity.getInstance());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    recyclerView.addOnScrollListener(new PaginationScroll(layoutManager) {

                        @Override
                        public void loadMoreItems() {
                            isLoading = true;
                            progressBar.setVisibility(View.VISIBLE);
                            currentPage += 1;
                            if (currentPage <= totalPage) {
                                loadNextPage(optionfilter, sort, currentPage);
                            }
                        }

                        @Override
                        public boolean isLoading() {
                            return isLoading;
                        }

                        @Override
                        public boolean isLastPage() {
                            return isLastPage;
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<resObj<List<Product>>> call, Throwable t) {
                Log.d("myList12", "FAIL");
            }
        }   );
    }

    private void loadNextPage(String  optionfilter, String sort, int page) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                apiService.getFilterProduct(_categoryId, optionfilter, page, MAX, sort, 0, _searchText).enqueue(new Callback<resObj<List<Product>>>() {
                    @Override
                    public void onResponse(Call<resObj<List<Product>>> call, Response<resObj<List<Product>>> response) {
                        if (response.isSuccessful()) {
                            resObj<List<Product>> resObj = response.body();
                            List<Product> list = resObj.getData();
                            myList.addAll(list);
                            adapter.notifyDataSetChanged();
                            isLoading = false;
                            progressBar.setVisibility(View.GONE);
                            if (currentPage == totalPage)
                            {
                                isLastPage = true;
                            }
                            Log.d("myList1221", myList.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<resObj<List<Product>>> call, Throwable t) {
                        Log.d("myList12", "FAIL");
                    }
                });
            }
        }, 3000);
    }

    private void GetData() {
        ApiService.apiService.getFilterProduct(_categoryId, null, 1, MAX, null, 0, _searchText).enqueue(new Callback<resObj<List<Product>>>() {
            @Override
            public void onResponse(Call<resObj<List<Product>>> call, Response<resObj<List<Product>>> response) {
                if (response.isSuccessful()) {
                    resObj<List<Product>> resObj = response.body();
                    myList = resObj.getData();
                    Log.d("myList12", myList.toString());
                    adapter = new ProductDetailAdapter(myList, CategoryActivity.getInstance());
                    recyclerView.setHasFixedSize(true);
                    DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
                    DividerItemDecoration divider2 = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL);
                    recyclerView.addItemDecoration(divider);
                    recyclerView.addItemDecoration(divider2);
                    LinearLayoutManager layoutManager = new GridLayoutManager(CategoryActivity.getInstance(), 2);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    recyclerView.addOnScrollListener(new PaginationScroll(layoutManager) {

                        @Override
                        public void loadMoreItems() {
                            isLoading = true;
                            progressBar.setVisibility(View.VISIBLE);
                            currentPage += 1;
                            if (currentPage <= totalPage) {
                                loadNextPage(optionfilter, sort, currentPage);
                            }
                        }

                        @Override
                        public boolean isLoading() {
                            return isLoading;
                        }

                        @Override
                        public boolean isLastPage() {
                            return isLastPage;
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<resObj<List<Product>>> call, Throwable t) {
                Log.d("myList12", "FAIL");
            }
        });
    }

    private void AnhXa() {
        recyclerView = mView.findViewById(R.id.rycall);
        spinner = mView.findViewById(R.id.spinner);
        categoryActivity = (CategoryActivity) getActivity();
        progressBar = mView.findViewById(R.id.progressBar);
    }

}
