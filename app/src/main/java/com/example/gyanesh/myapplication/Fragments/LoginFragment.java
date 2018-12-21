package com.example.gyanesh.myapplication.Fragments;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyanesh.myapplication.MainActivity;
import com.example.gyanesh.myapplication.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import androidx.appcompat.app.AlertDialog;

public class LoginFragment extends AuthFragment {


    private EditText usernameView;
    private EditText passwordView;

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(getActivity(),MainActivity.class));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_login, container, false);
        usernameView = view.findViewById(R.id.email_login);
        passwordView = view.findViewById(R.id.password_login);
        Button login_button = view.findViewById(R.id.button);


        TextView no_account = view.findViewById(R.id.no_account);
        no_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null) listener.loadFragment(new SignupFragment());
            }
        });


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        return view;
    }

    private void login() {
        //Validating the log in data
        boolean validationError = false;

        StringBuilder validationErrorMessage = new StringBuilder("Please, insert ");
        if (isEmpty(usernameView)) {
            validationError = true;
            validationErrorMessage.append("an username");
        }
        if (isEmpty(passwordView) || !isPasswordValid(passwordView.getText().toString())) {
            if (validationError) {
                validationErrorMessage.append(" and ");
            }
            validationError = true;
            validationErrorMessage.append("a valid password");
        }
        validationErrorMessage.append(".");

        if (validationError) {
            Toast.makeText(getActivity(), validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
            return;
        }

        //Setting up a progress dialog
        final ProgressDialog dlg = new ProgressDialog(getActivity());
        dlg.setTitle("Please, wait a moment.");
        dlg.setMessage("Logging in...");
        dlg.show();

        passwordView.setError(null);

        ParseUser.logInInBackground(usernameView.getText().toString(), passwordView.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    if (parseUser.getBoolean("emailVerified")) {
                        dlg.dismiss();
                        alertDisplayer("Login Sucessful", "Welcome, " + parseUser.getUsername().toString() + "!", false);
                    } else {
                        ParseUser.logOut();
                        dlg.dismiss();
                        alertDisplayer("Login Fail", "Please Verify Your Email first", true);
                    }
                } else {
                    ParseUser.logOut();
                    dlg.dismiss();
                    alertDisplayer("Login Fail", e.getMessage() + " Please re-try", true);
                }
            }
        });
    }

    private void alertDisplayer(String title, String message, final boolean error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if (!error) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private boolean isEmpty(EditText text) {
        if (text.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
}
