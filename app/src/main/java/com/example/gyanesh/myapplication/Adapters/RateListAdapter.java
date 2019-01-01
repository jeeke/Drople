package com.example.gyanesh.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gyanesh.myapplication.R;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RateListAdapter extends RecyclerView.Adapter<RateListAdapter.viewHolder> {

    private String[] data;
    private int cost[];

    public RateListAdapter(String[] data, int cost[]) {
        this.data = data;
        this.cost = cost;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.price_list_layout, parent, false);
        return new viewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CardView cv = holder.cv;
        String title = data[position];
        String price = "" + cost[position];
        TextView text1 = cv.findViewById(R.id.textView11);
        TextView text2 = cv.findViewById(R.id.textView24);
        text1.setText(title);
        text2.setText(price);
    }


    @Override
    public int getItemCount() {
        return data.length;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private CardView cv;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cv = (CardView) itemView;
        }
    }
}
