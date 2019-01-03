package com.example.gyanesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gyanesh.myapplication.Adapters.notificationRecyclerAdapter;
import com.example.gyanesh.myapplication.Models.NotificationData;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<NotificationData> list;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.notifiaction_recycler);
        //Demi data
        NotificationData data1 = new NotificationData("Harpreet",R.drawable.account,"18:34");
        NotificationData data2 = new NotificationData("Parbhat",R.drawable.btn_donate,"14:29");
        NotificationData data3 = new NotificationData("This message is written for checking purpose and I wish a Happy New Year plz enjoy and a lazy fox jump over the blue cow",R.drawable.btn_donate,"17:55");
        list = new ArrayList<>();
        list.add(data1);
        list.add(data2);
        list.add(data3);

        final notificationRecyclerAdapter adapter = new notificationRecyclerAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
