package com.example.gyanesh.myapplication;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AddAddressAdapter extends RecyclerView.Adapter<AddAddressAdapter.ViewHolder> {

    private ArrayList<Adress> myadress;

    public AddAddressAdapter(ArrayList<Adress> myadress) {
        this.myadress = myadress;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.address_layout_2,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView name = cardView.findViewById(R.id.Name);
        TextView number = cardView.findViewById(R.id.al_number);
        TextView pincode = cardView.findViewById(R.id.al_code);
        TextView city = cardView.findViewById(R.id.al_city);
        TextView add1 = cardView.findViewById(R.id.al_address);
        TextView add2 = cardView.findViewById(R.id.al_address_2);
        TextView def = cardView.findViewById(R.id.al_default);
        name.setText(myadress.get(position).name);
        number.setText(myadress.get(position).number);
        pincode.setText(myadress.get(position).pincode);
        city.setText(myadress.get(position).city);
        add1.setText(myadress.get(position).add1);
        add2.setText(myadress.get(position).add2);
        def.setText(myadress.get(position).def_value);

    }

    @Override
    public int getItemCount() {
        return (myadress == null) ? 0 : myadress.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }


}
