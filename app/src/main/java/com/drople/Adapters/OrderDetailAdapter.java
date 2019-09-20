package com.drople.Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.drople.Models.Order;
import com.drople.R;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {


    private List<Order> orders;

    public OrderDetailAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.active_orders, parent, false);
        return new ViewHolder(cv);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CardView cardView = holder.cardView;

        Order currentOrder = orders.get(position);
        TextView number = cardView.findViewById(R.id.orderno_cv);
        TextView date_cv = cardView.findViewById(R.id.orderdate_cv);
        ProgressBar progressBar = cardView.findViewById(R.id.progressBar);
        TextView in_progress = cardView.findViewById(R.id.IN_progress);
        TextView pickup = cardView.findViewById(R.id.ac_pickup);
        TextView processing = cardView.findViewById(R.id.ac_processing);
        TextView delivery = cardView.findViewById(R.id.ac_delivery);


        number.setText(String.valueOf(currentOrder.oid));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        date_cv.setText(dateFormat.format(currentOrder.pickup_time));

        int status = Integer.parseInt(currentOrder.status);
        if (status == 3) {
            in_progress.setText("COMPLETED");
            in_progress.setTextColor(Color.parseColor("#2bb657"));
            ColorStateList sl = ColorStateList.valueOf(Color.parseColor("#2bb657"));
            progressBar.setProgressTintList(sl);
            pickup.setTextColor(Color.parseColor("#2bb657"));
            processing.setTextColor(Color.parseColor("#2bb657"));
            delivery.setTextColor(Color.parseColor("#2bb657"));
            progressBar.setProgress(100);
        } else if (status== 2) {
            pickup.setTextColor(Color.parseColor("#2bb657"));
            processing.setTextColor(Color.parseColor("#2bb657"));
            delivery.setTextColor(Color.parseColor("#EF6C00"));
            progressBar.setProgress(75);
        } else if (status == 1) {
            pickup.setTextColor(Color.parseColor("#2bb657"));
            processing.setTextColor(Color.parseColor("#EF6C00"));
            progressBar.setProgress(50);
            //delivery.setTextColor(R.color.colorPrimary);
        } else {
            pickup.setTextColor(Color.parseColor("#EF6C00"));
            progressBar.setProgress(25);
            // processing.setTextColor(R.color.colorAccent);
            //delivery.setTextColor(R.color.colorPrimary);
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(CardView c) {
            super(c);
            cardView = c;
        }
    }
}
