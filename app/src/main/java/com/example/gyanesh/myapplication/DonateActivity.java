package com.example.gyanesh.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.gyanesh.myapplication.Fragments.Date_Picker;
import com.example.gyanesh.myapplication.Fragments.Slot_Picker;

public class DonateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        View v = findViewById(R.id.spinner2);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDial(2);
            }
        });

        v = findViewById(R.id.spinner3);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDial(3);
            }
        });
        ImageView imageView = findViewById(R.id.edit_address_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonateActivity.this,AddressActivity.class);
                startActivity(intent);
            }
        });
    }
    public void showDial(int dialogId) {
        FragmentManager fm = getSupportFragmentManager();
        if(dialogId==2){
            Date_Picker date_picker;
            date_picker =  new Date_Picker();
            date_picker.show(fm, "fragment_edit_name");
        }else {
            Slot_Picker slot_picker;
            slot_picker = new Slot_Picker();
            slot_picker.show(fm, "fragment_edit_name");
        }
    }
}
