package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Evaluate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EvaluateAdapter extends RecyclerView.Adapter<EvaluateAdapter.ViewHolder> {

    private List<Evaluate> evaluateList;
    private Context mContext;

    public EvaluateAdapter(List<Evaluate> evaluateList, Context mContext) {
        this.evaluateList = evaluateList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public EvaluateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.evaluate_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvaluateAdapter.ViewHolder holder, int position) {
        Evaluate evaluate = evaluateList.get(position);
        if (!evaluate.getUser().getFullName().trim().equals("")) {
            holder.username.setText(evaluate.getUser().getFullName());
        } else {
            holder.username.setText(evaluate.getUser().getUsername());
        }
        holder.comment.setText(evaluate.getComment());
        holder.rating_star.setRating((float) evaluate.getRate());
        holder.date.setText(evaluate.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return evaluateList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private TextView comment, date;
        private RatingBar rating_star;
         public ViewHolder(@NonNull View itemView) {
             super(itemView);

             username = itemView.findViewById(R.id.username);
             comment = itemView.findViewById(R.id.comment);
             rating_star = itemView.findViewById(R.id.rating_star);
             date = itemView.findViewById(R.id.date);
         }

     }
}
