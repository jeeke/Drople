package com.example.gyanesh.myapplication;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyanesh.myapplication.Models.Address;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AddAddressAdapter extends RecyclerView.Adapter<AddAddressAdapter.ViewHolder> {

    private ArrayList<Address> myadress;

    public AddAddressAdapter(ArrayList<Address> myadress) {
        this.myadress = myadress;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.address_layout_2,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;
        TextView name = cardView.findViewById(R.id.Name);
        TextView number = cardView.findViewById(R.id.al_number);
        TextView pincode = cardView.findViewById(R.id.al_code);
        TextView city = cardView.findViewById(R.id.al_city);
        TextView add1 = cardView.findViewById(R.id.al_address);
        TextView add2 = cardView.findViewById(R.id.al_address_2);
        TextView def = cardView.findViewById(R.id.al_default);
        name.setText(myadress.get(position).getName());
        number.setText(myadress.get(position).getMobile());
        pincode.setText(String.valueOf(myadress.get(position).getPinCode()));
        city.setText(myadress.get(position).getCity());
        add1.setText(myadress.get(position).getAddLine1());
        add2.setText(myadress.get(position).getAddLine2());
        String addType;
        if(myadress.get(position).getAddType()){
            addType = "HOME";
        }else {
            addType = "OTHERS";
        }
        def.setText(addType);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceOrderActivity.callme = position;
                Intent intent = new Intent(cardView.getContext(),PlaceOrderActivity.class);
                cardView.getContext().startActivity(intent);

            }
        });

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
