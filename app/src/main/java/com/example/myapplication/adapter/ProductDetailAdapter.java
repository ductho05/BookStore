package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.home.SearchActivity;
import com.example.myapplication.activity.productdetail.ProductDetailActivity;
import com.example.myapplication.model.Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.ViewHolder>{
    private static final String TAG = "ProductDetailAdapter";
    private List<Product> productList;

    private Context mContext;


    public ProductDetailAdapter(List<Product> productList, Context mContext) {
        this.productList = productList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ProductDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("AAAAANJUS", mContext.toString());
        if (mContext.toString().contains("SearchActivity")) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, null);
            return new ViewHolder(view);
        }
        else if (mContext.toString().contains("HomeActivity") || mContext.toString().contains("ProductDetailActivity")) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productitem, null);
            return new ViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cate, null);
            return new ViewHolder(view);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductDetailAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        Glide.with(mContext).load(product.getImages()).into(holder.productImage);
        holder.productName.setText(product.getTitle());
        holder.productPriceDiscount.setText(String.valueOf(NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(product.getPrice())));
        double number = (( product.getOld_price() - product.getPrice()) / product.getOld_price()) * 100;
        number = Math.round(number *100) / 100;
        holder.productDiscount.setText("-" + number + "%");
        holder.productPrice.setText(String.valueOf(NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(product.getOld_price())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ProductDetailActivity.class);
                intent.putExtra("_id", product.get_id());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productName;
        private TextView productPriceDiscount;
        private TextView productDiscount;
        private TextView productPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.productImage);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productPriceDiscount = (TextView) itemView.findViewById(R.id.productPriceDiscount);
            productDiscount = (TextView) itemView.findViewById(R.id.productDiscount);
            productPrice = (TextView) itemView.findViewById(R.id.productPrice);
        }
    }
}
