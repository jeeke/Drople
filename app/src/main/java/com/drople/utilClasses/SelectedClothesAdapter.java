package com.drople.utilClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drople.Models.Garment;
import com.drople.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelectedClothesAdapter extends RecyclerView.Adapter<SelectedClothesAdapter.ViewHolder> {

    private ArrayList<Garment> garments;

    public SelectedClothesAdapter(ArrayList<Garment> garments) {
        this.garments = garments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cv = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_selected_cloth, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final View cardView = holder.cardView;
        Garment item = garments.get(position);
        TextView temp = cardView.findViewById(R.id.title);
//        temp.setText(item.getTitle());
        temp = cardView.findViewById(R.id.count);
//        temp.setText(String.valueOf(item.getCount()));
        temp = cardView.findViewById(R.id.price);
//        temp.setText(String.valueOf(item.getCount() * item.getPrice()));
        temp = cardView.findViewById(R.id.service_type);
        //TODO set service type
        temp.setText("W & I");
    }

    @Override
    public int getItemCount() {
        return garments.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        View cardView;

        public ViewHolder(View v) {
            super(v);
            cardView = v;
        }
    }


}
