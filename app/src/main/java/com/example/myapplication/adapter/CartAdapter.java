package com.example.myapplication.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.helper.ManagementCart;
import com.example.myapplication.model.CartModel;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<CartModel> listDomains;
    ManagementCart managementCart;

    public CartAdapter(ArrayList<CartModel> listDomains, ManagementCart managementCart) {
        this.listDomains = listDomains;
        this.managementCart = managementCart;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CartModel cart = listDomains.get(position);
        holder.nameItem.setText(String.valueOf(cart.getNameItem()));
        holder.priceItem.setText(String.valueOf(cart.getOriginalPriceItem()));
        holder.salePriceItem.setText(String.valueOf(cart.getSalePriceItem()));
        holder.amountItem.setText(String.valueOf(cart.getAmount()));


        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(cart.getImageItem(),
                "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.imageItem);

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cart.getAmount() <= 2) {
                    holder.btnPlus.setVisibility(View.VISIBLE);
                    holder.btnMinus.setVisibility(View.GONE);
                    cart.setAmount(cart.getAmount() - 1);
                    notifyDataSetChanged();
                } else {
                    cart.setAmount(cart.getAmount() - 1);
                    notifyDataSetChanged();
                }

            }
        });
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cart.getAmount() >= 1) {
                    holder.btnPlus.setVisibility(View.VISIBLE);
                    holder.btnMinus.setVisibility(View.VISIBLE);
                }
                cart.setAmount(cart.getAmount() + 1);
                notifyDataSetChanged();

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDomains.remove(cart);
            }
        });

        holder.cbSelectedItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.cbSelectedItem.isChecked()){
                    managementCart.updatePrice(listDomains, holder.totalPrice);
                }else {
                    holder.totalPrice.setText("0.00");
                }
            }


        });
    }


    @Override
    public int getItemCount() {
        return listDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameItem, priceItem, salePriceItem, amountItem, btnMinus, btnPlus, totalPrice;
        ImageView imageItem, btnDelete;
        CheckBox cbSelectedItem;

        public ViewHolder(View itemView) {
            super(itemView);
            cbSelectedItem = itemView.findViewById(R.id.cbSelectedItem);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            nameItem = itemView.findViewById(R.id.nameItem);
            priceItem = itemView.findViewById(R.id.originalPriceItem);
            salePriceItem = itemView.findViewById(R.id.salePriceItem);
            amountItem = itemView.findViewById(R.id.amountItem);
            imageItem = itemView.findViewById(R.id.imageItem);
            totalPrice = itemView.findViewById(R.id.totalPrice);
        }
    }
}

