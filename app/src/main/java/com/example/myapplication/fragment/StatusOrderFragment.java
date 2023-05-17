package com.example.myapplication.fragment;


import static com.example.myapplication.api.ApiService.apiService;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.R;
import com.example.myapplication.activity.order.StatusOrderActivity;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.model.OrderModel;
import com.example.myapplication.model.StatusOrder;
import com.example.myapplication.model.resObj;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusOrderFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    StatusOrder mStatusOrder;

    private Handler handler;
    ConstraintLayout layoutOrderChoXacNhan;
    ProgressBar progressBar;
    int currentPosition;
    SwipeRefreshLayout orderDetailFragment;
    OrderAdapter orderAdapter;
    RecyclerView rcOrder;
    Switch switch1;
    ImageView empty;
    LinearLayout layoutBtn;
    List<OrderModel> listOrder;
    private StatusOrderActivity statusOrderActivity;
    private TextView tv_order_status1, tv_order_status2, tv_order_status3, tv_order_status4, tv_order_status5;

    DividerItemDecoration divider;
    View view;
    Button btnTuChoiOrder,btnChapNhanOrder;

//    public StatusOrderFragment() {
//    }

    public StatusOrderFragment(StatusOrder statusOrder) {
        mStatusOrder = statusOrder;
    }



//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        divider = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
//        // Đặt lịch tự động gọi phương thức getOrders() mỗi 5 phút
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                getOrders(mStatusOrder, "64477d8318a87d6e84a366d0");
//            }
//        }, 0, 1 * 5 * 1000); // thời gian tính bằng mili giây (5 phút = 5 * 60 * 1000 mili giây)
//    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order_detail, container, false );
        AnhXa();
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tv_order_status1.setVisibility(View.VISIBLE);
                tv_order_status2.setVisibility(View.GONE);
                getOrders(mStatusOrder, "64477d8318a87d6e84a366d0", true);
            } else {
                tv_order_status2.setVisibility(View.VISIBLE);
                tv_order_status1.setVisibility(View.GONE);
                getOrders(mStatusOrder, "64477d8318a87d6e84a366d0", false);
            }
        });

        boolean a = statusOrderActivity.getSort();
        Log.d("filt212er", String.valueOf(a));
        getOrders(mStatusOrder, "64477d8318a87d6e84a366d0", statusOrderActivity.getSort());
        return view;
    }
    Runnable hideProgressBarRunnable = new Runnable() {
        @Override
        public void run() {
            progressBar.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }
    };

    private void AnhXa() {
        empty = view.findViewById(R.id.empty);
        //linearLayout2  = view.findViewById(R.id.line);
        layoutOrderChoXacNhan = view.findViewById(R.id.layoutOrderChoXacNhan);
        progressBar = view.findViewById(R.id.progressBar2);
        switch1 = view.findViewById(R.id.switch1);
        statusOrderActivity = (StatusOrderActivity) getActivity();
        divider = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        rcOrder = view.findViewById(R.id.rc_OrderList);
        orderDetailFragment = view.findViewById(R.id.orderDetailFragment);
        orderDetailFragment.setOnRefreshListener(this);
        tv_order_status1 = view.findViewById(R.id.tv_order_status1);
        tv_order_status2 = view.findViewById(R.id.tv_order_status2);
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

    private void getOrders(StatusOrder mStatusOrder, String userId, boolean sort) {
        apiService.getAllOrder(userId, mStatusOrder.name()).enqueue(new Callback<resObj<List<OrderModel>>>() {
            @Override
            public void onResponse(Call<resObj<List<OrderModel>>> call, Response<resObj<List<OrderModel>>> response) {
                if (response.isSuccessful()) {
                    listOrder = response.body().getData();
                    if (listOrder.size() == 0) {
                        progressBar.setVisibility(View.VISIBLE);
                        handler = new Handler();
                        handler.postDelayed(hideProgressBarRunnable, 4000); // 5000ms = 5 giây
                        switch1.setVisibility(View.GONE);
                        tv_order_status1.setVisibility(View.GONE);
                        tv_order_status2.setVisibility(View.GONE);
                    }
                    else {
                        progressBar.setVisibility(View.GONE);
                    }
                    if (sort) {
                        Collections.reverse(listOrder);
                    }
                    //orderAdapter = new OrderAdapter(getContext(), listOrder, "https://www.facebook.com/photo/?fbid=1517457375429896&set=a.392833837892261");
                    orderAdapter = new OrderAdapter(getContext(), listOrder);
                    rcOrder.setHasFixedSize(true);
                    LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
                    //layoutManager.setReverseLayout(true);
                    rcOrder.setLayoutManager(layoutManager);
                    rcOrder.removeItemDecoration(divider);
                    rcOrder.addItemDecoration(divider);
                    rcOrder.setAdapter(orderAdapter);
                    orderAdapter.notifyDataSetChanged();
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
        getOrders(mStatusOrder, "64477d8318a87d6e84a366d0", statusOrderActivity.getSort());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                orderDetailFragment.setRefreshing(false);
            }
        }, 500);
    }
}
