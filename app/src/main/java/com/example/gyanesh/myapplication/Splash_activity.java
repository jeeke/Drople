package com.example.gyanesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.gyanesh.myapplication.Fragments.HomeFrag;

public class Splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent intent= new Intent(getApplicationContext(),AuthActivity.class);
                startActivity(intent);
                finish();

            }
        },4000);
    }
}
