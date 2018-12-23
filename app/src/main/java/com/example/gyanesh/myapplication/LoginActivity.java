package com.example.gyanesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gyanesh.myapplication.utilClasses.MyActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends MyActivity {

        private EditText usernameView;
        private EditText passwordView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            setContent(this,R.layout.activity_login);
            usernameView = (EditText) findViewById(R.id.email_login);
            passwordView = (EditText) findViewById(R.id.password_login);

        }

        public void login(View v) {
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
                Toast.makeText(LoginActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
                return;
            }

            //Setting up a progress dialog
            final ProgressDialog dlg = new ProgressDialog(LoginActivity.this);
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

        private void alertDisplayer(String title, String message, final boolean error) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            if (!error) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    });
            AlertDialog ok = builder.create();
            ok.show();
        }

        public void redirect(View v) {
            final ProgressDialog dlg = new ProgressDialog(LoginActivity.this);
            dlg.setTitle("Please, wait a moment.");
            dlg.setMessage("Redirecting you to register...");
            dlg.show();
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            dlg.dismiss();
            startActivity(intent);
        }


    public void retry(View view)
    {
        setContent(this,R.layout.activity_login);
    }


    @Override
    protected void doTheThing() {

    }
}
