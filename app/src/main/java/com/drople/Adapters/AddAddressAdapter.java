package com.drople.Adapters;

import android.app.Activity;
import android.provider.Telephony;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.drople.Models.Address;
import com.drople.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AddAddressAdapter extends RecyclerView.Adapter<AddAddressAdapter.ViewHolder> {

    private List<Address> myadress;
    private int lastPos = -1;

    public interface Listener {
        void updatePrevSelection(int lastPos, int position);

        void deleteAddress(Address address);
    }

    private Listener listener;

    public AddAddressAdapter(Listener listener, List<Address> myadress) {
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
        final View cardView = holder.itemView;
//        AddressCardManager addressCardManager = new AddressCardManager(cardView, myadress.get(position));
//        addressCardManager.updateDetailsInCard();
        holder.setItem(myadress.get(position));
        cardView.setOnClickListener(v -> {
            listener.updatePrevSelection(lastPos, position);
            lastPos = position;
        });

    }

    public Address getSelectedAddress(int position){
        return myadress.get(position);
    }

    @Override
    public int getItemCount() {
        return (myadress == null) ? 0 : myadress.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView btn_delete;
        TextView name, number, hostel, room, type;

        public ViewHolder(CardView v) {
            super(v);
            btn_delete = v.findViewById(R.id.btn_delete);
            btn_delete.setOnClickListener(this);
            name = v.findViewById(R.id.name);
            number = v.findViewById(R.id.number);
            type = v.findViewById(R.id.type);
            hostel = v.findViewById(R.id.hostel);
            room = v.findViewById(R.id.room);
        }

        public void setItem(Address address) {
            name.setText(address.name);
            number.setText("Phone :  " + address.phone);
            type.setText(address.type);
            hostel.setText("Hostel :  " + address.hostel);
            room.setText("Room No :  " + address.room);
        }

        @Override
        public void onClick(View v) {
            listener.deleteAddress(myadress.get(getAdapterPosition()));
        }
    }
}
