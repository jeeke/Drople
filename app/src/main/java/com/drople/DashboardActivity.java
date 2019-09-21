package com.drople;


import android.os.Bundle;

import com.drople.Fragments.HomeFrag;
import com.drople.Fragments.ProfileFrag;
import com.drople.Fragments.OrdersFrag;
import com.drople.Fragments.FragSupport;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class DashboardActivity extends AppCompatActivity {

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

//        Setting navigation bar
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        Initialising with home fragment
        loadFragment(new HomeFrag());

    }

    //    Navigation bar handler
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.nav_support:
                        fragment = new FragSupport();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_home:
                        fragment = new HomeFrag();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_account:
                        fragment = new ProfileFrag();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_history:
                        fragment = new OrdersFrag();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            };
}
