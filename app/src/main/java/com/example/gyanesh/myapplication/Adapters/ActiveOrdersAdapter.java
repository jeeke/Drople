package com.example.gyanesh.myapplication.Adapters;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gyanesh.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ActiveOrdersAdapter extends RecyclerView.Adapter<ActiveOrdersAdapter.ViewHolder> {


    private String[] ordersno;
    private String[] progressno;
    private String[] date;

    public ActiveOrdersAdapter(String[] ordersno, String[] progressno,String[] date) {
        this.ordersno = ordersno;
        this.progressno = progressno;
        this.date=date;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.active_orders,parent,false);
        return new ViewHolder(cv);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CardView cardView = holder.cardView;
        TextView number =(TextView) cardView.findViewById(R.id.orderno_cv);
        TextView date_cv = (TextView) cardView.findViewById(R.id.orderdate_cv);
        number.setText(ordersno[position]);
        date_cv.setText(date[position]);
        ProgressBar progressBar = (ProgressBar) cardView.findViewById(R.id.progressBar);
        progressBar.setProgress(Integer.parseInt(progressno[position]));
        TextView in_progress = cardView.findViewById(R.id.IN_progress);
        TextView pickup = cardView.findViewById(R.id.ac_pickup);
        TextView processing = cardView.findViewById(R.id.ac_processing);
        TextView delivery = cardView.findViewById(R.id.ac_delivery);
        if(Integer.parseInt(progressno[position])==100)
        {
            in_progress.setText("COMPLETED");
            in_progress.setTextColor(Color.parseColor("#2bb657"));
            ColorStateList sl = ColorStateList.valueOf(Color.parseColor("#2bb657"));
            progressBar.setProgressTintList(sl);
            pickup.setTextColor(Color.parseColor("#2bb657"));
            processing.setTextColor(Color.parseColor("#2bb657"));
            delivery.setTextColor(Color.parseColor("#2bb657"));
        }
        else if(Integer.parseInt(progressno[position])>=75)
        {
            pickup.setTextColor(Color.parseColor("#2bb657"));
            processing.setTextColor(Color.parseColor("#2bb657"));
            delivery.setTextColor(Color.parseColor("#EF6C00"));
        }
        else if(Integer.parseInt(progressno[position])>=40)
        {
            pickup.setTextColor(Color.parseColor("#2bb657"));
            processing.setTextColor(Color.parseColor("#EF6C00"));
            //delivery.setTextColor(R.color.colorPrimary);
        }
        else
        {
            pickup.setTextColor(Color.parseColor("#EF6C00"));
           // processing.setTextColor(R.color.colorAccent);
            //delivery.setTextColor(R.color.colorPrimary);
        }
    }

    @Override
    public int getItemCount() {
        return ordersno.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        public ViewHolder(CardView c) {
            super(c);
            cardView=c;
        }
    }
}
