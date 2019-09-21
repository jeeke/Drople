package com.drople.utilClasses;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.drople.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.drople.utilClasses.Constants.getDay;
import static com.drople.utilClasses.Constants.utilDate;

public class DateSelectManager {

    private Activity activity;
    private String selectedDate;
    private String selectedSlot;
    private Drawable colorv1, colorv4, colorv5, colorv6;
    private View v1, v2, v3, v4, v5, v6;

    public DateSelectManager(Activity activity) {
        this.activity = activity;
    }

    public void setColors() {
        final Date date = Calendar.getInstance().getTime();
        final Date default1 = utilDate(1);
        final Date default2 = utilDate(2);
        final Date default3 = utilDate(3);
        View slotLayout, timeLayout;
        slotLayout = activity.findViewById(R.id.slot_layout);
        timeLayout = activity.findViewById(R.id.time_layout);
        v1 = timeLayout.findViewById(R.id.date1);
        v2 = timeLayout.findViewById(R.id.date2);
        v3 = timeLayout.findViewById(R.id.date3);
        v4 = slotLayout.findViewById(R.id.date1);
        v5 = slotLayout.findViewById(R.id.date2);
        v6 = slotLayout.findViewById(R.id.date3);
        final Drawable colorAccent = activity.getResources().getDrawable(R.drawable.edit_text_round_blue);
        final Drawable colorPrimary = activity.getResources().getDrawable(R.drawable.edit_text_corner_round_grey);
        final Drawable grey = activity.getResources().getDrawable(R.drawable.edit_text_corner_round_grey);

        //TODO Starpoint use view.onclickListener before setClickable false

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(colorAccent);

                TextView temp = v.findViewById(R.id.date);
                selectedDate = temp.getText().toString();

                v2.setBackground(colorPrimary);
                v3.setBackground(colorPrimary);
                if (date.after(default1)) {
                    v4.setBackground(grey);
                    colorv4 = grey;
                    v4.setClickable(false);
                } else {
                    v4.setBackground(colorPrimary);
                    colorv4 = colorPrimary;
                    v4.setClickable(true);
                }
                if (date.after(default2)) {
                    v5.setBackground(grey);
                    colorv5 = grey;
                    v5.setClickable(false);
                } else {
                    v5.setBackground(colorPrimary);
                    colorv5 = colorPrimary;
                    v5.setClickable(true);
                }
            }
        });

        View.OnClickListener otherTwo = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView temp = v.findViewById(R.id.date);
                selectedDate = temp.getText().toString();
                v1.setBackground(colorv1);
                v2.setBackground(colorPrimary);
                v3.setBackground(colorPrimary);
                v.setBackground(colorAccent);
                v4.setBackground(colorPrimary);
                v5.setBackground(colorPrimary);
                v6.setBackground(colorPrimary);
                colorv4 = colorv5 = colorv6 = colorPrimary;
                v4.setClickable(true);
                v5.setClickable(true);
                v6.setClickable(true);
            }
        };
        v2.setOnClickListener(otherTwo);
        v3.setOnClickListener(otherTwo);


        View.OnClickListener slotListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v4.setBackground(colorv4);
                v5.setBackground(colorv5);
                v6.setBackground(colorv6);
                v.setBackground(colorAccent);
                TextView temp = v.findViewById(R.id.date);
                selectedSlot = temp.getText().toString();
            }
        };
        v4.setOnClickListener(slotListener);
        v5.setOnClickListener(slotListener);
        v6.setOnClickListener(slotListener);

        if (date.after(default3)) {
            v1.setBackground(grey);
            colorv1 = grey;
            v1.setClickable(false);
            v4.setClickable(false);
            v5.setClickable(false);
            v6.setClickable(false);
        } else {
            colorv1 = colorPrimary;
            v1.setBackground(colorPrimary);
        }
        v2.setBackground(colorPrimary);
        v3.setBackground(colorPrimary);
        v4.setBackground(grey);
        v5.setBackground(grey);
        v6.setBackground(grey);

        SimpleDateFormat df1 = new SimpleDateFormat("d  MMM");
        String formattedDate = df1.format(date);
        TextView textView1 = v1.findViewById(R.id.date);
        textView1.setText(formattedDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        String formattedDate2 = df1.format(cal.getTime());
        int d1 = (cal.get(Calendar.DAY_OF_WEEK) ) % 7;
        TextView day11 = v1.findViewById(R.id.day);
        day11.setText(getDay(d1));
        TextView textView2 = v2.findViewById(R.id.date);
        textView2.setText(formattedDate2);
        int d2 = (d1 % 7) + 1;
        TextView day22 = v2.findViewById(R.id.day);
        day22.setText(getDay(d2));
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date);
        cal2.add(Calendar.DAY_OF_YEAR, 2);
        String formattedDate3 = df1.format(cal2.getTime());
        TextView textView3 = v3.findViewById(R.id.date);
        textView3.setText(formattedDate3);
        int d3 = (d2 % 7) + 1;
        Log.e(" Days",d1 + " " + d2 + " "+ d3);
        TextView day33 = v3.findViewById(R.id.day);
        day33.setText(getDay(d3));


        TextView textView;
        textView = v4.findViewById(R.id.date);
        textView.setText("10 : 00");
        textView = v4.findViewById(R.id.day);
        textView.setText("A M");
        textView = v5.findViewById(R.id.date);
        textView.setText("2 : 00");
        textView = v5.findViewById(R.id.day);
        textView.setText("P M");
        textView = v6.findViewById(R.id.date);
        textView.setText("6 : 00");
        textView = v6.findViewById(R.id.day);
        textView.setText("P M");
    }

    public String getSelectedDate() {
        if(selectedSlot == null || selectedDate == null) return null;
        return selectedDate + "  Slot: "+selectedSlot;
    }
}
