package com.example.gyanesh.myapplication.Adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.gyanesh.myapplication.Fragments.VendorFragmentPending;
import com.example.gyanesh.myapplication.Fragments.VendorFragmentComp;
import com.example.gyanesh.myapplication.Fragments.VendorFragmentProgress;


public class VendorAdaptor extends FragmentPagerAdapter {

    private Fragment[] childFragments;
    private String[] titles = {"Pending", "In Progress", "Completed"};

    public VendorAdaptor(FragmentManager fm) {
        super(fm);
        childFragments = new Fragment[]{
                new VendorFragmentPending(), //0
                new VendorFragmentProgress(), //1
                new VendorFragmentComp(), //2
        };
    }

    @Override
    public Fragment getItem(int position) {
        return childFragments[position];
    }

    @Override
    public int getCount() {
        return childFragments.length; //3 items
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}