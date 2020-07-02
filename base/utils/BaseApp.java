package com.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.multidex.MultiDexApplication;

import java.net.CookieHandler;
import java.net.CookieManager;

public class BaseApp extends MultiDexApplication {

    @SuppressLint("StaticFieldLeak")
    private static Context ctx;
    private static String url;

    public static void setBaseUrl(String url) {
        BaseApp.url = url;
    }

    public static String getBaseUrl() {
        return url;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppSession.init(this);
        ctx = this;
        Utils.with(this).updateConfig();

        CookieHandler.setDefault(new CookieManager());
    }

    public static String getStringById(int strId) {
        return ctx.getString(strId);
    }

    @Override
    public Context getApplicationContext() {
        return this;
    }
}
