//package com.myapplication;
//
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//
//import com.myapplication.Adapters.OrderHistoryAdapter;
//import com.myapplication.Models.Order;
//import com.myapplication.utilClasses.MyActivity;
//import com.parse.FindCallback;
//import com.parse.ParseException;
//import com.parse.ParseQuery;
//import com.parse.ParseUser;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import static com.myapplication.Models.GenericOrder.PICKUP_TIME_KEY;
//import static com.myapplication.Models.GenericOrder.USER_ID_KEY;
//
//public class HistoryActivity extends MyActivity {
//
//    ArrayList<Order> mOrders = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContent(this,R.layout.activity_main);
//        Toolbar toolbar;
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        androidx.appcompat.app.ActionBar actionBar =  getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//    }
//
//    @Override
//    protected void doTheThing() {
//        refreshOrders();
//    }
//
//    public void retry(View view){
//        setContent(this,R.layout.activity_main);
//    }
//
//    public void updateUi() {
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        OrderHistoryAdapter mAdapter = new OrderHistoryAdapter(mOrders);
//        recyclerView.setAdapter(mAdapter);
//        // associate the LayoutManager with the RecylcerView
//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HistoryActivity.this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//    }
//
//    void refreshOrders() {
//
//        final ProgressDialog dlg = new ProgressDialog(HistoryActivity.this);
//        dlg.setTitle("Please, wait a moment.");
//        dlg.setMessage("Fetching your genericOrders...");
//        dlg.show();
//        // Construct query to execute
//        //TODO add donation also to history
//        ParseQuery<Order> query = ParseQuery.getQuery(Order.class);
//        // Configure limit and sort order
//        query.setLimit(50);
//        query.orderByDescending(PICKUP_TIME_KEY);
//        // Execute query to fetch all genericOrders from Parse asynchronously
//        query.whereEqualTo(USER_ID_KEY, ParseUser.getCurrentUser().getObjectId());
//        // This is equivalent to a SELECT query with SQL
//        query.findInBackground(new FindCallback<Order>() {
//            public void done(List<Order> orders, ParseException e) {
//                if (e == null) {
//                    mOrders.clear();
//                    mOrders.addAll(orders);
//                    dlg.dismiss();
//                    Log.e("Size :  ", "" + mOrders.size());
//                } else {
//                    dlg.dismiss();
//                    Log.e("message", "Error Loading Orders" + e);
//                }
//                updateUi();
//            }
//        });
//    }
//
//}
