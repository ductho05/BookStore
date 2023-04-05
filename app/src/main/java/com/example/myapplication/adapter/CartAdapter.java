package com.example.myapplication.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.CartModel;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    ArrayList<CartModel> listDomains;

    public CartAdapter(ArrayList<CartModel> listDomains) {
        this.listDomains = listDomains;
    }


    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.nameItem.setText(String.valueOf(listDomains.get(position).getNameItem()));
        holder.priceItem.setText(String.valueOf(listDomains.get(position).getPriceItem()));
        holder.salePriceItem.setText(String.valueOf(listDomains.get(position).getSalePriceItem()));
        holder.amountItem.setText(String.valueOf(listDomains.get(position).getAmount()));



        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(listDomains.get(position).getImageItem(),
                "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.imageItem);
    }

    @Override
    public int getItemCount() {
        return listDomains.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameItem, priceItem, salePriceItem, amountItem;
        ImageView imageItem;
        public ViewHolder(View itemView) {
            super(itemView);
            nameItem = itemView.findViewById(R.id.nameItem);
            priceItem = itemView.findViewById(R.id.priceItem);
            salePriceItem = itemView.findViewById(R.id.salePriceItem);
            amountItem = itemView.findViewById(R.id.amountItem);
            imageItem = itemView.findViewById(R.id.imageItem);
        }
    }
}

