package com.drople.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.drople.Models.Address;
import com.drople.R;
import com.drople.utilClasses.AddressCardManager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AddAddressAdapter extends RecyclerView.Adapter<AddAddressAdapter.ViewHolder> {

    private List<Address> myadress;
    private int lastPos = -1;
    public interface Listener{
        void updatePrevSelection(int lastPos,int position);
    }
    private Listener listener;

    public AddAddressAdapter(Listener listener,List<Address> myadress) {
        this.listener = listener;
        this.myadress = myadress;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.address_layout, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;
        AddressCardManager addressCardManager = new AddressCardManager(cardView,myadress.get(position));
        addressCardManager.updateDetailsInCard();
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