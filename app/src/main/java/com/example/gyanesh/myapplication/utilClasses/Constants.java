package com.example.gyanesh.myapplication.utilClasses;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.gyanesh.myapplication.Models.Order;
import com.example.gyanesh.myapplication.R;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Constants {

    //order keys constants
    public static final String USER_ID = "userId";
    public static final String ORDER_ID = "orderId";
    public static final String ADDRESS = "address";
    public static final String STATUS = "status";
    public static final String PICKUP_TIME = "pickupTime";
    public static final String GARMENTS = "garments";
    public static final String RETURN_TIME = "returnTime";
    public static final String TOTAL_COST = "cost";
    public static final String PAY_MODE = "payMode";

    //Address keys constants
    public static final String MOBILE = "mobile";
    public static final String NAME = "name";
    public static final String ADD_DESC = "addDesc";
    public static final String ADD_TYPE = "addType";
    public static final String CITY = "city";
    public static final String LOCALITY = "locality";


    //Garment keys constants
    public static final String COUNT = "count";
    public static final String TITLE = "title";
    public static final String PRICE = "price";
    public static final String SERVICE_TYPE = "serviceType";


    //Activity result codes
    static final String GOOGLE_TEZ_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    public static final int TEZ_REQUEST_CODE = 123;
    public static final int ADD_CLOTHES_REQUEST_CODE = 124;
    public static final int SELECT_ADDRESS_REQUEST_CODE = 126;

    //Paytm Constants
    static final String M_ID = "kbYXEa11946819800480"; //Paytm Merchand Id we got it in paytm credentials
    static final String CHANNEL_ID = "WAP"; //Paytm Channel Id, got it in paytm credentials
    static final String INDUSTRY_TYPE_ID = "Retail"; //Paytm industry type got it in paytm credential
    static final String WEBSITE = "WEBSTAGING";
    static final String CALLBACK_URL = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=";




    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    static boolean setUI(Activity activity, int layout1){
        if (isNetworkAvailable(activity)) {
            activity.setContentView(layout1);
            return true;
        }else {
            activity.setContentView(R.layout.error_page);
            return false;
        }
    }

    public static String getDay(int d) {
        switch (d) {
            case 1:
                return "SUN";
            case 2:
                return "MON";
            case 3:
                return "TUE";
            case 4:
                return "WED";
            case 5:
                return "THU";
            case 6:
                return "FRI";
            case 7:
                return "SAT";
            default:
                return "ERR";
        }
    }



    static Date utilDate(int type){

        Calendar cal = Calendar.getInstance();
        switch (type){

            case 1:
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),9,0,0);
                break;
            case 2:
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),13,0,0);
                break;
            case 3:
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),17,0,0);
                break;
        }
        return cal.getTime();
    }

    static String generateString() {
        String uuid = UUID.randomUUID().toString();
        Order order = new Order();
        return uuid.replaceAll("-", "");
    }
}