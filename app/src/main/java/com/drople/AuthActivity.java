package com.drople;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.drople.Fragments.LoginFragment;
import com.drople.Fragments.SignupFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.drople.utilClasses.PrefManager.PREF_NAME;

public class AuthActivity extends BaseActivity {

    public static final String TAG = "AuthActivity";
    SharedPreferences sp;

    @Override
    protected void onResume() {
        super.onResume();
        sp = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        if (sp.getBoolean("showIntro", true)) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("showIntro", false);
            editor.apply();
            Intent intent = new Intent(this, IntroActivity.class); // Call the AppIntro java class
            startActivity(intent);
        } else {
            setContentView(R.layout.activity_auth);
//          Initialising with login fragment
            loadFragment(new LoginFragment());

            final TextView loginPage = findViewById(R.id.login_page);
            final TextView signupPage = findViewById(R.id.signup_page);
            loginPage.setOnClickListener(v -> {
                signupPage.setTextColor(getResources().getColor(R.color.colorPrimaryLight));
                ((TextView)v).setTextColor(getResources().getColor(R.color.colorPrimary));
                loadFragment(new LoginFragment());
            });
            signupPage.setOnClickListener(v -> {
                loginPage.setTextColor(getResources().getColor(R.color.colorPrimaryLight));
                ((TextView)v).setTextColor(getResources().getColor(R.color.colorPrimary));
                loadFragment(new SignupFragment());
            });
        }

    }

    private void updateUI() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        updateUI();
    }
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

