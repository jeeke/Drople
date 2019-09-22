package com.drople.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.drople.DonateActivity;
import com.drople.PlaceOrderActivity;
import com.drople.R;
import com.drople.Adapters.ViewPagerAdapter;
import com.drople.ServicesActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends MainActivityFragments {

    public HomeFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.frag_home, container, false);
        setHasOptionsMenu(true);
        toolbar = layout.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ViewPager viewPager = layout.findViewById(R.id.offers);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext(), layout.findViewById(R.id.pageIndicatorView));
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);

        ImageView imageView;
        View home_button;
        TextView head;
//        place order
        home_button = layout.findViewById(R.id.btn_home1);
        imageView = home_button.findViewById(R.id.btn_home_img);
        imageView.setImageResource(R.drawable.place);
        home_button.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), PlaceOrderActivity.class);
            startActivity(intent);
        });


//        btn_donate clothes
        home_button = layout.findViewById(R.id.btn_home2);
        head = home_button.findViewById(R.id.head1);
        head.setText("Donate Clothes");
        imageView = home_button.findViewById(R.id.btn_home_img);
        imageView.setImageResource(R.drawable.btn_donate);
        home_button.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DonateActivity.class);
            startActivity(intent);
        });


        View.OnClickListener servicesListener = v -> {
            Intent intent = new Intent(getContext(), ServicesActivity.class);
            intent.putExtra("btnId", v.getId());
            startActivity(intent);
        };

        //Wash N Iron Button
        home_button = layout.findViewById(R.id.btn_home3);
        head = home_button.findViewById(R.id.head1);
        head.setText("Wash & Iron");
        imageView = home_button.findViewById(R.id.btn_home_img);
        imageView.setImageResource(R.drawable.normal1);
        home_button.setOnClickListener(servicesListener);


        //Ironing Button
        home_button = layout.findViewById(R.id.btn_home4);
        head = home_button.findViewById(R.id.head2);
        head.setText("min 12 hrs");
        imageView = home_button.findViewById(R.id.btn_home_img);
        imageView.setImageResource(R.drawable.wash_n_iron);
        home_button.setOnClickListener(servicesListener);


        //Dry Wash Button
        home_button = layout.findViewById(R.id.btn_home5);
        head = home_button.findViewById(R.id.head1);
        head.setText("Dry Wash");
        head = home_button.findViewById(R.id.head2);
        head.setText("min 2 days");
        imageView = home_button.findViewById(R.id.btn_home_img);
        imageView.setImageResource(R.drawable.dry);
        home_button.setOnClickListener(servicesListener);


        //Premium Wash Button
        home_button = layout.findViewById(R.id.btn_home6);
        head = home_button.findViewById(R.id.head1);
        head.setText("Premium");
        head = home_button.findViewById(R.id.head2);
        head.setText("min 1 days");
        imageView = home_button.findViewById(R.id.btn_home_img);
        imageView.setImageResource(R.drawable.premium4);
        home_button.setOnClickListener(servicesListener);


        return layout;
    }

}
