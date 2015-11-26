package com.vucafox.wishapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ForecastLoaderService extends Service {

    private MyBinder myBinder = new MyBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public class MyBinder extends Binder {
        ForecastLoaderService getService() {
            return ForecastLoaderService.this;
        }
    }
}
