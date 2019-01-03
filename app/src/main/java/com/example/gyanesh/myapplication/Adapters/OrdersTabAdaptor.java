package com.example.gyanesh.myapplication.Adapters;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.gyanesh.myapplication.Fragments.ActiveOrders;
import com.example.gyanesh.myapplication.Fragments.CompletedOrders;
import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.Models.Order;
import com.example.gyanesh.myapplication.utilClasses.CloudDbHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import static com.example.gyanesh.myapplication.Fragments.ActiveOrders.orderDetailAdapter;
import static com.example.gyanesh.myapplication.Fragments.CompletedOrders.completedOrdersAdapter;

public class OrdersTabAdaptor extends FragmentStatePagerAdapter implements CloudDbHelper.Listener {


    private CloudDbHelper cloudDbHelper;
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
        cloudDbHelper = new CloudDbHelper(this);
        cloudDbHelper.refreshOrders();
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

    @Override
    public void onAddressFetch(List<Address> addressList) {

    }

    @Override
    public void onAddressUpdate(Boolean error) {

    }

    @Override
    public void onOrdersFetch(Boolean error) {
        if (error) {
            dialog.dismiss();

            activeOrders.clear();
            activeOrders.addAll(cloudDbHelper.getActiveOrders());

            completedOrders.clear();
            completedOrders.addAll(cloudDbHelper.getCompletedOrders());

            orderDetailAdapter.notifyDataSetChanged();
            completedOrdersAdapter.notifyDataSetChanged();
        } else {
            //TODO Something error happened
        }

    }
}
