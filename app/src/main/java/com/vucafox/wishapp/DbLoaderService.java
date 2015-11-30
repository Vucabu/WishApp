package com.vucafox.wishapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

public class DbLoaderService extends Service {

    private MyBinder myBinder = new MyBinder();
    private DatabaseHelper myDbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        myDbHelper = new DatabaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        myDbHelper.openDataBase();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public String getRandomPhrase() {
        return myDbHelper.getRandomRow();
    }

    public class MyBinder extends Binder {
        public DbLoaderService getService() {
            return DbLoaderService.this;
        }
    }
}
