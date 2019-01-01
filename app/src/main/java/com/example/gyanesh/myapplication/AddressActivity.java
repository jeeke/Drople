package com.example.gyanesh.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyanesh.myapplication.Models.Address;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.example.gyanesh.myapplication.utilClasses.BackgroundData.getCityList;

public class AddressActivity extends AppCompatActivity {

    Spinner city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbaraa);

        //Populating spinner for cities
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,getCityList());
        city = findViewById(R.id.na_spinnerCitySelector);
        city.setAdapter(adapter);

        setSupportActionBar(toolbar);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    //TODO Modify address validity

    public void add_address_button_qwerty(View view) {
        TextView name = findViewById(R.id.na_name);
        TextView number = findViewById(R.id.na_number);
        TextView pincode = findViewById(R.id.na_code);
        TextView add1 = findViewById(R.id.na_add1);
        TextView add2 = findViewById(R.id.na_add2);
        RadioGroup def = findViewById(R.id.na_def);
        int rgid = def.getCheckedRadioButtonId();
        if (name.getText().toString().matches("")) {
            Toast toast = Toast.makeText(this, "Please Enter Name ", Toast.LENGTH_SHORT);
            toast.show();
        } else if (number.getText().toString().matches("")) {
            Toast toast = Toast.makeText(this, "Please Enter Number ", Toast.LENGTH_SHORT);
            toast.show();
        } else if (pincode.getText().toString().matches("")) {
            Toast toast = Toast.makeText(this, "Please Enter pincode", Toast.LENGTH_SHORT);
            toast.show();
//        }if (city.getSelectedItem().toString().matches("-- Select City --")) {
//            Toast toast = Toast.makeText(this, "Please Select City ", Toast.LENGTH_SHORT);
//            toast.show();
        } else if (add1.getText().toString().matches("")) {
            Toast toast = Toast.makeText(this, "Please Enter Locality ", Toast.LENGTH_SHORT);
            toast.show();
        } else if (add2.getText().toString().matches("")) {
            Toast toast = Toast.makeText(this, "Please Enter Building Name ", Toast.LENGTH_SHORT);
            toast.show();
        } else if (rgid == -1) {
            Toast toast = Toast.makeText(this, "Please Select Address Type  ", Toast.LENGTH_SHORT);
            toast.show();
        } else {

            boolean addType;

            if (rgid == R.id.radioButtonHome) {
                addType = true;

            } else {
                addType = false;
            }

            Address address = new Address();
            address.setName(name.getText().toString());
            address.setMobile(number.getText().toString());
            address.setAddLine1(add1.getText().toString());
            address.setAddLine2(add2.getText().toString());
            address.setCity(city.getSelectedItem().toString());
            address.setPin(Integer.parseInt(pincode.getText().toString()));
            address.setAddType(addType);
            Intent intent = new Intent();
            intent.putExtra("address", address);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

}
