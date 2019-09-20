package com.drople;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddressActivity extends AppCompatActivity{

    Spinner city;
    Spinner locality;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SelectAddressActivity.toUpdate = true;
        setContentView(R.layout.activity_address);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbaraa);
        city = findViewById(R.id.na_spinnerCitySelector);
        locality = findViewById(R.id.na_spinnerLocalitySelector);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    //TODO Modify address validity

    public void add_address_button_qwerty(View view) {
        EditText name = findViewById(R.id.na_name);
        EditText number = findViewById(R.id.na_number);
        EditText add1 = findViewById(R.id.na_desc);
        RadioGroup def = findViewById(R.id.na_def);


        int rgid = def.getCheckedRadioButtonId();
        if (name.getText().toString().matches("")) {
            Toast toast = Toast.makeText(this, "Please Enter Name ", Toast.LENGTH_SHORT);
            toast.show();
        } else if (number.getText().toString().matches("")) {
            Toast toast = Toast.makeText(this, "Please Enter Number ", Toast.LENGTH_SHORT);
            toast.show();
        } else if (add1.getText().toString().matches("")) {
            Toast toast = Toast.makeText(this, "Please Enter Address Description", Toast.LENGTH_SHORT);
            toast.show();
        } else if (rgid == -1) {
            Toast toast = Toast.makeText(this, "Please Select Address Type  ", Toast.LENGTH_SHORT);
            toast.show();
        } else {
        }
    }
}
