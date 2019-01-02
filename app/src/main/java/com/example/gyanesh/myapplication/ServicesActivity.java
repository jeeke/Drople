package com.example.gyanesh.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.gyanesh.myapplication.Fragments.wash_iron;
import com.example.gyanesh.myapplication.Fragments.premium;
import com.example.gyanesh.myapplication.Fragments.iron;
import com.example.gyanesh.myapplication.Fragments.dry_wash;
import androidx.fragment.app.FragmentTransaction;

public class ServicesActivity extends AppCompatActivity {


    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        int btnId=getIntent().getExtras().getInt("btnId");

            switch(btnId)
            {
                case R.id.btn_home3:
                    wash_iron fragmentw=new wash_iron();
                    loadFragment(fragmentw);
                    break;
                case R.id.btn_home4:
                    iron fragmenti=new iron();
                    loadFragment(fragmenti);
                    break;
                case R.id.btn_home5:
                    dry_wash fragmentd=new dry_wash();
                   loadFragment(fragmentd);
                   break;

                case R.id.btn_home6:
                    premium fragmentp=new premium();
                  loadFragment(fragmentp);
                  break;

            }
        }
    }

