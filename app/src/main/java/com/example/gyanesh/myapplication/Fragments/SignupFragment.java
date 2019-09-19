package com.example.gyanesh.myapplication.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gyanesh.myapplication.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import androidx.fragment.app.Fragment;

public class SignupFragment extends Fragment {

    private EditText usernameView;
    private EditText passwordView;
    private EditText emailView;
    private EditText passwordAgainView;
    private String uName,email,password;

    public SignupFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.card_signup, container, false);
        super.onCreate(savedInstanceState);

        usernameView = (EditText) view.findViewById(R.id.name);
        emailView = (EditText) view.findViewById(R.id.email);
        passwordView = (EditText) view.findViewById(R.id.password);
        passwordAgainView = (EditText) view.findViewById(R.id.confirm_pass);

        return view;
    }



    private boolean checkError(View v) {

        uName = usernameView.getText().toString();
        password = passwordView.getText().toString();
        email = emailView.getText().toString();
        String passwordAgain = passwordAgainView.getText().toString();
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder("Please, insert ");
        if (isEmpty(uName)) {
            validationError = true;
            validationErrorMessage.append("an username");
        }
        if (isEmpty(email) || !isEmailValid(emailView.getText().toString())) {
            if (validationError) {
                validationErrorMessage.append(" and ");
            }
            validationError = true;
            validationErrorMessage.append("a valid email address");
        }
        if (isEmpty(password) || !isPasswordValid(passwordView.getText().toString())) {
            if (validationError) {
                validationErrorMessage.append(" and ");
            }
            validationError = true;
            validationErrorMessage.append("a valid password");
        }
        if (isEmpty(passwordAgain)) {
            if (validationError) {
                validationErrorMessage.append(" and ");
            }
            validationError = true;
            validationErrorMessage.append("your password again");
        }
        if (!isMatching(password, passwordAgain)) {
            if (validationError) {
                validationErrorMessage.append(" and ");
            }
            validationError = true;
            validationErrorMessage.append("the same password twice.");
        }
        validationErrorMessage.append(".");

        if (validationError) {
            Toast.makeText(getActivity(), validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
        }
        return validationError;
    }

    private ProgressDialog dlg;
    public void signUp() {
        //Setting up a progress dialog
        dlg = new ProgressDialog(getActivity());
        dlg.setTitle("Please, wait a moment.");
        dlg.setMessage("Signing up...");
        dlg.show();

        try {
            // Reset errors.
            emailView.setError(null);
            passwordView.setError(null);
        } catch (Exception e) {
            dlg.dismiss();
            e.printStackTrace();
        }
    }


    private boolean isEmpty(String string) {
        if (string.trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isMatching(String text1, String text2) {
        if (text1.equals(text2)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private void alertDisplayer(String title, String message, final boolean error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        //TODO redirect to login
                        if (!error) {
//                            if (listener != null)
//                                listener.loadFragment(new LoginFragment());
                        }
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

//    @Override
//    public void onClick(View v) {
//        int i = v.getId();
//        Intent intent = new Intent(this, LoginActivity.class);
//        if (i == R.id.google) {
//            signIn();
//        } else if (i == R.id.login) {
//            intent.putExtra("from", false);
//            startActivity(intent);
//        } else if (i == R.id.signup) {
//            startActivity(intent);
//        }
//    }
}
