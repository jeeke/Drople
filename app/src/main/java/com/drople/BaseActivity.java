package com.drople;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements Server.ServerCallCompleteListener {
    public Server server;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    boolean mBound = false;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            server = ((Server.ServerBinder) service).getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    ProgressBar progressBar;

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
//        progressBar = findViewById(R.id.progress_bar);
        Server.setServerCallCompleteListener(this);
        Intent intent = new Intent(this, Server.class);
        startService(intent);
        bindService(intent, connection, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showProgressBar(boolean visibility) {
        if (progressBar != null)
            if (visibility) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onServerCallSuccess(int methodId, String title) {
        if (title != null) {
            showSnackBar(findViewById(android.R.id.content), title,null);
        }
        showProgressBar(false);
    }

    @Override
    public void onServerCallFailure(int methodId, String title, Server.OnRetryListener retryListener) {
        showProgressBar(false);
        showSnackBar(findViewById(android.R.id.content), title, retryListener);
    }

    public static void showSnackBar(View view, String title, Server.OnRetryListener retryListener){
        Toast.makeText(view.getContext(), title, Toast.LENGTH_SHORT).show();
    }


}
