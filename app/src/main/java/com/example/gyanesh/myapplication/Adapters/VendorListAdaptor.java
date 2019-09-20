package com.example.gyanesh.myapplication.Adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gyanesh.myapplication.R;

import static com.paytm.pgsdk.easypay.manager.PaytmAssist.getContext;

public class VendorListAdaptor extends RecyclerView.Adapter<VendorListAdaptor.ViewHolder>{

    Integer numberofobjects;
    public VendorListAdaptor(Integer numberofobjects) {
        this.numberofobjects = numberofobjects;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.vendorcard, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.clothesdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.deatilofclothes.getVisibility()==View.GONE)
                {
//                    holder.clothesdetail.setCompoundDrawables(v.getContext().getResources().getDrawable(R.drawable.up_arrow_show),null,null,null);
                    holder.deatilofclothes.setVisibility(View.VISIBLE);
                    VendorClothesAdaptor mAdapter = new VendorClothesAdaptor(10);//response.body().getData());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    holder.recyclerView.setLayoutManager(mLayoutManager);
                    holder.recyclerView.setItemAnimator(new DefaultItemAnimator());
                    holder.recyclerView.setAdapter(mAdapter);
                }
                else
                {

                    holder.deatilofclothes.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return numberofobjects;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView clothesdetail;
        public ConstraintLayout deatilofclothes;
        public RecyclerView recyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.clothesdetail = (TextView) itemView.findViewById(R.id.clothesdetail);
            this.deatilofclothes = itemView.findViewById(R.id.detailofclothes);
            this.recyclerView = itemView.findViewById(R.id.clothesrv);
        }
    }


}

