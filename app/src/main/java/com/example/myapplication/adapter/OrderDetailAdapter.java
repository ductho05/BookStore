package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.account.EvaluateActivity;
import com.example.myapplication.model.Evaluate;
import com.example.myapplication.model.OrderItemModel;
import com.example.myapplication.model.OrderModel;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.StatusOrder;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViemHolder>{

    private Context mContext;
    int flag = 0;
    private List<OrderItemModel> orderItemModels;

    private String _status = "DAGIAO";
    public OrderDetailAdapter(Context mContext, List<OrderItemModel> orderItemModels, String status) {
        this.mContext = mContext;
        this.orderItemModels = orderItemModels;
        this._status = status;
    }

    @NonNull
    @Override
    public OrderDetailAdapter.ViemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_order, null);
        return new ViemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViemHolder holder, int position) {
        Product product = orderItemModels.get(position).getProduct();
        String quantityProduct = String.valueOf(orderItemModels.get(position).getQuantity());
        Glide.with(mContext).load(product.getImages()).into(holder.productImage);
        holder.productName.setText(product.getTitle());
        holder.productPriceDiscount.setText(String.valueOf(NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(product.getPrice())));
        double number = (( product.getOld_price() - product.getPrice()) / product.getOld_price()) * 100;
        number = Math.round(number *100) / 100;
        holder.productDiscount.setText("-" + number + "%");
        holder.productPrice.setText(String.valueOf(NumberFormat.getCurrencyInstance(
                new Locale("vi", "VN")).format(product.getOld_price())));
        holder.quantity.setText("x" + quantityProduct);

        if (!_status.equals("DAGIAO")) {
            holder.btn_eval.setEnabled(false);
            holder.btn_eval.setBackgroundColor(Color.rgb(245,246,250));
            holder.btn_eval.setText("Chưa hoàn thành đơn hàng");
        } else {
            holder.btn_eval.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, EvaluateActivity.class);
                    intent.putExtra("_id", product.get_id());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return orderItemModels.size();
    }

    class ViemHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productName, productPriceDiscount, productDiscount, quantity
                ,productPrice;
        private Button btn_eval;

        public ViemHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPriceDiscount = itemView.findViewById(R.id.productPriceDiscount);
            productDiscount = itemView.findViewById(R.id.productDiscount);
            quantity = itemView.findViewById(R.id.quantity);
            productPrice = itemView.findViewById(R.id.productPrice);
            btn_eval = itemView.findViewById(R.id.btn_val2);
        }
    }
}
