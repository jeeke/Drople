package com.example.gyanesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseUser;

public class FrontActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this,MainActivity.class));
        }
        else {
            // show the signup or login screen
            setContentView(R.layout.activity_front);
        }
    }

    public void regEmail(View v){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
}
