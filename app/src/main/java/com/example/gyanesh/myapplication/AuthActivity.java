package com.example.gyanesh.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gyanesh.myapplication.Fragments.LoginFragment;
import com.example.gyanesh.myapplication.Fragments.SignupFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

//        Initialising with login fragment
        loadFragment(new LoginFragment());

        final TextView loginPage = findViewById(R.id.login_page);
        final TextView signupPage = findViewById(R.id.signup_page);
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupPage.setTextColor(getResources().getColor(R.color.colorPrimaryLight));
                ((TextView)v).setTextColor(getResources().getColor(R.color.colorPrimary));
                loadFragment(new LoginFragment());
            }
        });
        signupPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPage.setTextColor(getResources().getColor(R.color.colorPrimaryLight));
                ((TextView)v).setTextColor(getResources().getColor(R.color.colorPrimary));
                loadFragment(new SignupFragment());
            }
        });

    }
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

