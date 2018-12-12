package com.bedel.app.kamerbiz;


import android.app.Application;

public class KamerBizApplication extends Application {
    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static String SERVER_BASE_URL = "";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void initSession() {
        SERVER_BASE_URL = getResources().getString(R.string.server_base_url);
    }
}
