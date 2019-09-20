package com.drople.Adapters;

import android.app.ProgressDialog;
import android.content.Context;

import com.drople.Fragments.ActiveOrders;
import com.drople.Fragments.CompletedOrders;
import com.drople.Models.Address;
import com.drople.Models.Order;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import static com.drople.Fragments.ActiveOrders.orderDetailAdapter;
import static com.drople.Fragments.CompletedOrders.completedOrdersAdapter;

public class OrdersTabAdaptor extends FragmentStatePagerAdapter {


//    private CloudDbHelper cloudDbHelper;
    private ProgressDialog dialog;
    private List<Order> activeOrders, completedOrders;

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Active Orders";
            case 1:
                return "Completed Orders";
        }


        return super.getPageTitle(position);
    }

    public OrdersTabAdaptor(FragmentManager fm, Context context) {
        super(fm);
        activeOrders = new ArrayList<>();
        completedOrders = new ArrayList<>();
//        cloudDbHelper = new CloudDbHelper(this);
//        cloudDbHelper.refreshOrders();
        dialog = new ProgressDialog(context);
        dialog.setTitle("Fetching Your Orders, Please Wait.....");
        dialog.show();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new ActiveOrders();
                ((ActiveOrders) fragment).setOrders(activeOrders);
                return fragment;
            case 1:
                fragment = new CompletedOrders();
                ((CompletedOrders) fragment).setOrders(completedOrders);
                return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
