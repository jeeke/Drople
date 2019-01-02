package com.example.gyanesh.myapplication.utilClasses;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import static com.example.gyanesh.myapplication.utilClasses.Constants.GOOGLE_TEZ_PACKAGE_NAME;
import static com.example.gyanesh.myapplication.utilClasses.Constants.TEZ_REQUEST_CODE;

public class TejManager {

    private Activity activity;

    public TejManager(Activity activity) {
        this.activity = activity;
    }

    public void tej() {
        Uri uri = new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", "test@axisbank")
                .appendQueryParameter("pn", "Test Merchant")
                .appendQueryParameter("mc", "1234")
                .appendQueryParameter("tr", "123456789")
                .appendQueryParameter("tn", "test transaction note")
                .appendQueryParameter("am", "1.00")
                .appendQueryParameter("cu", "INR")
                .appendQueryParameter("url", "https://test.merchant.website")
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage(GOOGLE_TEZ_PACKAGE_NAME);
        activity.startActivityForResult(intent, TEZ_REQUEST_CODE);
    }
}