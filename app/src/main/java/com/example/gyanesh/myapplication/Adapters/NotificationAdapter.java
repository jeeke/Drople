package com.example.gyanesh.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gyanesh.myapplication.Models.NotificationData;
import com.example.gyanesh.myapplication.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.holder> {

    ArrayList<NotificationData> list;
    View mView;

    public NotificationAdapter(ArrayList<NotificationData> list)
    {
        this.list = list;
    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notification,parent,false);
        mView = view;
        holder viewholder = new holder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        String text = list.get(position).getText();
        holder.setuptext(text);

        int imageid = list.get(position).getImage();
        holder.setupimage(imageid);

        String time = list.get(position).getTime();
        holder.setuptime(time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder
    {

        public holder(@NonNull View itemView) {
            super(itemView);
        }

        public void setuptext(String string)
        {
            TextView textView = mView.findViewById(R.id.notification_text_id);
            textView.setText(string);
        }
        public void setupimage(int image)
        {
            ImageView imageView = mView.findViewById(R.id.notification_image_id);
            imageView.setImageResource(image);
        }

        public void setuptime(String time)
        {
            TextView timeview = mView.findViewById(R.id.notification_time);
            timeview.setText(time);
        }
    }
}
