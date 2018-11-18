package com.example.gyanesh.myapplication.Fragments.utils;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.gyanesh.myapplication.Fragments.ServicesFrag;
import com.example.gyanesh.myapplication.Fragments.AccountFrag;
import com.example.gyanesh.myapplication.Fragments.HomeFrag;

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

