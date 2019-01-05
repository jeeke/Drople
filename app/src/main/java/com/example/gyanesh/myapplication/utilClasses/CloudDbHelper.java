package com.example.gyanesh.myapplication.utilClasses;

import android.util.Log;

import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.Models.City;
import com.example.gyanesh.myapplication.Models.Garment;
import com.example.gyanesh.myapplication.Models.Locality;
import com.example.gyanesh.myapplication.Models.Order;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.gyanesh.myapplication.utilClasses.Constants.COUNT;
import static com.example.gyanesh.myapplication.utilClasses.Constants.LOCALITY;
import static com.example.gyanesh.myapplication.utilClasses.Constants.SERVICE_TYPE;
import static com.example.gyanesh.myapplication.utilClasses.Constants.USER_ID;

public class CloudDbHelper {

    public interface Listener {
        void onAddressFetch(List<Address> addressList);

        void onAddressUpdate(Boolean error);

        void onOrdersFetch(Boolean error);
    }

    public static ArrayList<Garment> garment0;
    public static ArrayList<Garment> garment1;
    public static ArrayList<Garment> garment2;
    public static ArrayList<Garment> garment3;
    public static ArrayList<Locality> localities;
    public static ArrayList<String> cities;
    public static boolean detailsUpdated = false;


    private Listener listener;
    private ArrayList<Order> activeOrders;
    private ArrayList<Order> completedOrders;

    public CloudDbHelper(Listener listener) {
        this.listener = listener;
        activeOrders = new ArrayList<>();
        completedOrders = new ArrayList<>();
    }


    //Helper Fucntions related to orders
    public void refreshOrders() {
        ParseQuery<Order> query = ParseQuery.getQuery(Order.class);
        query.setLimit(20);
        query.findInBackground(new FindCallback<Order>() {
            public void done(List<Order> mOrders, ParseException e) {
                if (e == null) {
                    processOrders(mOrders);
                    listener.onOrdersFetch(true);
                } else {
                    Log.e("Error", e.toString());
                    listener.onOrdersFetch(false);
                }
            }
        });
    }

    private void processOrders(List<Order> mOrders) {
        for (Order x : mOrders) {
            if (x.getStatus() == 3) {
                completedOrders.add(x);
            } else {
                activeOrders.add(x);
            }
        }
    }

    public List<Order> getActiveOrders() {
        return activeOrders;
    }

    public List<Order> getCompletedOrders() {
        return completedOrders;
    }


    //Helper functions related to address
    public void getCloudAddressList() {
        ParseQuery<Address> query = ParseQuery.getQuery(Address.class);
        // Configure limit and sort order
        query.setLimit(10);
        // Execute query to fetch all genericOrders from Parse asynchronously
        query.whereEqualTo(USER_ID, ParseUser.getCurrentUser().getObjectId());
        // This is equivalent to a SELECT query with SQL
        query.findInBackground(new FindCallback<Address>() {
            public void done(List<Address> mAddresses, ParseException e) {
                if (e == null) {
                    listener.onAddressFetch(mAddresses);
                } else {
                    listener.onAddressFetch(null);
                }
            }
        });
    }

    public void updateCloudAddressList(List<Address> addresses) {
        for (Address address : addresses) {
            address.setUserId(ParseUser.getCurrentUser().getObjectId());
            address.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        listener.onAddressUpdate(true);
                    } else {
                        listener.onAddressUpdate(false);
                    }
                }
            });
        }
    }


    public ArrayList<String> getLocalitiesForCity(String city) {
        ArrayList<String> list = new ArrayList<>();
        for (Locality x : localities) {
            if (x.getCity().equals(city)) {
                list.add(x.getObjectId());
            }
        }
        return list;
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


    public static void updateDetails() {
        getCitiesList();
        getLocalitiesList();
        getGarmentsList();
    }

    //Helper functions for cities and localities
    private static void getCitiesList() {
        ParseQuery<City> query = ParseQuery.getQuery(City.class);
        query.findInBackground(new FindCallback<City>() {
            public void done(List<City> mCities, ParseException e) {
                if (e == null) {
                    cities = new ArrayList<>();
                    for (City x : mCities) {
                        cities.add(x.getObjectId());
                    }
                    detailsUpdated = true;
                } else {
                    detailsUpdated = false;
                }
            }
        });
    }

    private static void getLocalitiesList() {
        ParseQuery<Locality> query = ParseQuery.getQuery(Locality.class);
        query.findInBackground(new FindCallback<Locality>() {
            public void done(List<Locality> mLocalities, ParseException e) {
                if (e == null) {
                    localities = new ArrayList<>();
                    localities.addAll(mLocalities);
                    detailsUpdated = true;
                } else {
                    detailsUpdated = false;
                }
            }
        });
    }


    public static void getGarmentsList() {
        ParseQuery<Garment> query = ParseQuery.getQuery(Garment.class);
        query.findInBackground(new FindCallback<Garment>() {
            public void done(List<Garment> mGarments, ParseException e) {
                if (e == null) {
                    processGarments(mGarments);
                    detailsUpdated = true;
                } else {
                    detailsUpdated = false;
                }
            }
        });
    }


    private static void processGarments(List<Garment> mGarments) {
        garment0 = new ArrayList<>();
        garment1 = new ArrayList<>();
        garment2 = new ArrayList<>();
        garment3 = new ArrayList<>();
        for (Garment x : mGarments) {
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

    static HashMap<String, Object> garmentToHashMap(ArrayList<Garment> garments) {
        HashMap<String, Object> hashMap = new HashMap<>();
        ArrayList<String> objectIds = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();
        ArrayList<String> localities = new ArrayList<>();
        ArrayList<Integer> serviceType = new ArrayList<>();
        int i = 0;
        for (Garment x : garments) {
            objectIds.add(i, x.getObjectId());
            counts.add(i, x.getCount());
            localities.add(i, x.getLocality());
            serviceType.add(i, x.getServiceType());
        }
        hashMap.put(COUNT, counts);
        hashMap.put(LOCALITY, localities);
        hashMap.put(SERVICE_TYPE, serviceType);
        hashMap.put("objectId", objectIds);
        return hashMap;
    }

}
