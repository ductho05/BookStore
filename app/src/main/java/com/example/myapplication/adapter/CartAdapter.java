package com.example.myapplication.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.model.CartItem;
import com.example.myapplication.model.CartItemModel;
import com.example.myapplication.model.resObj;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    public interface OnItemClickListener {
        void onItemClick(int position, boolean isChecked, double total_price, ArrayList<CartItemModel> checkedItems);
    }

    public interface OnItemEventListener {
        void onItemEvent(boolean isDelete);
    }
    private List<CartItemModel> listDomains;
    private boolean isMasterChecked = false;
    private boolean isDelete = false;
    private OnItemClickListener listener;
    private OnItemEventListener mOnItemEventListener;
    private double total_price;

    private ArrayList<CartItemModel> checkedItems = new ArrayList<>();

    private CartItemModel mCartItemModel;

    private RecyclerView mRecyclerView;

    private Context mContext;

    public CartAdapter(Context mContext, List<CartItemModel> listDomains, OnItemClickListener listener, double total_price,
                       RecyclerView mRecyclerView, ArrayList<CartItemModel> checkedItems, OnItemEventListener onItemEventListener) {
        this.mContext = mContext;
        this.listDomains = listDomains;
        this.listener = listener;
        this.total_price = total_price;
        this.checkedItems = checkedItems;
        this.mRecyclerView = mRecyclerView;
        this.mOnItemEventListener = onItemEventListener;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.nameItem.setText(String.valueOf(listDomains.get(position).getProduct().getTitle()));
        holder.priceItem.setText(NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(listDomains.get(position).getProduct().getOld_price()));
        holder.priceItem.setPaintFlags(holder.priceItem.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.salePriceItem.setText(NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(listDomains.get(position).getProduct().getPrice()));
        holder.amountItem.setText(String.valueOf(listDomains.get(position).getQuantity()));
        holder.cbSelectedItem.setChecked(listDomains.get(position).isChecked());
        Glide.with(holder.itemView.getContext())
                .load(listDomains.get(position).getProduct().getImages())
                .into(holder.imageItem);
        holder.cbSelectedItem.setChecked(isMasterChecked);

        holder.cbSelectedItem.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            CartItemModel cartItem = listDomains.get(holder.getAdapterPosition());
            cartItem.setChecked(isChecked);
            if (isChecked) {
                total_price += cartItem.getProduct().getPrice();
                checkedItems.add(listDomains.get(holder.getAdapterPosition()));
            } else {
                total_price -= cartItem.getProduct().getPrice();
                checkedItems.remove(listDomains.get(holder.getAdapterPosition()));
            }
            listener.onItemClick(holder.getAdapterPosition(), isChecked, total_price, checkedItems);
        });

        // Update, Delete cart item
        // Giảm số lượng sản phẩm
        holder.btnMinus.setOnClickListener(view -> {
            int quantity = Integer.parseInt(holder.amountItem.getText().toString());
            if (quantity > 1) {
                quantity -= 1;
                CartItemModel cartItem = listDomains.get(holder.getAdapterPosition());
                cartItem.setQuantity(quantity);
                ApiService.apiService.updateCartItem(cartItem.get_id(), cartItem).enqueue(new Callback<resObj<String>>() {
                    @Override
                    public void onResponse(Call<resObj<String>> call, Response<resObj<String>> response) {
                        if (response.isSuccessful()) {

                            mOnItemEventListener.onItemEvent(false);
                        }
                    }
                    @Override
                    public void onFailure(Call<resObj<String>> call, Throwable t) {}
                });
            }
        });

        // Tăng số lượng sản phẩm
        holder.btnPlus.setOnClickListener(view -> {
            int quantity = Integer.parseInt(holder.amountItem.getText().toString());
            quantity += 1;
            CartItemModel cartItem = listDomains.get(holder.getAdapterPosition());
            cartItem.setQuantity(quantity);
            ApiService.apiService.updateCartItem(cartItem.get_id(), cartItem).enqueue(new Callback<resObj<String>>() {
                @Override
                public void onResponse(Call<resObj<String>> call, Response<resObj<String>> response) {
                    if (response.isSuccessful()) {
                        isDelete = false;
                        mOnItemEventListener.onItemEvent(false);
                    }
                }
                @Override
                public void onFailure(Call<resObj<String>> call, Throwable t) {}
            });
        });

        // Xóa sản phẩm
        holder.btnDelete.setOnClickListener(view -> {
            String id = listDomains.get(holder.getAdapterPosition()).get_id();
            ApiService.apiService.deleteCartItem(id).enqueue(new Callback<resObj<String>>() {
                @Override
                public void onResponse(Call<resObj<String>> call, Response<resObj<String>> response) {
                    if (response.isSuccessful()) {
                        isDelete = true;
                        mOnItemEventListener.onItemEvent(true);
                    }
                }
                @Override
                public void onFailure(Call<resObj<String>> call, Throwable t) {}
            });
        });
    }

    @Override
    public int getItemCount() {
        return listDomains.size();
    }
    public void setMasterChecked(boolean isChecked) {
        isMasterChecked = isChecked;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameItem, priceItem, salePriceItem, amountItem;
        public ImageView imageItem, btnMinus, btnPlus, btnDelete;
        public CheckBox cbSelectedItem;
        public ViewHolder(View itemView) {
            super(itemView);
            nameItem = itemView.findViewById(R.id.nameItem);
            priceItem = itemView.findViewById(R.id.originalPriceItem);
            salePriceItem = itemView.findViewById(R.id.salePriceItem);
            amountItem = itemView.findViewById(R.id.amountItem);
            imageItem = itemView.findViewById(R.id.imageItem);
            cbSelectedItem = itemView.findViewById(R.id.cbSelectedItem);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        public void bind(CartItemModel cartItemModel) {
            cartItemModel = mCartItemModel;

        }
        public CartItemModel getItem() {
            return mCartItemModel;
        }
    }
}

