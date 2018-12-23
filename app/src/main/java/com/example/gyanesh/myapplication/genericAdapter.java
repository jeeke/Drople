package com.example.gyanesh.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.leodroidcoder.genericadapter.BaseViewHolder;
import com.leodroidcoder.genericadapter.GenericRecyclerViewAdapter;
import com.leodroidcoder.genericadapter.OnRecyclerItemClickListener;

public class genericAdapter extends GenericRecyclerViewAdapter<User,OnRecyclerItemClickListener,BaseViewHolder<User,OnRecyclerItemClickListener>> {


    public genericAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
    }

    @Override
    public BaseViewHolder<User, OnRecyclerItemClickListener> onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}