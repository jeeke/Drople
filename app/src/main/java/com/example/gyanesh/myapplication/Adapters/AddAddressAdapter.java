package com.example.gyanesh.myapplication.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.R;
import com.example.gyanesh.myapplication.utilClasses.AddressCardManager;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AddAddressAdapter extends RecyclerView.Adapter<AddAddressAdapter.ViewHolder> {

    private ArrayList<Address> myadress;
    private int lastPos = -1;
    interface Listener{
        void updatePrevSelection(int lastPos,int position);
    }
    private Listener listener;

    public AddAddressAdapter(Listener listener,ArrayList<Address> myadress) {
        this.listener = listener;
        this.myadress = myadress;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.address_layout_2, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;
        AddressCardManager addressCardManager = new AddressCardManager(cardView);
        addressCardManager.updateDetailsInCard(position);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.updatePrevSelection(lastPos, position);
                lastPos = position;
            }
        });

    }

    @Override
    public int getItemCount() {
        return (myadress == null) ? 0 : myadress.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        ImageView btn_edit,btn_delete;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
            btn_edit = v.findViewById(R.id.btn_edit);
            btn_delete = v.findViewById(R.id.btn_delete);
            btn_edit.setOnClickListener(this);
            btn_delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if(v.getId()==R.id.btn_edit){
                Toast.makeText((Activity)listener,"Edit Clicked",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText((Activity)listener,"Delete Clicked",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
