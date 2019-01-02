package com.example.gyanesh.myapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyanesh.myapplication.DonateActivity;
import com.example.gyanesh.myapplication.PlaceOrderActivity;
import com.example.gyanesh.myapplication.R;
import com.example.gyanesh.myapplication.Adapters.ViewPagerAdapter;
import com.example.gyanesh.myapplication.ServicesActivity;
import com.example.gyanesh.myapplication.TrackOrderActivity;
import com.example.gyanesh.myapplication.ViewPagerAdapter;
import com.rd.IndicatorManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends Fragment {

    //    private ViewFlipper viewFlipper;
    private ViewPager viewPager;
    private Toolbar toolbar;

    public HomeFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notification:
                Toast.makeText(getContext(), "Notification selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.rate_us:
                Toast.makeText(getContext(), "Rate selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.frag_home, container, false);
        setHasOptionsMenu(true);
        toolbar = layout.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        Attaching Listeners to Home buttons
        viewPager = layout.findViewById(R.id.offers);
//        viewPager.setClipToPadding(false);
//        //TODO Starpoint do not remove top and bottom padding coz its unsycronizes pager indicator motion
//        viewPager.setPadding(40,10,40,10);
//        viewPager.setPageMargin(20);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext(), layout.findViewById(R.id.pageIndicatorView));
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);

//        Timer timer= new Timer();
//        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);


        ImageView imageView;
        View home_button;
        TextView head;
//        place order
        home_button = layout.findViewById(R.id.btn_home1);
        imageView = home_button.findViewById(R.id.btn_home_img);
        imageView.setImageResource(R.drawable.place);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlaceOrderActivity.class);
                startActivity(intent);
            }
        });


//        btn_donate clothes
        home_button = layout.findViewById(R.id.btn_home2);
        head = home_button.findViewById(R.id.head1);
        head.setText("Donate Clothes");
        imageView = home_button.findViewById(R.id.btn_home_img);
        imageView.setImageResource(R.drawable.btn_donate);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DonateActivity.class);
                startActivity(intent);
            }
        });


        //       Services Buttons
        int btnId = 0;
        final int finalBtnId = btnId;
        View.OnClickListener servicesListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(),ServicesActivity.class);
                intent.putExtra("btnId", finalBtnId);
                startActivity(intent);

            }
        };

        //Wash N Iron Button
        home_button = layout.findViewById(R.id.btn_home3);
        btnId=R.id.btn_home3;
        head = home_button.findViewById(R.id.head1);
        head.setText("Wash & Iron");
        imageView = home_button.findViewById(R.id.btn_home_img);
        imageView.setImageResource(R.drawable.normal1);
        home_button.setOnClickListener(servicesListener);


        //Ironing Button
        home_button = layout.findViewById(R.id.btn_home4);
        btnId=R.id.btn_home4;
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

}
