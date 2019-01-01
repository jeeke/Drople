package com.example.gyanesh.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.Models.Garment;
import com.example.gyanesh.myapplication.utilClasses.BackgroundData;
import com.example.gyanesh.myapplication.utilClasses.SelectedClothesAdapter;

import java.util.ArrayList;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {

    private static int ADD_ADDRESS_REQUEST_CODE = 125;
    private ArrayList<Address> addresses;
    AddAddressAdapter addAddressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_address);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        BackgroundData.refreshAddresses();
        addresses = BackgroundData.addresses;
        addAddressAdapter = new AddAddressAdapter(addresses);
        RecyclerView recyclerView = findViewById(R.id.recycler_address);
        recyclerView.setAdapter(addAddressAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ADDRESS_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                addresses.add((Address) data.getSerializableExtra("address"));
                //TODO also update BackgroudData Address
                BackgroundData.updateAddresses();
                addAddressAdapter.notifyDataSetChanged();
            }
        }
    }

    public void addressSetter(View view)
    {
        Intent intent = new Intent(this,AddressActivity.class);
        startActivityForResult(intent,ADD_ADDRESS_REQUEST_CODE);
    }
}
