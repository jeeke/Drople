package com.example.gyanesh.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.gyanesh.myapplication.R;
import com.rd.PageIndicatorView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private PageIndicatorView pageIndicatorView;
    private Integer[] images = {R.drawable.offers, R.drawable.error_image, R.drawable.donate};

    public ViewPagerAdapter(Context context, View pageIndicatorView) {
        this.context = context;
        this.pageIndicatorView = (PageIndicatorView) pageIndicatorView;
        this.pageIndicatorView.setCount(images.length);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        int real_pos = position % images.length;
        pageIndicatorView.setSelection((real_pos+1)%3);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = view.findViewById(R.id.imageView16);
        imageView.setImageResource(images[real_pos]);
        if(position==1)imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ViewPager vp = (ViewPager) container;
        vp.addView(view);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
