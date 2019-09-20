package com.drople;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ServicesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int btnId = getIntent().getExtras().getInt("btnId");
        View view = findViewById(R.id.frame_container);
        switch (btnId) {
            case R.id.btn_home3:
                //TODO update details and rate list accordingly
                break;
            case R.id.btn_home4:
                break;
            case R.id.btn_home5:
                break;
            case R.id.btn_home6:
                break;

        }
    }
}

