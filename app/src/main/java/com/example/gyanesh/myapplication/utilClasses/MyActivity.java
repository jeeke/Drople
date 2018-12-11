package com.example.gyanesh.myapplication.utilClasses;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.gyanesh.myapplication.utilClasses.Constants.setUI;

public abstract class MyActivity extends AppCompatActivity {

    protected abstract void doTheThing();
    protected void setContent(Activity activity, int layout){
    if(setUI(activity,layout)) {
        doTheThing();
    }
    }
}
