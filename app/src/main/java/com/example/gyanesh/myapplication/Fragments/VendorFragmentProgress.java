package com.example.gyanesh.myapplication.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyanesh.myapplication.Adapters.VendorListAdaptor;
import com.example.gyanesh.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.content.Context.MODE_PRIVATE;

public class VendorFragmentProgress extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_vendor, container, false);
        recyclerView = rootView.findViewById(R.id.vendorrv);
        myapivendor("1",rootView);
        return rootView;
    }

    public void myapivendor(String no,View rootview)
    {
        VendorListAdaptor mAdapter = new VendorListAdaptor(10);//response.body().getData());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootview.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public void myapiInduction(String furnace, final View view) {

//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
//
//        alertDialogBuilder.setTitle("Getting Reports");
//
//        alertDialogBuilder.setMessage("Getting Reports please wait ...!!").setCancelable(false);
//
//        final AlertDialog alertDialog = alertDialogBuilder.create();
//
//        alertDialog.show();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(LoginShift.url)
//                .addConverterFactory(new NullOnEmptyConverterFactory())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
//        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        final String text = sharedPreferences.getString(TOKEN, "");
//
//        Call<DinductionModel> call = jsonPlaceHolder.getinductiondetail(text, furnace,InductionDashboard2.datenow);
//        call.enqueue(new Callback<DinductionModel>() {
//
//            @Override
//            public void onResponse(Call<DinductionModel> call, final Response<DinductionModel> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(view.getContext(), response.message(), Toast.LENGTH_SHORT).show();
//                    alertDialog.dismiss();
//                    return;
//                }
//                InductionAdaptor mAdapter = new InductionAdaptor(response.body().getData());
//                Log.v("dekg le",  ""+response.body().getData().size()+"  "+InductionDashboard2.datenow);
//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
//                recyclerView.setLayoutManager(mLayoutManager);
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
//                recyclerView.setAdapter(mAdapter);
//                Toast.makeText(view.getContext(), "Reports Updated", Toast.LENGTH_SHORT).show();
//                alertDialog.dismiss();
//
//                if(!InductionDashboard2.datenow.equals("00-00-0000"))
//                {
//                    ConstraintLayout constraintLayout = view.findViewById(R.id.now);
//                    constraintLayout.setVisibility(View.VISIBLE);
//                    TextView textView,textView1,textView2,textView3,textView4;
//                    textView = view.findViewById(R.id.twh);
//                    textView1 = view.findViewById(R.id.awh);
//                    textView2 = view.findViewById(R.id.au);
//                    textView3 = view.findViewById(R.id.amn);
//                    textView4 = view.findViewById(R.id.alw);
//                    textView.setText(response.body().getTwh().toString()+" hours");
//                    textView1.setText(response.body().getAwh().toString()+" hours");
//                    if(response.body().getAu()!=null)
//                        textView2.setText(response.body().getAu().toString());
//                    else
//                    {
//                        textView2.setText("-");
//                    }
//                    textView3.setText(response.body().getAmn().toString());
//
//                    if(response.body().getAlw()!=null)
//                        textView4.setText(response.body().getAlw().toString());
//                    else
//                    {
//                        textView4.setText("-");
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<DinductionModel> call, Throwable t) {
//                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.v("now", t.getMessage().toString());
//                alertDialog.dismiss();
//            }
//        });
    }

}

