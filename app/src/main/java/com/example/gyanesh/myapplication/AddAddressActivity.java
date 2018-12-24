package com.example.gyanesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class AddAddressActivity extends AppCompatActivity {

//    public Adress one = new Adress("Rakesh Pandey","8652751124","HOME","421605","Mumbai","Valaram Apartment","Room no A-1/302 ");
//    public Adress two = new Adress("Gyanesh Kumar","12345679","HOME","123456","UP","near station road","on street");
    public  static ArrayList<Adress> adressAAA = new ArrayList<Adress>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_address);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        AddAddressAdapter addAddressAdapter = new AddAddressAdapter(adressAAA);
        RecyclerView recyclerView = findViewById(R.id.recycler_address);
        recyclerView.setAdapter(addAddressAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void addressSetter(View view)
    {
        Intent intent = new Intent(this,AddressActivity.class);
        startActivity(intent);
    }
}
