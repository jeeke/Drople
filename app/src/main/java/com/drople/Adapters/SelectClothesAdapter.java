package com.drople.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.drople.Models.Garment;
import com.drople.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class SelectClothesAdapter extends RecyclerView.Adapter<SelectClothesAdapter.viewHolder> {

    private List<Garment> garments;

    public interface Listener {
        void updateSelected(Garment garment, int prevCount);
    }

    private Listener listener;

    public SelectClothesAdapter(Listener listener) {
        this.listener = listener;

        this.garments = new ArrayList<>();
//        garments.addAll(getGarmentsList("DR. B R AMBEDKAR NIT JALANDHAR", 0));
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.add_clothes_item, parent, false);
        return new viewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CardView cv = holder.cv;
        Garment item = garments.get(position);
        TextView garmentTitle = cv.findViewById(R.id.garment_type);
        TextView garmentPrice = cv.findViewById(R.id.garment_price);
        garmentTitle.setText(item.title);
        garmentPrice.setText(item.price);
        holder.garmentCount.setText(item.count);
    }


    @Override
    public int getItemCount() {
        return garments.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView cv;
        protected Button btn_plus, btn_minus;
        TextView garmentCount;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cv = (CardView) itemView;
            garmentCount = itemView.findViewById(R.id.garment_count);
            btn_plus = itemView.findViewById(R.id.btn_plus);
            btn_minus = itemView.findViewById(R.id.btn_minus);
            btn_plus.setOnClickListener(this);
            btn_minus.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Garment item = garments.get(getAdapterPosition());
            int count = Integer.parseInt(item.count);
            if (v.getId() == btn_plus.getId()) {
                item.count = String.valueOf(count + 1);
                garmentCount.setText(String.valueOf(count + 1));
                listener.updateSelected(item, count);
            } else if (v.getId() == btn_minus.getId() && count > 0) {
                garments.get(getAdapterPosition()).count = String.valueOf(count - 1);
                garmentCount.setText(String.valueOf(count - 1));
                listener.updateSelected(item, count);
            }
        }
    }
}