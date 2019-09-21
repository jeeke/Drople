package com.drople.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.drople.Fragments.DryWash;
import com.drople.Fragments.Premium;
import com.drople.Fragments.washIronFrag;

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
