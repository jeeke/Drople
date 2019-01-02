package com.example.gyanesh.myapplication;


import android.os.Bundle;
import android.view.MenuItem;

import com.example.gyanesh.myapplication.Fragments.HomeFrag;
import com.example.gyanesh.myapplication.Fragments.ProfileFrag;
import com.example.gyanesh.myapplication.Fragments.ServicesFrag;
import com.example.gyanesh.myapplication.utilClasses.BackgroundData;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO Update data in background
        BackgroundData.getRemoteAddresses();

        setContentView(R.layout.activity_main);

//        Setting navigation bar
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        Initialising with home fragment
        loadFragment(new HomeFrag());

    }

    //    Navigation bar handler
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_support:
                    fragment = new ProfileFrag();
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
                    fragment = new ServicesFrag();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
}
