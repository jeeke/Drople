package com.example.gyanesh.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gyanesh.myapplication.Models.Address;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.example.gyanesh.myapplication.utilClasses.CloudDbHelper.cities;
import static com.example.gyanesh.myapplication.utilClasses.CloudDbHelper.detailsUpdated;

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
        toolbar.setTitle("Address");
        //city = findViewById(R.id.na_spinnerCitySelector);
        locality = findViewById(R.id.na_spinnerLocalitySelector);

        //Populating spinner for cities
        if(detailsUpdated){
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,cities);
            city.setAdapter(adapter);
//            adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,);
//            Todo update locality spinner
            locality.setAdapter(adapter);
        }
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
            boolean addType;

            addType = rgid == R.id.radioButtonHome;

            Address address = new Address();
            address.setName(name.getText().toString());
            address.setMobile(number.getText().toString());
            address.setAddDesc(add1.getText().toString());
            address.setCity(city.getSelectedItem().toString());
            address.setLocality(locality.getSelectedItem().toString());
            address.setAddType(addType);
            Intent intent = new Intent();
            intent.putExtra("address", address);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
