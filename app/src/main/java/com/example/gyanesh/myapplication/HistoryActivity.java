package com.example.gyanesh.myapplication;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.gyanesh.myapplication.Models.OrderModel;
import com.example.gyanesh.myapplication.utilClasses.MyActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.gyanesh.myapplication.Models.Order.PICKUP_TIME_KEY;
import static com.example.gyanesh.myapplication.Models.Order.USER_ID_KEY;

public class HistoryActivity extends MyActivity {

    ArrayList<OrderModel> mOrders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent(this,R.layout.activity_history);
    }

    @Override
    protected void doTheThing() {
        refreshOrders();
    }

    public void retry(View view){
        setContent(this,R.layout.activity_history);
    }

    public void updateUi() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        OrderHistoryAdapter mAdapter = new OrderHistoryAdapter(mOrders);
        recyclerView.setAdapter(mAdapter);
        // associate the LayoutManager with the RecylcerView
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HistoryActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    void refreshOrders() {

        final ProgressDialog dlg = new ProgressDialog(HistoryActivity.this);
        dlg.setTitle("Please, wait a moment.");
        dlg.setMessage("Fetching your orders...");
        dlg.show();
        // Construct query to execute
        //TODO add donation also to history
        ParseQuery<OrderModel> query = ParseQuery.getQuery(OrderModel.class);
        // Configure limit and sort order
        query.setLimit(50);
        query.orderByDescending(PICKUP_TIME_KEY);
        // Execute query to fetch all orders from Parse asynchronously
        query.whereEqualTo(USER_ID_KEY, ParseUser.getCurrentUser().getObjectId());
        // This is equivalent to a SELECT query with SQL
        query.findInBackground(new FindCallback<OrderModel>() {
            public void done(List<OrderModel> orders, ParseException e) {
                if (e == null) {
                    mOrders.clear();
                    mOrders.addAll(orders);
                    dlg.dismiss();
                    Log.e("Size :  ", "" + mOrders.size());
                } else {
                    dlg.dismiss();
                    Log.e("message", "Error Loading Orders" + e);
                }
                updateUi();
            }
        });
    }

}