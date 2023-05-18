package com.example.myapplication.seller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.model.Product;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ManagerProductAdapter extends RecyclerView.Adapter<ManagerProductAdapter.ViewHolder>{
    private List<Product> productList;
    private Context mContext;

    public ManagerProductAdapter(List<Product> productList, Context mContext) {
        this.productList = productList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ManagerProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.manager_product_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerProductAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        Glide.with(mContext).load(product.getImages()).into(holder.images);
        holder.title.setText(product.getTitle());
        holder.price.setText(String.valueOf(NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(product.getOld_price())));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  title, price;
        private ImageView images, delete, update;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.pTitle);
            price = itemView.findViewById(R.id.sprice);
            images = itemView.findViewById(R.id.pimages);
            delete = itemView.findViewById(R.id.btnpDelete);
            update = itemView.findViewById(R.id.btnUpdate);
        }
    }
}
