package com.example.gyanesh.myapplication.Adapters;

import com.example.gyanesh.myapplication.Fragments.ActiveOrders;
import com.example.gyanesh.myapplication.Fragments.HistoryHistory;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class OrdersAdaptor extends FragmentPagerAdapter {
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Active Orders";
            case 1:
                return "History";
        }


        return super.getPageTitle(position);
    }

    public OrdersAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new ActiveOrders();
            case 1:
                return new HistoryHistory();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }


}
