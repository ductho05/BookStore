package com.example.myapplication.activity.checkout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.home.HomeActivity;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.api.MapApiService;
import com.example.myapplication.constants.AppConstants;
import com.example.myapplication.model.CartItemModel;
import com.example.myapplication.model.NominatimResult;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.OrderItem;
import com.example.myapplication.model.resObj;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payment_Method extends AppCompatActivity {

    private RadioButton rb_DeliveryCharges;
    private TextView shippingDate;
    private TextView price;
    private TextView shippingCost;
    private TextView total_price;
    private Button btn_order;
    private ImageView btn_back;

    ArrayList<CartItemModel> cartItems = new ArrayList<>();
    Order order = new Order();
    ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_payment_method);
        AnhXa();
        ClickOnThing();

        findViewById(R.id.btn_back).setOnClickListener(view -> {
            noticeCancelCheckout();
        });

        Intent intents = getIntent();
        Bundle bundle = getIntent().getExtras();
        order = (Order) intents.getSerializableExtra("order");
        Log.d("Order: ",String.valueOf(order.getName()));
        orderItems = (ArrayList<OrderItem>) intents.getSerializableExtra("orderItems");
        cartItems = bundle.getParcelableArrayList("cartItems");

        float addressShipping_Lat = intents.getFloatExtra("lat", 0);
        float addressShipping_Lon = intents.getFloatExtra("lon", 0);

        float distance = calculateDistance(10.8518124, 106.768695, addressShipping_Lat, addressShipping_Lon);
        float shipping_cost = calculateShippingCost(distance);

        order.setShippingCost(shipping_cost);
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_YEAR, 1);
        Date processingDate = today.getTime();

        double speed = 60;

        Date shipping_date = calculateExpectedDeliveryDate(processingDate, distance, speed);
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy", Locale.getDefault());
        String formattedDate = formatter.format(shipping_date);
        order.setDeliveryDate(String.valueOf(shipping_date));

        rb_DeliveryCharges.setText("Giao hàng tiêu chuẩn: "+ NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(shipping_cost)
        );
        shippingDate.setText("Ngày giao hàng: "+ formattedDate);
        price.setText(String.valueOf(NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(order.getPrice())));
        shippingCost.setText(String.valueOf(NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(shipping_cost)));
        float totalPrice = order.getPrice() + shipping_cost;
        total_price.setText(String.valueOf(NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(totalPrice)));
        btn_order.setOnClickListener(view -> {

            ApiService.apiService.addOrder(order).enqueue(new Callback<resObj<Order>>() {
                @Override
                public void onResponse(Call<resObj<Order>> call, Response<resObj<Order>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String id = response.body().getData().get_id();
                        for (OrderItem oi:orderItems) {
                            oi.setOrder(id);
                            ApiService.apiService.addOrderItem(oi).enqueue(new Callback<OrderItem>() {
                                @Override
                                public void onResponse(Call<OrderItem> call, Response<OrderItem> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        Log.e("Thêm order item: ", "Thành công");
                                    }
                                }
                                @Override
                                public void onFailure(Call<OrderItem> call, Throwable t) {
                                    Log.e("Thêm order item: ", "Thất bại");
                                }
                            });
                        }

                        for (int i = 0; i < cartItems.size(); i++) {
                            String id_cartItem = cartItems.get(i).get_id();
                            Log.e("id_cartItem", id_cartItem);
                            ApiService.apiService.deleteCartItem(id_cartItem).enqueue(new Callback<resObj<String>>() {
                                @Override
                                public void onResponse(Call<resObj<String>> call, Response<resObj<String>> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        Log.e("Xóa CartItem: ", "Thành công");
                                    } else {
                                        Log.e("Xóa CartItem: ", "Thất bại");
                                    }
                                }

                                @Override
                                public void onFailure(Call<resObj<String>> call, Throwable t) {
                                    Log.e("Xóa CartItem: ", t.getMessage());
                                }
                            });
                        }
                        Intent intent = new Intent(Payment_Method.this, OrderPlaced_Activity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
                    } else {
                        Toast.makeText(getApplicationContext(), "Thanh toán đơn hàng không thành công", Toast.LENGTH_SHORT);
                    }
                }

                @Override
                public void onFailure(Call<resObj<Order>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Lỗi! Không thể thanh toán đơn hàng", Toast.LENGTH_SHORT);
                }
            });

        });

    }

    private void ClickOnThing() {
        btn_back.setOnClickListener(view -> {
            supportFinishAfterTransition();
            Intent intent = new Intent(Payment_Method.this, HomeActivity.class);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(Payment_Method.this);
            Toast.makeText(Payment_Method.this, "Hủy thanh toán đơn hàng này", Toast.LENGTH_SHORT).show();
            startActivity(intent, optionsCompat.toBundle());
        });
    }
    @Override
    public void onBackPressed() {
        noticeCancelCheckout();
    }
    private void noticeCancelCheckout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.notice_cancel, null);

        builder.setView(viewDialog);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button buttonOK = viewDialog.findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(view -> {
            Intent intent1 = new Intent(Payment_Method.this, HomeActivity.class);
            startActivity(intent1);
            overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
        });
    }

    private Date calculateExpectedDeliveryDate(Date processingDate, float distance, double speed) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(processingDate);
        double travelTime = distance / speed; // Thời gian vận chuyển từ kho hàng đến địa chỉ nhận hàng
        int days = (int) Math.ceil(travelTime / 24); // Số ngày cần để vận chuyển hàng
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    private float calculateShippingCost(float distance) {
        float rate = (float) 3;
        float cost = (float) (rate * distance);
        return cost;
    }

    private float calculateDistance(double fromLat, double fromLng, double toLat, double toLng) {
        Location fromLocation = new Location("");
        fromLocation.setLatitude(fromLat);
        fromLocation.setLongitude(fromLng);

        Location toLocation = new Location("");
        toLocation.setLatitude(toLat);
        toLocation.setLongitude(toLat);

        float distance = fromLocation.distanceTo(toLocation);
        return distance / 1000;
    }

    private void AnhXa() {
        btn_back = findViewById(R.id.btn_back);
        rb_DeliveryCharges = findViewById(R.id.rb_DeliveryCharges);
        shippingDate = findViewById(R.id.shipping_date);
        price = findViewById(R.id.price);
        shippingCost = findViewById(R.id.shippingCost);
        total_price = findViewById(R.id.total_price);
        btn_order = findViewById(R.id.btn_order);
    }
}