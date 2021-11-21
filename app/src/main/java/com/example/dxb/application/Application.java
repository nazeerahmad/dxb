package com.example.dxb.application;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class Application extends android.app.Application {
    private static Application mInstance;

    public static synchronized Application getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

//        AppSignatureHelper appSignatureHelper = new AppSignatureHelper(this);
//        appSignatureHelper.getAppSignatures();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public static boolean hasNetwork(){
        return mInstance.isNetworkConnected();
    }

    private boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
