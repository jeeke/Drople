//package com.example.gyanesh.myapplication;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//
//import com.example.gyanesh.myapplication.Models.OrderModel;
//import com.parse.FindCallback;
//import com.parse.ParseException;
//import com.parse.ParseQuery;
//import com.parse.ParseUser;import android.app.ProgressDialog;
//
//import java.util.List;
//
//public class SaloonActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_saloon);
//    }
//
//    private void getQueue(){
//
//        final ProgressDialog dlg = new ProgressDialog(SaloonActivity.this);
//        dlg.setTitle("Please, wait a moment.");
//        dlg.setMessage("Fetching your orders...");
//        dlg.show();
//        // Construct query to execute
//        ParseQuery<OrderModel> query = ParseQuery.getQuery(OrderModel.class);
//        // Configure limit and sort order
//        query.whereEqualTo("CITY", "JALANDHAR").whereEqualTo("SHOP_ID",1);
//        // This is equivalent to a SELECT query with SQL
//        query.findInBackground(new FindCallback<Inte>() {
//            public void done(int customer, ParseException e) {
//                if (e == null) {
//
//                    dlg.dismiss();
//                    Log.e("Size :  ", "" + customer+"");
//                } else {
//                    dlg.dismiss();
//                    Log.e("message", "Error Loading Orders" + e);
//                }
//            }
//        });
//
//    }
//}
