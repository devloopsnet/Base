package com.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * @author Odey M. Khalaf <odey@devloops.net>
 */
public class Singleton {
    @SuppressLint("StaticFieldLeak")
    private static Singleton mInstance;
    @SuppressLint("StaticFieldLeak")
    private static Context mCtx;
    private RequestQueue requestQueue;

    private Singleton(Context context) {
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized Singleton getInstance(Context context) {
        if (mInstance == null)
            mInstance = new Singleton(context);
        return mInstance;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        requestQueue.getCache().clear();
        return requestQueue;
    }

    public <T> void addRequestQueue(Request<T> request) {
        requestQueue.add(request);
    }
}