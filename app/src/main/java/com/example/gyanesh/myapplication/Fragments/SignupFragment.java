package com.example.gyanesh.myapplication.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gyanesh.myapplication.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import androidx.fragment.app.Fragment;

public class SignupFragment extends Fragment {

    private EditText usernameView;
    private EditText passwordView;
    private EditText emailView;
    private EditText passwordAgainView;

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

    public void signUp(View v) {

        //Validating the log in data
        boolean validationError = false;

        StringBuilder validationErrorMessage = new StringBuilder("Please, insert ");
        if (isEmpty(usernameView)) {
            validationError = true;
            validationErrorMessage.append("an username");
        }
        if (isEmpty(emailView) || !isEmailValid(emailView.getText().toString())) {
            if (validationError) {
                validationErrorMessage.append(" and ");
            }
            validationError = true;
            validationErrorMessage.append("a valid email address");
        }
        if (isEmpty(passwordView) || !isPasswordValid(passwordView.getText().toString())) {
            if (validationError) {
                validationErrorMessage.append(" and ");
            }
            validationError = true;
            validationErrorMessage.append("a valid password");
        }
        if (isEmpty(passwordAgainView)) {
            if (validationError) {
                validationErrorMessage.append(" and ");
            }
            validationError = true;
            validationErrorMessage.append("your password again");
        } else {
            if (!isMatching(passwordView, passwordAgainView)) {
                if (validationError) {
                    validationErrorMessage.append(" and ");
                }
                validationError = true;
                validationErrorMessage.append("the same password twice.");
            }
        }
        validationErrorMessage.append(".");

        if (validationError) {
            Toast.makeText(getActivity(), validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
            return;
        }

        //Setting up a progress dialog
        final ProgressDialog dlg = new ProgressDialog(getActivity());
        dlg.setTitle("Please, wait a moment.");
        dlg.setMessage("Signing up...");
        dlg.show();

        try {
            // Reset errors.
            emailView.setError(null);
            passwordView.setError(null);

            ParseUser user = new ParseUser();
            user.setUsername(usernameView.getText().toString());
            user.setPassword(passwordView.getText().toString());
            user.setEmail(emailView.getText().toString());
            user.put("userType", 0);

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        dlg.dismiss();
                        ParseUser.logOut();
                        alertDisplayer("Account Created Successfully!", "Please verify your email before Login", false);
                    } else {
                        dlg.dismiss();
                        ParseUser.logOut();
                        alertDisplayer("Error Account Creation failed", "Account could not be created" + " :" + e.getMessage(), true);
                    }
                }
            });
        } catch (Exception e) {
            dlg.dismiss();
            e.printStackTrace();
        }
    }


    private boolean isEmpty(EditText text) {
        if (text.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isMatching(EditText text1, EditText text2) {
        if (text1.getText().toString().equals(text2.getText().toString())) {
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
}
