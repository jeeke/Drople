package com.example.gyanesh.myapplication.Adapters;

import com.example.gyanesh.myapplication.Fragments.ActiveOrders;
import com.example.gyanesh.myapplication.Fragments.HistoryHistory;
import com.example.gyanesh.myapplication.Models.Order;
import com.example.gyanesh.myapplication.utilClasses.BackgroundData;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class OrdersAdaptor extends FragmentPagerAdapter {


    List<Order> activeOrders, completedOrders;

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

    public OrdersAdaptor(FragmentManager fm, List<Order> activeOrders, List<Order> completedOrders) {
        super(fm);
        this.activeOrders = activeOrders;
        this.completedOrders = completedOrders;
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
                fragment = new HistoryHistory();
                ((HistoryHistory) fragment).setOrders(activeOrders);
                return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }


}
