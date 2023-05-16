package com.example.myapplication.activity.cart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.checkout.CheckOut_Address_Activity;
import com.example.myapplication.activity.home.HomeActivity;
import com.example.myapplication.activity.productdetail.ProductDetailActivity;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.CartItemModel;
import com.example.myapplication.model.resObj;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnItemClickListener,CartAdapter.OnItemEventListener{
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private CheckBox cbAll;
    private TextView totalPrice;
    private Button btn_checkout;
    String user = "64477d8318a87d6e84a366d0";
    List<CartItemModel> cartItems;
    double total_price = 0;
    private ArrayList<CartItemModel> listCartItem = new ArrayList<>();
    private ArrayList<CartItemModel> checkedItems = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cart);

        AnhXa();
        setViewCart();

        btn_checkout.setOnClickListener(view -> {
            Set<CartItemModel> set = new HashSet<>(listCartItem);
            listCartItem.clear();
            listCartItem.addAll(set);
            for (CartItemModel i:listCartItem) {
                Log.e("ListCartItem Cart: ", i.getProduct().getTitle());
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("listCartItem", listCartItem);
            Intent intent = new Intent(CartActivity.this, CheckOut_Address_Activity.class);
            intent.putExtras(bundle);
            intent.putExtra("total_price", total_price);
            startActivity(intent);
            finish();
        });
    }
    public void AnhXa() {
        cbAll = findViewById(R.id.cbAll);
        totalPrice = findViewById(R.id.total_price);
        btn_checkout = findViewById(R.id.btn_checkout);
        recyclerView = findViewById(R.id.recycleView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = getIntent();
        String productId = intent1.getStringExtra("_id");
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("_id", productId);
        startActivity(intent);
        overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
    }

    private void noticeDeleteCart() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.notice_delete_cart, null);

        builder.setView(viewDialog);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button buttonOK = viewDialog.findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(view -> {
            cbAll.setChecked(false);
            Intent intent1 = getIntent();
            String productId = intent1.getStringExtra("_id");
            Intent intent = new Intent(CartActivity.this, CartActivity.class);
            intent.putExtra("_id", productId);
            startActivity(intent);
            overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
        });
    }

    public void setViewCart() {
        ApiService.apiService.getAllCartItemByUser(user).enqueue(new Callback<resObj<List<CartItemModel>>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<resObj<List<CartItemModel>>> call, Response<resObj<List<CartItemModel>>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    cartItems =  (List<CartItemModel>) response.body().getData();
                    if (cartItems != null) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setClipToPadding(true);
                        recyclerView.setHasFixedSize(true);
                        adapter = new CartAdapter(CartActivity.this, cartItems, CartActivity.this,total_price, recyclerView, checkedItems, (CartAdapter.OnItemEventListener) CartActivity.this);
                        recyclerView.setAdapter(adapter);

                        cbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                adapter.setMasterChecked(b);
                            }
                        });

                        int numItem = 0;
                        numItem = cartItems.size();
                        cbAll.setText("Chọn tất cả ("+ numItem + " sản phẩm)");
                        totalPrice.setText(NumberFormat.getCurrencyInstance(
                                new Locale("vi", "VN")).format(total_price));

                        if (total_price == 0) {
                            btn_checkout.setEnabled(false);
                            btn_checkout.setBackgroundColor(Color.rgb(254, 232, 176));
                        }

                    } else {
                        Log.e("Cart: ","Trống");
                    }
                }
            }

            @Override
            public void onFailure(Call<resObj<List<CartItemModel>>> call, Throwable t) {
                Log.e("Cart: ",t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int position, boolean isChecked, double total_price, ArrayList<CartItemModel> checkedItems) {
        this.total_price = total_price;
        this.checkedItems = checkedItems;
        if (checkedItems != null) {
            listCartItem.addAll(checkedItems);
        }
        totalPrice.setText(NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(total_price));
        if (total_price == 0) {
            btn_checkout.setEnabled(false);
            btn_checkout.setBackgroundColor(Color.rgb(245,246,250));

        } else {
            btn_checkout.setEnabled(true);
            btn_checkout.setBackgroundColor(Color.rgb(77,177,136));
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onItemEvent(boolean isDelete,double total_price) {
        this.total_price = total_price;
        adapter.notifyDataSetChanged();
        totalPrice.setText(NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(total_price));
        if (isDelete) {
            noticeDeleteCart();
        }

    }
}
