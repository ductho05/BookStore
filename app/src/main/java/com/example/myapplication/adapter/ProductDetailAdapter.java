package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.Product;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.ViewHolder> {
    private static final String TAG = "ProductDetailAdapter";
    private List<Product> productList;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public ProductDetailAdapter(List<Product> productList, Context mContext) {
        this.productList = productList;
        this.mContext = mContext;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ProductDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.productitem, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        Glide.with(mContext).load(product.getProductImage()).into(holder.productImage);
        holder.productName.setText(product.getProductName());
        holder.productPriceDiscount.setText(String.valueOf(NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(product.getProductPrice() * (1 - product.getProductDiscount()))));
        holder.productDiscount.setText("-" + String.valueOf(product.getProductDiscount() * 100) + "%");
        holder.productPrice.setText(String.valueOf(NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(product.getProductPrice())));

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
