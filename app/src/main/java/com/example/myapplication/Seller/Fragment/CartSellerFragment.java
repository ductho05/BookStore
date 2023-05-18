package com.example.myapplication.seller.Fragment;

import static com.example.myapplication.api.ApiService.apiService;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.home.CategoryActivity;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.adapter.ProductDetailAdapter;
import com.example.myapplication.adapter.StatusOrderAdapter;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.Cart;
import com.example.myapplication.model.CartModel;
import com.example.myapplication.model.OrderModel;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.StatusOrder;
import com.example.myapplication.model.resObj;
import com.example.myapplication.seller.activity.GraphViewCategoryActicity;
import com.example.myapplication.seller.activity.GraphViewOrderActicity;
import com.example.myapplication.seller.activity.GraphViewProductActicity;
import com.example.myapplication.seller.activity.GraphViewUserActicity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartSellerFragment extends Fragment {

    View view;
    RecyclerView rc_OrderList;
    ImageView imgAdmin1, imgAdmin2, imgAdmin3, imgAdmin4;
    LinearLayout tvProduct, tvOrder, tvAccount, tvCategory, tvBrand, tvReport, tvSetting, tvLogout, admin1, admin2, admin3, admin4;
    public CartSellerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart_seller, container, false);


        rc_OrderList = view.findViewById(R.id.rc_OrderList);

        //AnhXa();
            apiService.getAllOrder("", "").enqueue(new Callback<resObj<List<OrderModel>>>() {
                @Override
                public void onResponse(Call<resObj<List<OrderModel>>> call, Response<resObj<List<OrderModel>>> response) {
                    if (response.isSuccessful()) {
                        List<OrderModel> listOrder = response.body().getData();
                        Collections.reverse(listOrder);
                        OrderAdapter orderAdapter = new OrderAdapter(getActivity(), listOrder);
                        rc_OrderList.setAdapter(orderAdapter);
                        rc_OrderList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        rc_OrderList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                    }
                }

                @Override
                public void onFailure(Call<resObj<List<OrderModel>>> call, Throwable t) {

                }
            });
        return view;
    }

    private void SetImageAdmin(List <String> listAdminimage) {
        Glide.with(getActivity()).load(listAdminimage.get(0)).into(imgAdmin1);
        Glide.with(getActivity()).load(listAdminimage.get(1)).into(imgAdmin2);
        Glide.with(getActivity()).load(listAdminimage.get(2)).into(imgAdmin3);
        Glide.with(getActivity()).load(listAdminimage.get(3)).into(imgAdmin4);
    }
    private void ClickOneThing(List<String> listAdmin) {
        tvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GraphViewProductActicity.class);
                startActivity(intent);
            }
        });
        tvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GraphViewOrderActicity.class);
                startActivity(intent);
            }
        });

        tvAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GraphViewUserActicity.class);
                startActivity(intent);
            }
        });

        tvCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GraphViewCategoryActicity.class);
                startActivity(intent);
            }
        }   );

        admin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listAdmin.get(0)));
                startActivity(intent);
            }
        });

        admin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAdmin.get(1);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listAdmin.get(1)));
                startActivity(intent);
            }
        });

        admin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAdmin.get(2);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listAdmin.get(2)));
                startActivity(intent);
            }
        });

        admin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAdmin.get(3);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listAdmin.get(3)));
                startActivity(intent);
            }
        });


    }

    private void AnhXa() {


        tvProduct = view.findViewById(R.id.tvptSanpham);
        tvAccount = view.findViewById(R.id.tvptNguoidung);
        tvOrder = view.findViewById(R.id.tvptDonhang);
        tvCategory = view.findViewById(R.id.tvptDanhmuc);
        admin2 = view.findViewById(R.id.admin2);
        admin3 = view.findViewById(R.id.admin3);
        admin4 = view.findViewById(R.id.admin4);
        admin1 = view.findViewById(R.id.admin1);
        imgAdmin1 = view.findViewById(R.id.imgAdmin1);
        imgAdmin2 = view.findViewById(R.id.imgAdmin2);
        imgAdmin3 = view.findViewById(R.id.imgAdmin3);
        imgAdmin4 = view.findViewById(R.id.imgAdmin4);
    }
}