package com.example.myapplication.activity.order;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.OrderDetailAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.OrderItemModel;
import com.example.myapplication.model.OrderModel;
import com.example.myapplication.model.StatusOrder;
import com.example.myapplication.model.resObj;

import java.text.NumberFormat;;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailOrderItemActivity extends AppCompatActivity {

    private TextView shipping_method, status, userName, phoneNumber, address, total_price
    ,payment_method, id, time_order, time_complete;
    private ImageView backToOrderStatus;
    private RecyclerView rv_product;

    private List<OrderItemModel> orderItemList;
    private OrderDetailAdapter orderDetailAdapter;

    private OrderModel order;
    // Set cứng đơn hàng:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order_item);
        AnhXa();
        Bundle bundle = getIntent().getExtras();
        String orderId = bundle.getString("OrderId");
        Log.e("test01", orderId);
        setInfoOrder(orderId);
        setProduct(orderId);

    }

    private void setProduct(String orderId) {
//        Bundle bundle = getIntent().getExtras();
//        String orderId = bundle.getString("OrderId");
        Log.e("test01656", orderId);
        ApiService.apiService.getOrderItemByOrder(orderId).enqueue(new Callback<resObj<List<OrderItemModel>>>() {
            @Override
            public void onResponse(Call<resObj<List<OrderItemModel>>> call, Response<resObj<List<OrderItemModel>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    orderItemList = response.body().getData();
                    orderDetailAdapter = new OrderDetailAdapter(DetailOrderItemActivity.this, orderItemList, order.getStatus());
                    rv_product.setHasFixedSize(true);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailOrderItemActivity.this, LinearLayoutManager.VERTICAL, false);
                    rv_product.setLayoutManager(linearLayoutManager);
                    rv_product.setAdapter(orderDetailAdapter);
                }
            }

            @Override
            public void onFailure(Call<resObj<List<OrderItemModel>>> call, Throwable t) {

            }
        });
    }

    private void setInfoOrder(String orderId) {
//        Bundle bundle = getIntent().getExtras();
//         orderId = bundle.getString("OrderId");
        Log.e("test01", orderId);
        ApiService.apiService.getOrderById(orderId).enqueue(new Callback<resObj<OrderModel>>() {

            @Override
            public void onResponse(Call<resObj<OrderModel>> call, Response<resObj<OrderModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    order = response.body().getData();
                    shipping_method.setText(order.getShipping_method());
                    String statusOrder = order.getStatus();
                    if (statusOrder.equals("DAGIAO")) {
                        status.setText("Đơn hàng đã được giao thành công");
                    } else if (statusOrder.equals("HUY")) {
                        status.setText("Đơn hàng đã bị hủy");
                    } else if (statusOrder.equals("CHOXACNHAN")) {
                        status.setText("Đơn hàng đang chờ xác nhận");
                    } else if (statusOrder.equals("CHOLAYHANG")) {
                        status.setText("Cửa hàng đang lấy hàng");
                    } else if (statusOrder.equals("DANGGIAO")) {
                        status.setText("Đơn hàng đang được giao");
                    }
                    userName.setText(order.getName());
                    phoneNumber.setText(order.getPhone());
                    String addressShip = order.getAddress() + " " + order.getCity();
                    address.setText(addressShip);
                    total_price.setText(NumberFormat.getCurrencyInstance(
                            new Locale("vi", "VN")).format(order.getPrice()));
                    payment_method.setText(order.getPayment_method());
                    id.setText(order.get_id());
                    String timeOrder = order.getCreatedAt();
                    String timeComplete = order.getUpdatedAt();
                    time_order.setText(timeOrder);
                    time_complete.setText(timeComplete);
                }
            }

            @Override
            public void onFailure(Call<resObj<OrderModel>> call, Throwable t) {
                Log.e("Get order: ", t.getMessage());
            }
        });
    }

    private void AnhXa() {
        shipping_method = findViewById(R.id.shipping_method);
        status = findViewById(R.id.status);
        phoneNumber = findViewById(R.id.phoneNumber);
        total_price = findViewById(R.id.total_price);
        payment_method = findViewById(R.id.payment_method);
        id = findViewById(R.id.id);
        time_order = findViewById(R.id.time_order);
        time_complete = findViewById(R.id.time_complete);
        backToOrderStatus = findViewById(R.id.backToOrderStatus);
        rv_product = findViewById(R.id.rv_product);
        userName = findViewById(R.id.userName);
        address = findViewById(R.id.address);
    }
}