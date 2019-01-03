package com.example.gyanesh.myapplication.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gyanesh.myapplication.Models.Order;
import com.example.gyanesh.myapplication.R;
import com.example.gyanesh.myapplication.utilClasses.BackgroundData;

import java.util.Date;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {
    private List<Order> mOrders;

    public OrderHistoryAdapter(List<Order> orders,BackgroundData backgroundData) {
        Log.e(OrderHistoryAdapter.class.getName(),orders.size()+"");
        mOrders = orders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv =(CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_item,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cv = holder.cv;
        Order order = mOrders.get(position);
        Double cost = order.getCost();
        Date pickUp = order.getPickupTime();
        String orderId = "ID: " + order.getObjectId();
        int clothes = order.getClothes();
        Date returnDate = order.getReturnTime();
        int payMode = order.getPayMode();
        int status = order.getStatus();

        //TODO Populate CardView with above order
        String dummy = ""+orderId;
        TextView textView = cv.findViewById(R.id.orderId);
        textView.setText(dummy);
        textView = cv.findViewById(R.id.clothes);
        dummy = ""+clothes;
        textView.setText(dummy);
        textView = cv.findViewById(R.id.pickup_date);
        textView.setText(pickUp.toString());
        textView = cv.findViewById(R.id.cost);
        dummy = cost + "";
        textView.setText(dummy);
        textView = cv.findViewById(R.id.return_time);
        if(payMode==1 || status==3){
            textView.setText(returnDate.toString());
        }
    }


    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView;
        }
    }
}