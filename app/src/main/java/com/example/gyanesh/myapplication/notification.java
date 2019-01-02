package com.example.gyanesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gyanesh.myapplication.Adapters.notificationRecyclerAdapter;
import com.example.gyanesh.myapplication.Models.notification_data;

import java.util.ArrayList;

public class notification extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<notification_data> list;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        toolbar = findViewById(R.id.notification_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notifications");

        recyclerView = findViewById(R.id.notifiaction_recycler);
        //Demi data
        notification_data data1 = new notification_data("Harpreet",R.drawable.account,"18:34");
        notification_data data2 = new notification_data("Parbhat",R.drawable.btn_donate,"14:29");
        notification_data data3 = new notification_data("This message is written for checking purpose and I wish a Happy New Year plz enjoy and a lazy fox jump over the blue cow",R.drawable.btn_donate,"17:55");
        list = new ArrayList<>();
        list.add(data1);
        list.add(data2);
        list.add(data3);

        final notificationRecyclerAdapter adapter = new notificationRecyclerAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
