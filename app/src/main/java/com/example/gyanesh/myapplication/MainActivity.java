package com.example.gyanesh.myapplication;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gyanesh.myapplication.Fragments.HomeFrag;
import com.example.gyanesh.myapplication.Fragments.ProfileFrag;
import com.example.gyanesh.myapplication.Fragments.ServicesFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

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
            // action with ID action_refresh was selected
            case R.id.about_us:
                Toast.makeText(this, "About Us selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            // action with ID action_settings was selected
            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.refer:
                Toast.makeText(this, "Refer selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.feedback:
                Toast.makeText(this, "Feedback selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.contact_us:
                Toast.makeText(this, "Contact selected", Toast.LENGTH_SHORT)
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
        toolbar = getSupportActionBar();

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
