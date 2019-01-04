package com.example.gyanesh.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gyanesh.myapplication.Models.Garment;
import com.example.gyanesh.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.gyanesh.myapplication.utilClasses.CloudDbHelper.getGarmentsList;

public class SelectClothesAdapter extends RecyclerView.Adapter<SelectClothesAdapter.viewHolder> {

    private List<Garment> garments;

    public interface Listener {
        void updateSelected(Garment garment, int prevCount);
    }

    private Listener listener;

    public SelectClothesAdapter(Listener listener) {
        this.listener = listener;

        //TODO update this to get current locality
        this.garments = new ArrayList<>();
        garments.addAll(getGarmentsList("DR. B R AMBEDKAR NIT JALANDHAR", 0));
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
        garmentTitle.setText(item.getTitle());
        garmentPrice.setText(String.valueOf(item.getPrice()));
        holder.garmentCount.setText(String.valueOf(item.getCount()));
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
            int count = item.getCount();
            if (v.getId() == btn_plus.getId()) {
                item.setCount(count + 1);
                garmentCount.setText(String.valueOf(count + 1));
                listener.updateSelected(item, count);
            } else if (v.getId() == btn_minus.getId() && count > 0) {
                garments.get(getAdapterPosition()).setCount(count - 1);
                garmentCount.setText(String.valueOf(count - 1));
                listener.updateSelected(item, count);
            }
        }
    }
}