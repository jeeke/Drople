package com.example.gyanesh.myapplication.Fragments;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gyanesh.myapplication.LoginActivity;
import com.example.gyanesh.myapplication.R;
import com.example.gyanesh.myapplication.SignUpActivity;
import com.parse.ParseUser;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFrag extends Fragment {


    public ProfileFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_profile, container, false);

        String name = ParseUser.getCurrentUser().getUsername();
        String email = ParseUser.getCurrentUser().getEmail();
        //TODO BIND data to views
        TextView textView = view.findViewById(R.id.name).findViewById(R.id.text);
        textView.setText(name);

        textView = view.findViewById(R.id.email).findViewById(R.id.text);
        textView.setText(email);


        final Button logout_button = view.findViewById(R.id.logout_btn);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                 logging out of Parse
                ParseUser.logOut();
                alertDisplayer("So, you're going...", "Ok...Bye-bye then");

                final ProgressDialog dlg = new ProgressDialog(getContext());
                dlg.setTitle("Please, wait a moment.");
                dlg.setMessage("Redirecting you to register...");
                dlg.show();
                Intent intent = new Intent(getContext(), SignUpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                dlg.dismiss();
                startActivity(intent);
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
                        // don't forget to change the line below with the names of your Activities
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

}
