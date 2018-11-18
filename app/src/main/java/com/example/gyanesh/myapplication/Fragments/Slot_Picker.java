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

public class Slot_Picker extends DialogFragment {

    private CardView cardView1;
    private CardView cardView2;
    public static int slotId;
    public Slot_Picker() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_slot_picker, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slotId = setSlot(v);
            }
        };
        // Get field from view
        cardView1 = view.findViewById(R.id.slot_cv1);
        TextView textView1 = cardView1.findViewById(R.id.slot);
        textView1.setText(SlotContract.slot1);
        cardView2 = view.findViewById(R.id.slot_cv2);
        TextView textView2 = cardView2.findViewById(R.id.slot);
        textView2.setText(SlotContract.slot2);
        cardView1.setOnClickListener(clickListener);
        cardView2.setOnClickListener(clickListener);
    }

    public int setSlot(View v){
        TextView textView = v.findViewById(R.id.slot);
        return SlotContract.slotNo((String) textView.getText());
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