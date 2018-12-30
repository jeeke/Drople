package com.example.gyanesh.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images={R.drawable.offers,R.drawable.error_image,R.drawable.btn_donate};
    public ViewPagerAdapter(Context context)
    {
        this.context=context;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position)
    { layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView= view.findViewById(R.id.imageView16);
        imageView.setImageResource(images[position]);
        ViewPager vp=(ViewPager) container;
        vp.addView(view,0);
        return view;

    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        ViewPager vp= (ViewPager)container;
        View view= (View) object;
        vp.removeView(view);
    }
}
