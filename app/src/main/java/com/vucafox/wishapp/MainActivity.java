package com.vucafox.wishapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    private DbLoaderService dbLoaderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv_wish);
        RelativeLayout relativeLayout = (RelativeLayout) tv.getParent();
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText();
            }
        });
    }

    private void updateText() {
        String rowText = "";
        if (dbLoaderService != null) {
            rowText = dbLoaderService.getRandomPhrase();
        }
        tv.setText(rowText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(getBaseContext(), DbLoaderService.class);

        // TODO read more about Context.BIND_AUTO_CREATE!!!
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(mConnection);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            dbLoaderService = ((DbLoaderService.MyBinder) binder).getService();
            updateText();
            Toast.makeText(getApplicationContext(), "Service is connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            dbLoaderService = null;
        }
    };
}
