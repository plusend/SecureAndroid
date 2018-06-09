package com.plusend.secure;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("test", String.valueOf(SignUtil.getSignature(application)));
                SignUtil.checkSign(application);
            }
        }).start();
    }
}
