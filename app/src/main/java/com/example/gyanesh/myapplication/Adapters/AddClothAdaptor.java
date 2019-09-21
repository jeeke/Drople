package com.example.gyanesh.myapplication.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.gyanesh.myapplication.Fragments.DryWash;
import com.example.gyanesh.myapplication.Fragments.Premium;
import com.example.gyanesh.myapplication.Fragments.VendorFragmentComp;
import com.example.gyanesh.myapplication.Fragments.VendorFragmentPending;
import com.example.gyanesh.myapplication.Fragments.VendorFragmentProgress;
import com.example.gyanesh.myapplication.Fragments.washIronFrag;

public class AddClothAdaptor extends FragmentPagerAdapter {

    private Fragment[] childFragments;
    private String[] titles = {"Wash & Iron", "Dry wash", "Premium"};

    public AddClothAdaptor(FragmentManager fm) {
        super(fm);
        childFragments = new Fragment[]{
                new washIronFrag(), //0
                new DryWash(), //1
                new Premium(), //2
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
