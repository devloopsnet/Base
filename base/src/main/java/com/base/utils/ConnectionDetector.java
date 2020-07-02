package com.base.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.net.NetworkInfo.State.CONNECTED;

/**
 * @author Odey M. Khalaf <odey@devloops.net>
 */
public class ConnectionDetector {

    private Context _context;

    private ConnectionDetector(Context context) {
        this._context = context;
    }

    public static ConnectionDetector with(Context context) {
        return new ConnectionDetector(context);
    }

    public boolean isConnectedToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            for (NetworkInfo anInfo : info)
                if (anInfo.getState() == CONNECTED) return true;
        }
        return false;
    }
}