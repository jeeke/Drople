package com.drople.Fragments;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drople.AuthActivity;
import com.drople.R;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

        String name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
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
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.cancel();
//                     Logging out of Parse
                    ProgressDialog dlg = new ProgressDialog(getContext());
                    dlg.setTitle("Please, wait a moment.");
                    dlg.setMessage("Redirecting you to register...");
                    dlg.show();
                    // don't forget to change the line below with the names of your Activities
//                        FirebaseAuth.logOut();
                    Intent intent = new Intent(getContext(), AuthActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    dlg.dismiss();
                    startActivity(intent);
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

}
