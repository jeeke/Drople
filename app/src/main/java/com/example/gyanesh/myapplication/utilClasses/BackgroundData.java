package com.example.gyanesh.myapplication.utilClasses;

import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.Models.Garment;
import com.example.gyanesh.myapplication.Models.Order;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;
import static com.example.gyanesh.myapplication.Models.Order.USER_ID_KEY;

public class BackgroundData {
    public static ArrayList<Address> addresses = new ArrayList<>();
    public static ArrayList<Order> orders;
    public static ArrayList<Garment> garments;
    public static boolean prevSendSuccess;
    public static boolean prevFetchSuccess;

    private BackgroundData() {

    }

    public static void getRemoteAddresses() {
        ParseQuery<Address> query = ParseQuery.getQuery(Address.class);
        // Configure limit and sort order
        query.setLimit(10);
        // Execute query to fetch all orders from Parse asynchronously
        query.whereEqualTo(USER_ID_KEY, ParseUser.getCurrentUser().getObjectId());
        // This is equivalent to a SELECT query with SQL
        query.findInBackground(new FindCallback<Address>() {
            public void done(List<Address> orders, ParseException e) {
                if (e == null) {
                    addresses.clear();
                    addresses.addAll(orders);
                    prevFetchSuccess = true;
                } else {
                    prevFetchSuccess = false;
                }
            }
        });
    }

    public static void refreshGarments() {
    }

    public static void updateRemoteAddresses() {
        for (Address address : addresses) {
            address.setId(ParseUser.getCurrentUser().getObjectId());
            address.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        prevSendSuccess = true;
                    } else {
                        prevSendSuccess = false;
                    }
                }
            });
        }
    }

}
