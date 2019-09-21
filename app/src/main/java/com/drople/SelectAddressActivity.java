package com.drople;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.drople.Adapters.AddAddressAdapter;
import com.drople.Models.Address;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelectAddressActivity extends BaseActivity implements AddAddressAdapter.Listener {

    private List<Address> addresses;
    AddAddressAdapter addAddressAdapter;
    LinearLayoutManager linearLayoutManager;
    int selectedPos = -1;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_address);
        addresses = new ArrayList<>();
        dialog = new ProgressDialog(this);
        dialog.setTitle("Please Wait, Fetching your addresses....");
        dialog.show();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Select Address");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FirebaseDatabase.getInstance().
                getReference().child("Users").child(FirebaseAuth.getInstance().
                getCurrentUser().getUid()).child("addresses").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    addresses.add(d.getValue(Address.class));
                }
                addAddressAdapter = new AddAddressAdapter(SelectAddressActivity.this, addresses);
                RecyclerView recyclerView = findViewById(R.id.recycler_address);
                linearLayoutManager = new LinearLayoutManager(SelectAddressActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(addAddressAdapter);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                dialog.dismiss();
            }
        });
        findViewById(R.id.done).setOnClickListener(v -> {
            finishActivity();
        });
    }

    public void addressSetter(View view) {
        Intent intent = new Intent(this, AddressActivity.class);
        startActivity(intent);
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

    @Override
    public void deleteAddress(Address address) {
        server.deleteAddress(FirebaseAuth.getInstance().getCurrentUser().getUid(), address);
    }


    public void finishActivity() {
        if (selectedPos == -1) {
            Toast.makeText(this, "Please select an address", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("address", addAddressAdapter.getSelectedAddress(selectedPos));
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
