package com.example.myapplication.fragment;


import static com.example.myapplication.api.ApiService.apiService;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.R;
import com.example.myapplication.activity.account.LoginManager;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.model.OrderModel;
import com.example.myapplication.model.StatusOrder;
import com.example.myapplication.model.resObj;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusOrderFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    StatusOrder mStatusOrder;
    SwipeRefreshLayout orderDetailFragment;
    OrderAdapter orderAdapter;
    RecyclerView rcOrder;
    LinearLayout layoutBtn;
    List<OrderModel> listOrder;
    View view;
    Button btnTuChoiOrder,btnChapNhanOrder;
    LoginManager loginManager;
    String user;

//    public StatusOrderFragment() {
//    }

    public StatusOrderFragment(StatusOrder statusOrder) {
        mStatusOrder = statusOrder;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order_detail, container, false );
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        loginManager = new LoginManager(sharedPreferences);
        user = sharedPreferences.getString("id", "");
        AnhXa();
        getOrders(mStatusOrder, user);
        return view;
    }
    private void AnhXa() {
        rcOrder = view.findViewById(R.id.rc_OrderList);
        orderDetailFragment = view.findViewById(R.id.orderDetailFragment);
        orderDetailFragment.setOnRefreshListener(this);
//            if(mStatusOrder == StatusOrder.CHOXACNHAN){
//                layoutBtn.setVisibility(View.VISIBLE);
//            }
//            else {
//                layoutBtn.setVisibility(View.INVISIBLE);
//            }
    }

//    // Lấy bức ảnh đầu tiên của sản phẩm trong đơn hàng
//    private String getFirstImage(List<OrderModel> listOrder) {
//        String firstImage = "";
//        for (OrderModel orderModel : listOrder) {
//
//        }
//        return firstImage;
//    }

    private void getOrders(StatusOrder mStatusOrder, String userId) {
        apiService.getAllOrder(userId, String.valueOf(mStatusOrder)).enqueue(new Callback<resObj<List<OrderModel>>>() {
            @Override
            public void onResponse(Call<resObj<List<OrderModel>>> call, Response<resObj<List<OrderModel>>> response) {
                if (response.isSuccessful()) {
                    listOrder = response.body().getData();
                    Log.d("TA312312G", "onResponse: " + mStatusOrder.toString());
                    orderAdapter = new OrderAdapter(getContext(), listOrder, "https://www.facebook.com/photo/?fbid=1517457375429896&set=a.392833837892261");
                    rcOrder.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
                    rcOrder.setLayoutManager(layoutManager);
                    rcOrder.setAdapter(orderAdapter);
                    //orderAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<resObj<List<OrderModel>>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onRefresh() {
        getOrders(mStatusOrder, "64477d8318a87d6e84a366d0");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                orderDetailFragment.setRefreshing(false);
            }
        }, 5000);
    }
}
