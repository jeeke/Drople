package com.example.gyanesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import com.example.gyanesh.myapplication.Adapters.SelectClothesAdapter;
import com.example.gyanesh.myapplication.Models.Garment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.gyanesh.myapplication.utilClasses.CloudDbHelper.getGarmentsList;

public class SelectClothesActivity extends AppCompatActivity implements SelectClothesAdapter.Listener {

    Map<String, Garment> selectedGarments;
    TextView total_amt;
    int total_amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_clothes);
        total_amt = findViewById(R.id.total_amount);
        selectedGarments = new HashMap<>();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new SelectClothesAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selectedGarments",(Serializable) selectedGarments);
                intent.putExtra("total_amount",total_amount);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

    }

    @Override
    public void updateSelected(Garment item,int prevCount) {

        if (selectedGarments.containsKey(item.getObjectId())) {
            if(item.getCount()==0){
                selectedGarments.remove(item.getObjectId());
            }
            total_amount -= item.getPrice()*prevCount;
            total_amount += item.getPrice()*item.getCount();

        } else {
            total_amount += item.getPrice() * item.getCount();
            selectedGarments.put(item.getObjectId(), item);
        }
        total_amt.setText(String.valueOf(total_amount));
    }
}
