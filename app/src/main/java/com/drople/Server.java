package com.drople;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.drople.Models.Address;
import com.drople.Models.GenericOrder;
import com.drople.Models.Order;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Server extends Service {


    public static int SERVER_SAVE_ADDRESS = 1110;
    public static int SERVER_PLACE_ORDER = 1111;
    public static int SERVER_EDIT_PASSWORD = 1112;
    public static int SERVER_DONATE = 1113;
    public static int SERVER_SIGNUP = 1114;
    public static int SERVER_LOGIN = 1115;

    static ProgressDialog dialog;

    public static void setServerCallCompleteListener(ServerCallCompleteListener listener) {
        if (listener == null) dialog = null;
        mListener = listener;
    }

    private void showProgressBar() {
        Activity activity = ((Activity) mListener);
        if (mListener != null) {
//            ProgressBar progressBar = activity.findViewById(R.id.progress_bar);
//            if (progressBar != null) progressBar.setVisibility(View.VISIBLE);
//            else {
            dialog = new ProgressDialog(activity);
            dialog.show();
//            }
        }
    }

    private void notifyListener(boolean success, int methodId, String titlePos, String titleNeg, OnRetryListener retryListener) {
        if (mListener != null)
            if (success) {
                if (dialog != null) {
                    dialog.dismiss();
                    dialog = null;
                    mListener.onServerCallSuccess(methodId, null);
                } else mListener.onServerCallSuccess(methodId, titlePos);
            } else {
                if (dialog != null) dialog.dismiss();
                mListener.onServerCallFailure(methodId, titleNeg, retryListener);
            }
        //TODO send notification
        //not for auth actions
    }

    private static ServerCallCompleteListener mListener;
    private final ServerBinder mBinder = new ServerBinder();

    //method 1

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void saveAddress(String uid, Address address) {
        DatabaseReference ref =
                FirebaseDatabase.getInstance().getReference().child("Users").
                        child(uid).child("addresses");
        address.id = ref.push().getKey();
        ref.child(address.id).setValue(address).addOnCompleteListener(task -> {
            if (task.isSuccessful())
                notifyListener(true, SERVER_SAVE_ADDRESS,
                        "Address added", "", null);
            else notifyListener(false,
                    SERVER_SAVE_ADDRESS, "",
                    "Address adding error", () -> saveAddress(uid, address));
        });
    }

    public void placeOrder(Order order) {
        showProgressBar();
        String ordersKey =
                FirebaseDatabase.getInstance().getReference().child("PrevOrders").
                        child(order.uid).push().getKey();
        String prevOrderRef = "PrevOrders/" + order.uid + "/" + ordersKey;
        String orderRef = "Orders/" + ordersKey;
        order.id = ordersKey;
        order.c_date = new Date().getTime() + "";
        order.status = "0";
        Map<String, Object> map = new HashMap<>();
        map.put(prevOrderRef, order);
        map.put(orderRef, order);

        FirebaseDatabase.getInstance().getReference().updateChildren(map)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        PlaceOrderActivity.aacount = 0;
                        PlaceOrderActivity.aamount = 0;
                        notifyListener(true, SERVER_PLACE_ORDER,
                                "Order Placed", "", null);
                    } else notifyListener(false,
                            SERVER_PLACE_ORDER, "",
                            "Order placing error", () -> placeOrder(order));
                });
    }

    public void orderDonate(GenericOrder order) {
        showProgressBar();
        FirebaseDatabase.getInstance().getReference().child("Donations").push().setValue(order)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful())
                        notifyListener(true, SERVER_DONATE,
                                "Order Placed", "", null);
                    else notifyListener(false,
                            SERVER_DONATE, "",
                            "Order placing error", () -> orderDonate(order));
                });
    }

    public void deleteAddress(String uid, Address address) {
//        showProgressBar();
        DatabaseReference ref =
                FirebaseDatabase.getInstance().getReference().child("Users").
                        child(uid).child("addresses");
        ref.child(address.id).setValue(null).addOnCompleteListener(task -> {
            if (task.isSuccessful())
                notifyListener(true, SERVER_SAVE_ADDRESS,
                        "Address deleted", "", null);
            else notifyListener(false,
                    SERVER_SAVE_ADDRESS, "",
                    "Address deleting error", () -> saveAddress(uid, address));
        });
    }

    public void getAddresses(String id) {

    }


    //method 3
    public void editPassword(FirebaseUser user, String password, String newPassword) {
        showProgressBar();
        final OnRetryListener retry = () -> editPassword(user, password, newPassword);
        AuthCredential credential = EmailAuthProvider
                .getCredential(user.getEmail(), password);
        user.reauthenticate(credential)
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful())
                        notifyListener(false, SERVER_EDIT_PASSWORD,
                                "", "Authentication Error", retry);
                    else user.updatePassword(newPassword)
                            .addOnCompleteListener(task1 -> notifyListener(task1.isSuccessful(),
                                    SERVER_EDIT_PASSWORD, "Password Updated",
                                    "Password Updating Unsuccessful", retry));
                });
    }

    // method 5
    public void signUp(String name, String email, String password) {
        showProgressBar();
        FirebaseAuth.getInstance().
                createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        initProfile(name);
                    } else notifyListener(false, SERVER_SIGNUP, "",
                            "SignUp Unsuccessful", () -> signUp(name, email, password));
                });
    }

    private void initProfile(String name) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
//                    .setPhotoUri(Uri.parse(avatars[(new Random().nextInt() % 5)]))
                    .build();
            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(task ->
                            notifyListener(task.isSuccessful(), SERVER_SIGNUP, "SignUp Successful",
                                    "SignUp Unsuccessful", () -> initProfile(name)));
        } else {
            notifyListener(false, SERVER_SIGNUP, "",
                    "SignUp Unsuccessful", () -> initProfile(name));
        }
    }


    // Method 6
    public void login(String email, String password) {
        showProgressBar();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    notifyListener(task.isSuccessful(),
                            SERVER_LOGIN,
                            "Login Successful",
                            "Login Unsuccessful", () -> login(email, password));
                });
    }

    public interface OnRetryListener {
        void retryTask();
    }

    public interface ServerCallCompleteListener {
        void onServerCallSuccess(int methodId, String title);

        void onServerCallFailure(int methodId, String title, OnRetryListener retryListener);

    }

    public class ServerBinder extends Binder {
        public Server getService() {
            return Server.this;
        }
    }


}
