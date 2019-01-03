package com.example.gyanesh.myapplication.Fragments;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gyanesh.myapplication.R;

import androidx.fragment.app.Fragment;

public class MainActivityFragments extends Fragment {

    protected androidx.appcompat.widget.Toolbar toolbar;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notification:
                Toast.makeText(getContext(), "Notification selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.rate_us:
                Toast.makeText(getContext(), "Rate selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }
        return true;
    }
}
