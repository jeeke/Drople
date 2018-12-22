package com.example.gyanesh.myapplication;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
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
        Button doneb = (Button) findViewById(R.id.button2);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbaraa);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                PlaceOrderActivity.callme =1;
                EditText name = (EditText) findViewById(R.id.Name_edit_address);
                EditText number = (EditText) findViewById(R.id.MobileNo_edit_address);
                EditText Address1 = (EditText) findViewById(R.id.HostelNo_edit_address);
                EditText Address2 = (EditText) findViewById(R.id.RoomNo_edit_address);
                EditText Address3 = (EditText) findViewById(R.id.Pincode_edit_address);
                Spinner address = (Spinner) findViewById(R.id.spinnerCitySelector);
                if(name.getText().toString().matches(""))
                {
                    Toast toast = Toast.makeText(AddressActivity.this,"Please Fill Name " ,Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(number.getText().toString().matches(""))
                {
                    Toast toast = Toast.makeText(AddressActivity.this,"Please Fill Number " ,Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(Address1.getText().toString().matches(""))
                {
                    Toast toast = Toast.makeText(AddressActivity.this,"Please Fill Address " ,Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(Address2.getText().toString().matches(""))
                {
                    Toast toast = Toast.makeText(AddressActivity.this,"Please Fill address " ,Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(Address3.getText().toString().matches(""))
                {
                    Toast toast = Toast.makeText(AddressActivity.this,"Please Fill Pincode " ,Toast.LENGTH_SHORT);
                    toast.show();
                }

                else {
                    CheckBox box = (CheckBox) findViewById(R.id.checkBox_saver);
                    Intent intent = new Intent(AddressActivity.this, PlaceOrderActivity.class);
                    intent.putExtra("name", String.valueOf(name.getText()));
                    intent.putExtra("number", String.valueOf(number.getText()));
                    intent.putExtra("address1", String.valueOf(Address1.getText()));
                    intent.putExtra("address2", String.valueOf(Address2.getText()));
                    intent.putExtra("city", String.valueOf(address.getSelectedItem()));
                    intent.putExtra("pincode", String.valueOf(Address3.getText()));
                    if(box.isChecked())
                    {
                        intent.putExtra("checkbox","hello");
                    }
                    startActivity(intent);
                }

            }
        };
        doneb.setOnClickListener(onClickListener);

    }

}
