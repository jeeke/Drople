package com.example.gyanesh.myapplication.Fragments.utils;


import com.example.gyanesh.myapplication.Fragments.AccountFrag;
import com.example.gyanesh.myapplication.Fragments.HomeFrag;
import com.example.gyanesh.myapplication.Fragments.ServicesFrag;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentPagerAdapterX extends FragmentPagerAdapter {

    public FragmentPagerAdapterX(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {


        switch (i) {

            case 0:
                return new HomeFrag();
            case 1:
                return new AccountFrag();
            case 2:
                return new ServicesFrag();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "HOME";
            case 1:
                return "FAVOURITE";
            case 2:
                return "CLOUD";
        }
        return null;
        }

}

