package com.example.gyanesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {


    private EditText usernameView;
    private EditText passwordView;
    private EditText emailView;
    private EditText passwordAgainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_sign_up);

        usernameView = (EditText) findViewById(R.id.input_name);
        emailView = (EditText) findViewById(R.id.input_email);
        passwordView = (EditText) findViewById(R.id.input_password);
        passwordAgainView = (EditText) findViewById(R.id.input_password_again);

    }
//my comment is Here
    public void signUp(View v) {

        //Validating the log in data
        boolean validationError = false;

        StringBuilder validationErrorMessage = new StringBuilder("Please, insert ");
        if (isEmpty(usernameView)) {
            validationError = true;
            validationErrorMessage.append("an username");
        }
        if (isEmpty(emailView)||!isEmailValid(emailView.getText().toString())) {
            if (validationError) {
                validationErrorMessage.append(" and ");
            }
            validationError = true;
            validationErrorMessage.append("a valid email address");
        }
        if (isEmpty(passwordView)||!isPasswordValid(passwordView.getText().toString())) {
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
        }
        else {
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
            Toast.makeText(SignUpActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
            return;
        }

        //Setting up a progress dialog
        final ProgressDialog dlg = new ProgressDialog(SignUpActivity.this);
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
            user.put("userType",0);

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

    public void login(View v){
        Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(intent);
    }


    private boolean isEmpty(EditText text) {
        if (text.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isMatching(EditText text1, EditText text2){
        if(text1.getText().toString().equals(text2.getText().toString())){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private void alertDisplayer(String title,String message, final boolean error){
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        if(!error) {
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}