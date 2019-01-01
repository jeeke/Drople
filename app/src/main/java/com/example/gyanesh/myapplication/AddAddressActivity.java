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
import android.widget.Toast;

import com.example.gyanesh.myapplication.Adapters.AddAddressAdapter;
import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.utilClasses.BackgroundData;

import java.util.ArrayList;

public class AddAddressActivity extends AppCompatActivity implements AddAddressAdapter.Listener {

    private static int ADD_ADDRESS_REQUEST_CODE = 125;
    private ArrayList<Address> addresses;
    AddAddressAdapter addAddressAdapter;
    LinearLayoutManager linearLayoutManager;
    int selectedPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_address);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        addresses = BackgroundData.addresses;
        addAddressAdapter = new AddAddressAdapter(this, addresses);
        RecyclerView recyclerView = findViewById(R.id.recycler_address);
        recyclerView.setAdapter(addAddressAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPos == -1) {
                    Toast.makeText(getBaseContext(), "No Address Selected", Toast.LENGTH_SHORT).show();
                } else {
                    //TODO also update BackgroudData Address
                    BackgroundData.updateRemoteAddresses();
                    Intent intent = new Intent();
                    intent.putExtra("selectedAddress", selectedPos);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ADDRESS_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                addresses.add((Address) data.getParcelableExtra("address"));
                addAddressAdapter.notifyDataSetChanged();
            }
        }
    }

    public void addressSetter(View view) {
        Intent intent = new Intent(this, AddressActivity.class);
        startActivityForResult(intent, ADD_ADDRESS_REQUEST_CODE);
    }

    @Override
    public void updatePrevSelection(int lastPos, int position) {
        View view;
        if (lastPos != -1) {
            view = linearLayoutManager.findViewByPosition(lastPos);
            view.setBackground(null);
        }
        view = linearLayoutManager.findViewByPosition(position);
        view.setBackground(getDrawable(R.drawable.edit_text_round_blue));
        selectedPos = position;
    }
}
