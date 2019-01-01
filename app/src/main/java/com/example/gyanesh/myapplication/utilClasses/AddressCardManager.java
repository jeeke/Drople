package com.example.gyanesh.myapplication.utilClasses;

import android.view.View;
import android.widget.TextView;

import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.R;

import static com.example.gyanesh.myapplication.utilClasses.BackgroundData.addresses;

public class AddressCardManager {

    private  View view;
    public AddressCardManager(View view){
        this.view = view;
    }

    public void updateDetailsInCard(int position){

        TextView Name = view.findViewById(R.id.Name);
        TextView number = view.findViewById(R.id.al_number);
        TextView al_default = view.findViewById(R.id.al_default);
        TextView al_Add1 = view.findViewById(R.id.al_address);
        TextView al_Add2 = view.findViewById(R.id.al_address_2);
        TextView Al_city = view.findViewById(R.id.al_city);
        TextView Al_code = view.findViewById(R.id.al_code);

        Address address = addresses.get(position);

        Name.setText(address.getName());
        number.setText(address.getMobile());
        al_Add1.setText(address.getAddLine1());
        al_Add2.setText(address.getAddLine2());
        Al_code.setText(String.valueOf(address.getPin()));
        Al_city.setText(address.getCity());
        String df;
        if (address.getAddType()) {
            df = "HOME";
        } else {
            df = "OTHERS";
        }
        al_default.setText(df);
    }
}
