package com.drople;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.drople.Models.Address;
import com.google.firebase.auth.FirebaseAuth;

import static com.drople.Server.SERVER_SAVE_ADDRESS;

public class AddressActivity extends BaseActivity {

    Spinner mHostel;
    EditText mName, mNumber, mRoom;
    RadioGroup mDef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_address);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbaraa);

        mName = findViewById(R.id.na_name);
        mNumber = findViewById(R.id.na_number);
        mRoom = findViewById(R.id.na_desc);
        mDef = findViewById(R.id.na_def);
        mHostel = findViewById(R.id.spinner);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    //TODO Modify address validity

    public void saveAddress(View view) {
        String name = mName.getText().toString();
        String number = mNumber.getText().toString();
        String room = mRoom.getText().toString();
        String hostel = mHostel.getSelectedItem().toString();
        int rgid = mDef.getCheckedRadioButtonId();
        if (name.equals("")) {
            showSnackBar(this, "Please Enter Name ");
        } else if (number.equals("")) {
            showSnackBar(this, "Please Enter Number ");
        } else if (room.equals("")) {
            showSnackBar(this, "Please Enter Room No");
        } else if (hostel.equals("Select Hostel")) {
            showSnackBar(this, "Please Select Hostel");
        } else if (rgid == -1) {
            showSnackBar(this, "Please Select Address Type  ");
        } else {
            Address address = new Address();
            address.hostel = hostel;
            address.name = name;
            address.type = rgid==R.id.radioButtonHome?"Home":"Temporary";
            address.phone = number;
            address.room = room;
            server.saveAddress(FirebaseAuth.getInstance().getCurrentUser().getUid(),address);
        }
    }

    @Override
    public void onServerCallSuccess(int methodId, String title) {
        super.onServerCallSuccess(methodId, title);
        if(methodId==SERVER_SAVE_ADDRESS){
            finish();
        }
    }
}
