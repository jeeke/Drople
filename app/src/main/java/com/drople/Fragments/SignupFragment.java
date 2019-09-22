package com.drople.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.drople.BaseActivity;
import com.drople.R;

import androidx.fragment.app.Fragment;

import static com.drople.BaseActivity.showSnackBar;

public class SignupFragment extends Fragment {

    private EditText usernameView;
    private EditText passwordView;
    private EditText emailView;
    private EditText passwordAgainView;
    private String uName, email, password;

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
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.card_signup, container, false);
        usernameView = (EditText) view.findViewById(R.id.name_signup);
        emailView = (EditText) view.findViewById(R.id.email_signup);
        passwordView = (EditText) view.findViewById(R.id.password_signup);
        passwordAgainView = (EditText) view.findViewById(R.id.confirm_pass);
        view.findViewById(R.id.btn_signup).setOnClickListener(this::signUp);
        return view;
    }


    private boolean checkError() {
        uName = usernameView.getText().toString();
        password = passwordView.getText().toString();
        email = emailView.getText().toString();
        String passwordAgain = passwordAgainView.getText().toString();
        if (uName.equals("") || password.equals("") || email.equals("")) {
            showSnackBar(getActivity(), "Username,email and password can't be empty");
            return true;
        }else if (!password.equals(passwordAgain)) {
            showSnackBar(getActivity(), "Passwords do not match");
        }
        return false;
    }

    private void signUp(View v) {
            if (!checkError()) {
                //Setting up a progress dialog
                BaseActivity activity = (BaseActivity) getActivity();
                activity.server.signUp(uName, email, password);
            }
    }
}
