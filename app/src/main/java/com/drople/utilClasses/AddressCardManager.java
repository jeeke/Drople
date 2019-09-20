package com.drople.utilClasses;

import android.view.View;
import android.widget.TextView;

import com.drople.Models.Address;
import com.drople.R;

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
        TextView al_Add1 = view.findViewById(R.id.al_address_desc);
        TextView Al_city = view.findViewById(R.id.al_city);
        TextView Al_code = view.findViewById(R.id.al_locality);
    }
}
