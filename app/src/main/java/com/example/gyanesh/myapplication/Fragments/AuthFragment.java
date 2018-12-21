package com.example.gyanesh.myapplication.Fragments;

import android.content.Context;

import androidx.fragment.app.Fragment;

public class AuthFragment extends Fragment {

    public interface Listener{
        void loadFragment(Fragment fragment);
    }

    public static Listener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Listener) context;
    }
}
