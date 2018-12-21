package com.example.gyanesh.myapplication;

import android.app.Application;

import com.example.gyanesh.myapplication.Models.Order;
import com.example.gyanesh.myapplication.Models.OrderModel;
import com.parse.Parse;
import com.parse.ParseObject;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(OrderModel.class);
        ParseObject.registerSubclass(Order.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
    }
}
