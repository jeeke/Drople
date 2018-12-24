package com.example.gyanesh.myapplication.utilClasses;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.gyanesh.myapplication.R;

import java.util.Calendar;
import java.util.Date;

public class Constants {

    public static final String M_ID = "kbYXEa11946819800480"; //Paytm Merchand Id we got it in paytm credentials
    public static final String CHANNEL_ID = "WAP"; //Paytm Channel Id, got it in paytm credentials
    public static final String INDUSTRY_TYPE_ID = "Retail"; //Paytm industry type got it in paytm credential

    public static final String WEBSITE = "WEBSTAGING";
    public static final String CALLBACK_URL = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=";

    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean setUI(Activity activity, int layout1){
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



    public static Date utilDate(int type){

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
}