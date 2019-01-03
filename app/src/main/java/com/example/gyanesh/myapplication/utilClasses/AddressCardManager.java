package com.example.gyanesh.myapplication.utilClasses;

import android.view.View;
import android.widget.TextView;

import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.R;

public class AddressCardManager {

    private Address address;

    private  View view;
    public AddressCardManager(View view,Address address){
        this.view = view;
        this.address = address;
    }
    public void updateDetailsInCard(){

        TextView Name = view.findViewById(R.id.Name);
        TextView number = view.findViewById(R.id.al_number);
        TextView al_default = view.findViewById(R.id.al_default);
        TextView al_Add1 = view.findViewById(R.id.al_address);
        TextView al_Add2 = view.findViewById(R.id.al_address_2);
        TextView Al_city = view.findViewById(R.id.al_city);
        TextView Al_code = view.findViewById(R.id.al_code);

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
