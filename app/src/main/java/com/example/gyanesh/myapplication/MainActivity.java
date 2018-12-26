package com.example.gyanesh.myapplication;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.example.gyanesh.myapplication.Fragments.HomeFrag;
import com.example.gyanesh.myapplication.Fragments.ProfileFrag;
import com.example.gyanesh.myapplication.Fragments.ServicesFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_us:
                Intent intent = new Intent(this,ExtrasActivity.class);
                startActivity(intent);
                break;
            case R.id.action_notification:
                Toast.makeText(this, "Notification selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.policies:
                Toast.makeText(this, "Policies selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.rate_us:
                Toast.makeText(this, "Rate selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Setting navigation bar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("DASHBOARD");

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
                case R.id.nav_home:
                    toolbar.setTitle(R.string.nav_home);
                    fragment = new HomeFrag();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_account:
                    toolbar.setTitle(R.string.nav_account);
                    fragment = new ProfileFrag();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_services:
                    toolbar.setTitle(R.string.nav_services);
                    fragment = new ServicesFrag();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }


    };
}
