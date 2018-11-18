package com.example.gyanesh.myapplication.Fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.gyanesh.myapplication.R;
// ...

public class Date_Picker extends DialogFragment {

    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;
    private static String date;
    public Date_Picker() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_date_picker, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = setDate(v);
            }
        };
        // Get field from view
        cardView1 = view.findViewById(R.id.cv1);
        cardView2 = view.findViewById(R.id.cv2);
        cardView3 = view.findViewById(R.id.cv3);
        cardView1.setOnClickListener(clickListener);
        cardView2.setOnClickListener(clickListener);
        cardView3.setOnClickListener(clickListener);

    }

    public String setDate(View v){
        TextView textView = v.findViewById(R.id.date);
        return ((String) textView.getText());
    }

    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.8), (int) (size.y * 0.55));
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        super.onResume();
    }

}