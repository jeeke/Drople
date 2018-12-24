package com.example.gyanesh.myapplication;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbaraa);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    public void add_address_button_qwerty(View view)
    {
        TextView name = findViewById(R.id.na_name);
        TextView number = findViewById(R.id.na_number);
        TextView pincode = findViewById(R.id.na_code);
        Spinner city = findViewById(R.id.na_spinnerCitySelector);
        TextView add1 = findViewById(R.id.na_add1);
        TextView add2 = findViewById(R.id.na_add2);
        RadioGroup def = findViewById(R.id.na_def);
        int rgid = def.getCheckedRadioButtonId();
        if(name.getText().toString().matches(""))
        {
            Toast toast = Toast.makeText(this,"Please Enter Name ",Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(number.getText().toString().matches(""))
        {
            Toast toast = Toast.makeText(this,"Please Enter Number ",Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(pincode.getText().toString().matches(""))
        {
            Toast toast = Toast.makeText(this,"Please Enter pincode",Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(city.getSelectedItem().toString().matches("-- Select City --"))
        {
            Toast toast = Toast.makeText(this,"Please Select City ",Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(add1.getText().toString().matches(""))
        {
            Toast toast = Toast.makeText(this,"Please Enter Locality ",Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(add2.getText().toString().matches(""))
        {
            Toast toast = Toast.makeText(this,"Please Enter Building Name ",Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(rgid==-1)
        {
            Toast toast = Toast.makeText(this,"Please Select Address Type  ",Toast.LENGTH_SHORT);
            toast.show();
        }

        else {

            String s;
            if(rgid == R.id.radioButtonHome)
            {
                s = "Home";

            }
            else
            {
                s="Temporary";
            }

            AddAddressActivity.adressAAA.add(new Adress(name.getText().toString(), number.getText().toString(), s, pincode.getText().toString(), city.getSelectedItem().toString(), add1.getText().toString(), add2.getText().toString()));
            Intent intent = new Intent(this, AddAddressActivity.class);
            startActivity(intent);
        }
    }

}
