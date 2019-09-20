package com.drople;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.drople.Adapters.SelectClothesAdapter;
import com.drople.Models.Garment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
        int count = Integer.parseInt(item.count);
        int price = Integer.parseInt(item.price);

        if (selectedGarments.containsKey(item.id)) {
            if(count==0){
                selectedGarments.remove(item.id);
            }
            total_amount -= price*prevCount;
            total_amount += price*count;

        } else {
            total_amount += price * count;
            selectedGarments.put(item.id, item);
        }
        total_amt.setText(String.valueOf(total_amount));
    }
}
