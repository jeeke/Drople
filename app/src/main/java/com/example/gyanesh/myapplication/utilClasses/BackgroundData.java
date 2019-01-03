package com.example.gyanesh.myapplication.utilClasses;

import android.util.Log;

import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.Models.City;
import com.example.gyanesh.myapplication.Models.Garment;
import com.example.gyanesh.myapplication.Models.GenericOrder;
import com.example.gyanesh.myapplication.Models.Locality;
import com.example.gyanesh.myapplication.Models.Order;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import static com.example.gyanesh.myapplication.Models.GenericOrder.USER_ID_KEY;

public class BackgroundData {
    public static ArrayList<Address> addresses = new ArrayList<>();
    private static ArrayList<Order> activeOrders = new ArrayList<>();
    private static ArrayList<Order> completedOrders = new ArrayList<>();
    private static ArrayList<Garment> garments = new ArrayList<>();
    private static ArrayList<Garment> garment0 = new ArrayList<>();
    private static ArrayList<Garment> garment1 = new ArrayList<>();
    private static ArrayList<Garment> garment2 = new ArrayList<>();
    private static ArrayList<Garment> garment3 = new ArrayList<>();
    public static ArrayList<City> cities = new ArrayList<>();
    public static ArrayList<Locality> localities = new ArrayList<>();

    public static boolean prevSendSuccess;
    public static boolean prevFetchSuccess;

    private BackgroundData() {
    }

    public static void refreshOrders() {
        ParseQuery<Order> query = ParseQuery.getQuery(Order.class);
        query.findInBackground(new FindCallback<Order>() {
            public void done(List<Order> mOrders, ParseException e) {
                if (e == null) {
                    for(Order x:mOrders){
                        if(x.getStatus()==3){
                            completedOrders.add(x);
                        }else{
                            activeOrders.add(x);
                        }
                    }
                    Log.e("Remote Orders: ",mOrders.toString());
                    Log.e("Remote Orders: ",activeOrders.toString());
                    prevFetchSuccess = true;
                } else {
                    Log.e("Error",e.toString());
                    prevFetchSuccess = false;
                }
            }
        });
    }

    public static List<Order> getActiveOrders(){
        return activeOrders;
    }

    public static List<Order> getCompletedOrders(){
        return completedOrders;
    }

    public static void refreshCities() {
        ParseQuery<City> query = ParseQuery.getQuery(City.class);
        query.findInBackground(new FindCallback<City>() {
            public void done(List<City> mCities, ParseException e) {
                if (e == null) {
                    cities.clear();
                    cities.addAll(mCities);
                    prevFetchSuccess = true;
                } else {
                    prevFetchSuccess = false;
                }
            }
        });
    }

    public static void refreshLocalities() {
        ParseQuery<Locality> query = ParseQuery.getQuery(Locality.class);
        query.findInBackground(new FindCallback<Locality>() {
            public void done(List<Locality> mLocalities, ParseException e) {
                if (e == null) {
                    localities.clear();
                    localities.addAll(mLocalities);
                    prevFetchSuccess = true;
                } else {
                    prevFetchSuccess = false;
                }
            }
        });
    }

    public static ArrayList<String> getCityList() {
        ArrayList<String> list = new ArrayList<>();
        for (City x : cities) {
            list.add(x.getTitle());
        }
        return list;
    }

    public static ArrayList<String> getLocalitiesForCity(String city) {
        ArrayList<String> list = new ArrayList<>();
        for (Locality x : localities) {
            if (x.getCity() == city) {
                list.add(x.getTitle());
            }
        }
        return list;
    }


    public static void refreshGarments() {
        ParseQuery<Garment> query = ParseQuery.getQuery(Garment.class);
        query.findInBackground(new FindCallback<Garment>() {
            public void done(List<Garment> mGarments, ParseException e) {
                if (e == null) {
                    garments.clear();
                    garments.addAll(mGarments);
                    prevFetchSuccess = true;
                } else {
                    prevFetchSuccess = false;
                }
            }
        });
    }

    private static void processGarments() {
        for (Garment x : garments) {
            int serviceType = x.getServiceType();
            if (serviceType == 0) {
                garment0.add(x);
            } else if (serviceType == 1) {
                garment1.add(x);
            } else if (serviceType == 2) {
                garment2.add(x);
            } else {
                garment3.add(x);
            }
        }
    }

    public static List<Garment> getGarmentsList(String locality, int serviceType) {
        List<Garment> list = new ArrayList<>();
        switch (serviceType) {
            case 0:
                for (Garment x : garment0) {
                    if (x.getLocality().equals(locality)) {
                        list.add(x);
                    }
                }
                break;

            case 1:
                for (Garment x : garment1) {
                    if (x.getLocality().equals(locality)) {
                        list.add(x);
                    }
                }
                break;

            case 2:
                for (Garment x : garment2) {
                    if (x.getLocality().equals(locality)) {
                        list.add(x);
                    }
                }
                break;
            case 3:
                for (Garment x : garment3) {
                    if (x.getLocality().equals(locality)) {
                        list.add(x);
                    }
                }
                break;
        }
        return list;
    }


    public static void getRemoteAddresses() {
        ParseQuery<Address> query = ParseQuery.getQuery(Address.class);
        // Configure limit and sort order
        query.setLimit(10);
        // Execute query to fetch all genericOrders from Parse asynchronously
        query.whereEqualTo(USER_ID_KEY, ParseUser.getCurrentUser().getObjectId());
        // This is equivalent to a SELECT query with SQL
        query.findInBackground(new FindCallback<Address>() {
            public void done(List<Address> mAddresses, ParseException e) {
                if (e == null) {
                    addresses.clear();
                    addresses.addAll(mAddresses);
                    prevFetchSuccess = true;
                } else {
                    prevFetchSuccess = false;
                }
            }
        });
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
