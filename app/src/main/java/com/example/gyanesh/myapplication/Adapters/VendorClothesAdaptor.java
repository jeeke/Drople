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

public class VendorClothesAdaptor extends RecyclerView.Adapter<VendorClothesAdaptor.ViewHolder>{

    Integer numberofobjects;
    public VendorClothesAdaptor(Integer numberofobjects) {
        this.numberofobjects = numberofobjects;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.clothesdetail, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return numberofobjects;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView clothesdetail;
//        public ConstraintLayout deatilofclothes;
        public ViewHolder(View itemView) {
            super(itemView);
//            this.clothesdetail = (TextView) itemView.findViewById(R.id.clothesdetail);
//            this.deatilofclothes = itemView.findViewById(R.id.detailofclothes);
        }
    }


}

