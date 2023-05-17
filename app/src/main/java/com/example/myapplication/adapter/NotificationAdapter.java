package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.NotificationModel;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    ArrayList<NotificationModel> arrayList;

    public NotificationAdapter(ArrayList<NotificationModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Notification.setText(String.valueOf(arrayList.get(position).getNotify()));
        holder.dateNotification.setText(String.valueOf(arrayList.get(position).getDateNotify()));
        holder.descriptionNotification.setText(String.valueOf(arrayList.get(position).getDescriptionNotify()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateNotification, Notification, descriptionNotification;
        public ViewHolder(View itemView) {
            super(itemView);
            dateNotification = itemView.findViewById(R.id.tv_Date);
            Notification = itemView.findViewById(R.id.tv_Notify);
            descriptionNotification = itemView.findViewById(R.id.tv_descriptionNotify);

        }
    }

}
