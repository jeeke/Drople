package com.drople.Adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.drople.AddedClothesActivity;
import com.drople.PlaceOrderActivity;
import com.drople.R;

import java.util.ArrayList;

public class AddClothListAdaptor extends RecyclerView.Adapter<AddClothListAdaptor.ViewHolder> {


    public AddClothListAdaptor(ArrayList<String> clothes, ArrayList<Integer> cost) {
        this.clothes = clothes;
        this.cost = cost;
    }

    ArrayList<String> clothes;
    ArrayList<Integer> cost;

    @Override
    public AddClothListAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.add_clothes_item, parent, false);
        AddClothListAdaptor.ViewHolder viewHolder = new AddClothListAdaptor.ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final AddClothListAdaptor.ViewHolder holder, final int position) {
            holder.clothtype.setText(clothes.get(position).toString());
            holder.price.setText(cost.get(position).toString());
            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PlaceOrderActivity.aacount++;
                    Integer temp = Integer.valueOf(holder.count.getText().toString());
                    holder.count.setText(String.valueOf(temp+1));
                    PlaceOrderActivity.aamount+=cost.get(position);
                    AddedClothesActivity.amontdisp.setText(PlaceOrderActivity.aamount+" Rs");
                }
            });
            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer temp = Integer.valueOf(holder.count.getText().toString());
                    if(temp!=0) {
                       PlaceOrderActivity.aacount--;
                        holder.count.setText(String.valueOf(temp - 1));
                        PlaceOrderActivity.aamount -= cost.get(position);
                        AddedClothesActivity.amontdisp.setText(PlaceOrderActivity.aamount+" Rs");
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return clothes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView clothtype,price,count;
        public Button minus,plus;
        public ViewHolder(View itemView) {
            super(itemView);
            this.clothtype = itemView.findViewById(R.id.garment_type);
            this.price = itemView.findViewById(R.id.garment_price);
            this.count = itemView.findViewById(R.id.garment_count);
            this.minus = itemView.findViewById(R.id.btn_minus);
            this.plus  = itemView.findViewById(R.id.btn_plus);
        }
    }


}


