package com.drople.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.drople.AuthActivity;
import com.drople.BaseActivity;
import com.drople.DashboardActivity;
import com.drople.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import static com.drople.AuthActivity.TAG;
import static com.drople.BaseActivity.showSnackBar;

public class LoginFragment extends Fragment {


    private EditText usernameView;
    private EditText passwordView;
    private ProgressDialog dlg;
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                showSnackBar(getActivity(),"Google sign in failed");
                Log.e(TAG, "Google sign in failed", e);
//                updateUI();
            }
        }
    }

    private void updateUI() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(getContext(), DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.card_login, container, false);
        usernameView = view.findViewById(R.id.email_login);
        passwordView = view.findViewById(R.id.password_login);
        dlg = new ProgressDialog(getContext());
        View google = view.findViewById(R.id.google);
        google.setOnClickListener(v -> {
            signInGoogle();
        });
        FloatingActionButton login_button = view.findViewById(R.id.btn_login);
        login_button.setOnClickListener(v -> login());
        return view;
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        dlg.setTitle("Logging You In, Please Wait....");
        dlg.show();
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    dlg.dismiss();
                    if (task.isSuccessful()) {
                        updateUI();
                    } else {
                        showSnackBar(getActivity(),"Authentication Failed");
                    }
                });
    }

    private void signInGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
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
            showSnackBar(getActivity(), validationErrorMessage.toString());
            return;
        }

        BaseActivity activity = (BaseActivity) getActivity();
        activity.server.login(usernameView.getText().toString(),passwordView.getText().toString());
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 7;
    }

    private boolean isEmpty(EditText text) {
        if (text.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
}
