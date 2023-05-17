package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.example.myapplication.activity.productdetail.ProductDetailActivity;
import com.example.myapplication.model.Product;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{
    private static final String TAG = "ProductDetailAdapter";
    private List<Product> productList;

    private Context mContext;


    public FavoriteAdapter(List<Product> productList, Context mContext) {
        this.productList = productList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_favorite, null);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
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

        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: add to cart");
            }
        });

        holder.deletefavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: delete favorite");
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

        private ImageView addtocart, deletefavorite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addtocart = (ImageView) itemView.findViewById(R.id.addtocart);
            deletefavorite = (ImageView) itemView.findViewById(R.id.deletefavorite);
            productImage = (ImageView) itemView.findViewById(R.id.productImagefavorite);
            productName = (TextView) itemView.findViewById(R.id.productNamefavorite);
            productPriceDiscount = (TextView) itemView.findViewById(R.id.productPriceDiscountfavorite);
            productDiscount = (TextView) itemView.findViewById(R.id.productDiscount_favorite);
            productPrice = (TextView) itemView.findViewById(R.id.productPricefavorite);
        }
    }
}
