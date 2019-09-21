package com.drople.Adapters;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.drople.Fragments.ActiveOrders;
import com.drople.Fragments.CompletedOrders;
import com.drople.Models.Order;

public class OrdersTabAdaptor extends FragmentStatePagerAdapter {
    private List<Order> mActiveOrders, mCompletedOrders;

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

    public OrdersTabAdaptor(FragmentManager fm,List<Order> activeOrders,List<Order> completedOrders) {
        super(fm);
        mActiveOrders = activeOrders;
        mCompletedOrders = completedOrders;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ActiveOrders();
                ((ActiveOrders) fragment).setOrders(mActiveOrders);
                return fragment;
            case 1:
                fragment = new CompletedOrders();
                ((CompletedOrders) fragment).setOrders(mCompletedOrders);
                return fragment;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
