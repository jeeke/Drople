package com.example.gyanesh.myapplication.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.gyanesh.myapplication.DonateActivity;
import com.example.gyanesh.myapplication.HistoryActivity;
import com.example.gyanesh.myapplication.PlaceOrderActivity;
import com.example.gyanesh.myapplication.R;
import com.example.gyanesh.myapplication.TrackOrderActivity;
import com.example.gyanesh.myapplication.ViewPagerAdapter;

import java.util.Timer;
import java.util.TimerTask;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends Fragment {

//    private ViewFlipper viewFlipper;
    private ViewPager viewPager;
    public HomeFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.frag_home, container, false);
//        Attaching Listeners to Home buttons


        //Auto Image Slider
        //viewFlipper = layout.findViewById(R.id.offers);
        viewPager=layout.findViewById(R.id.offers);
        Context c= viewPager.getContext();
        // int images[] = {R.drawable.offers,R.drawable.error_image,R.drawable.donate};

        //for(int image:images){
        //flipperImages(image);
        //  }
        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(c);
        viewPager.setAdapter(viewPagerAdapter);
//        Timer timer= new Timer();
//        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);


//        donate clothes
        View home_button1 = layout.findViewById(R.id.constraintLayout2);
        home_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),DonateActivity.class);
                startActivity(intent);
            }
        });

//        track order
        View home_button2 = layout.findViewById(R.id.constraintLayout3);
        home_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TrackOrderActivity.class);
                startActivity(intent);
            }
        });

//        place order
        View home_button3 = layout.findViewById(R.id.constraintLayout4);
        home_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PlaceOrderActivity.class);
                startActivity(intent);
            }
        });

//        OrderModel History

        View home_button4 = layout.findViewById(R.id.constraintLayout5);
        home_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),HistoryActivity.class);
                startActivity(intent);
            }
        });
        return layout;
    }

//    public class MyTimerTask extends TimerTask
//    {
//
//        @Override
//        public void run() {
//            getActivity().runOnUiThread(new Runnable(){
//                @Override
//                public void run()
//                {
//                    if(viewPager.getCurrentItem()==0)
//                        viewPager.setCurrentItem(1);
//                    else if(viewPager.getCurrentItem()==1)
//                        viewPager.setCurrentItem(2);
//                    else
//                        viewPager.setCurrentItem(0);
//                }
//
//            });
//        }
//    }

//    private void flipperImages(int image){
//        ImageView imageView = new ImageView(getContext());
//        imageView.setBackgroundResource(image);
//        viewFlipper.addView(imageView);
//        viewFlipper.setFlipInterval(4000);
//        viewFlipper.setAutoStart(true);
//        viewFlipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
//        viewFlipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);
//    }


}
