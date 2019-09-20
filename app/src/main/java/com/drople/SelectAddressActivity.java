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

public class SelectAddressActivity extends AppCompatActivity implements AddAddressAdapter.Listener {

    private List<Address> addresses;
    AddAddressAdapter addAddressAdapter;
    LinearLayoutManager linearLayoutManager;
    int selectedPos = -1;
    ProgressDialog dialog;
    static boolean toUpdate = false;

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
                getReference().child(FirebaseAuth.getInstance().
                getCurrentUser().getUid()).child("addresses").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d: dataSnapshot.getChildren()){
                    addresses.add(d.getValue(Address.class));
                }
                addAddressAdapter = new AddAddressAdapter(SelectAddressActivity.this,addresses);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                dialog.dismiss();
            }
        });
        //Get Address List from server
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == ADD_ADDRESS_REQUEST_CODE) {
//            if (resultCode == Activity.RESULT_OK) {
//                addresses.add((Address) data.getParcelableExtra("address"));
//                addAddressAdapter.notifyDataSetChanged();
//            }
//        }
//    }

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


    public void finishActivity() {
        //Todo address update successful
        toUpdate = false;
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
