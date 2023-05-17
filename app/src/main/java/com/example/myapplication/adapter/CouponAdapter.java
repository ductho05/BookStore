package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.CouponModel;

import java.util.ArrayList;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {
    public CouponAdapter(ArrayList<CouponModel> arrayList) {
        this.arrayList = arrayList;
    }

    ArrayList<CouponModel> arrayList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coupon, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameCoupon.setText(String.valueOf(arrayList.get(position).getNameCoupon()));
        holder.rangeApply.setText(String.valueOf(arrayList.get(position).getRangeApply()));
        holder.dateExpired.setText(String.valueOf(arrayList.get(position).getDateExpired()));
        holder.hourExpired.setText(String.valueOf(arrayList.get(position).getHourExpired()));
        holder.couponCode.setText(String.valueOf(arrayList.get(position).getCouponCode()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameCoupon, rangeApply, dateExpired,hourExpired, couponCode;
        public ViewHolder(View itemView) {
            super(itemView);
            nameCoupon = itemView.findViewById(R.id.tv_CouponName);
            rangeApply = itemView.findViewById(R.id.tv_RangeApplyCoupon);
            dateExpired = itemView.findViewById(R.id.tv_dateExpiredCoupon);
            hourExpired = itemView.findViewById(R.id.tv_hourExpiredCoupon);
            couponCode = itemView.findViewById(R.id.tv_CouponCode);

        }
    }
}
