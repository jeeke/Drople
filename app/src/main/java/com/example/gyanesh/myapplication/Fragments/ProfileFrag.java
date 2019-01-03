package com.example.gyanesh.myapplication.Fragments;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyanesh.myapplication.AuthActivity;
import com.example.gyanesh.myapplication.R;
import com.parse.ParseUser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
public class ProfileFrag extends MainActivityFragments {


    public ProfileFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_profile, container, false);
        setHasOptionsMenu(true);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        String name = ParseUser.getCurrentUser().getUsername();
        String email = ParseUser.getCurrentUser().getEmail();
        //TODO BIND data to views
        TextView textView = view.findViewById(R.id.include1).findViewById(R.id.name);
        textView.setText(name);

        textView = view.findViewById(R.id.profile_layout).findViewById(R.id.textView20);
        textView.setText(email);

        TextView textView1 = view.findViewById(R.id.profile_layout).findViewById(R.id.textView33);
        textView1.setText("Name : "+ name);


        final TextView logout_button = view.findViewById(R.id.profile_layout).findViewById(R.id.textView35);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO Cofirmation dialog on logout click
                alertDisplayer("So, you're going...", "Ok...Bye-bye then");
            }
        });
        return view;
    }


    private void alertDisplayer(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
//  l                   ogging out of Parse
                        ProgressDialog dlg = new ProgressDialog(getContext());
                        dlg.setTitle("Please, wait a moment.");
                        dlg.setMessage("Redirecting you to register...");
                        dlg.show();
                        // don't forget to change the line below with the names of your Activities
                        ParseUser.logOut();
                        Intent intent = new Intent(getContext(), AuthActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        dlg.dismiss();
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

}
