package com.example.gyanesh.myapplication;


import android.app.ActionBar;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TrackOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_order_using_adapter);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar_track_order);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String[] date={"16-01-2000","25-01-2000","31-31-2031","5-09-2000","14-04-2019"};
        String [] progress={"25","30","100","90","60"};
        String [] orderid={"#0001","#0002","#0003","#0004","#0005"};
        ActiveOrdersAdapter activeOrdersAdapter = new ActiveOrdersAdapter(orderid,progress,date);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.track_order_recycler);
        recyclerView.setAdapter(activeOrdersAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
