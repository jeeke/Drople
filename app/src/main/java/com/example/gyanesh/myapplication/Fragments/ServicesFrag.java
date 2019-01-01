package com.example.gyanesh.myapplication.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.gyanesh.myapplication.Adapters.ActiveOrdersAdapter;
import com.example.gyanesh.myapplication.R;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ServicesFrag extends Fragment {

    //private int cnt = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.frag_services, container, false);



        String[] date={"16-01-2000","25-01-2000","31-31-2031","5-09-2000"};
        String [] progress={"25","30","90","60"};
        String [] orderid={"#0001","#0002","#0003","#0004"};
        ActiveOrdersAdapter activeOrdersAdapter = new ActiveOrdersAdapter(orderid,progress,date);
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.activeorderrv);
        recyclerView.setAdapter(activeOrdersAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        ConstraintLayout constraintLayout = view.findViewById(R.id.newrange);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recyclerView.getVisibility()==View.GONE)
                {
                    recyclerView.setVisibility(View.VISIBLE);
                }
                else
                {
                    recyclerView.setVisibility(View.GONE);
                }

            }
        });

        String[] date2={"16-01-2000","25-01-2000","31-31-2031","5"};
        String [] progress2={"100","100","100","100"};
        String [] orderid2={"#0001","#0002","#0003","4"};
        ActiveOrdersAdapter activeOrdersAdapter2 = new ActiveOrdersAdapter(orderid2,progress2,date2);
        final RecyclerView recyclerView2 = (RecyclerView)view.findViewById(R.id.historyrv);
        recyclerView2.setAdapter(activeOrdersAdapter2);
        //recyclerView2.setMinimumHeight(1000);






        //CustomLinearLayoutManager customLayoutManager2 = new CustomLinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(linearLayoutManager2);


        ConstraintLayout constraintLayout2 = view.findViewById(R.id.newrange2);
        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScrollView scrollView=view.findViewById(R.id.scrolltop);
                //scrollView.fullScroll(ScrollView.FOCUS_D);
                if(recyclerView2.getVisibility()==View.GONE)
                {
                    recyclerView2.setVisibility(View.VISIBLE);
                }
                else
                {
                    recyclerView2.setVisibility(View.GONE);
                }

            }
        });


        return view;
    }

}