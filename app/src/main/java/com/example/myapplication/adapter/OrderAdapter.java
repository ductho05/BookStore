package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.order.DetailOrderItemActivity;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.OrderModel;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder>{

    Context context;
    List<OrderModel> array;

    String firstImageBook;
    public OrderAdapter(Context context, List<OrderModel> array, String firstImageBook) {
        this.context = context;
        this.array = array;
        this.firstImageBook = firstImageBook;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OrderModel order = array.get(position);
        if(order != null){
            holder.id = order.get_id();
            holder.Status = order.getStatus();
            int price = (int) order.getPrice();
            holder.nameItem.setText("Người nhận: (Anh/Chị) " + order.getName());
        holder.price.setText( "Tổng cộng: " + String.format("%,d",
                Math.round(order.getPrice())) + "đ");
        holder.quality.setText("Số lượng: " + order.getQuantity() + " sản phẩm");
        Glide.with(context).load("https://www.facebook.com/photo/?fbid=1517457375429896&set=a.392833837892261").into(holder.images);


        }
    }

    @Override
    public int getItemCount() {
        return array == null ? 0 : array.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price, quality, nameItem;
        public ImageView images;
        private String id,Status;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameItem = (TextView) itemView.findViewById(R.id.nameItem);
            price = (TextView) itemView.findViewById(R.id.salePriceItem);
            quality = (TextView) itemView.findViewById(R.id.quality);
            images =(ImageView) itemView.findViewById(R.id.imageItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("OrderId", id);
                    Log.e("OrderId", id);
                    Intent intent = new Intent(context, DetailOrderItemActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
