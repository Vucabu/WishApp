package com.vucafox.wishapp;

import android.app.IntentService;
import android.content.Intent;

import java.io.IOException;

public class DbIntentService extends IntentService {

    private DatabaseHelper myDbHelper;

    public DbIntentService(String name) {
        super(name);
    }

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
    protected void onHandleIntent(Intent intent) {
        String redord = myDbHelper.getRandomRow();
    }
}
