package com.base.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Odey M. Khalaf <odey@devloops.net>
 */
public class ConnectionDetector {

    private final Context _context;

    private ConnectionDetector(Context context) {
        this._context = context;
    }

    public static ConnectionDetector with(Context context) {
        return new ConnectionDetector(context);
    }

    public boolean isInternetConnected(Context context) {
        boolean isWifiConnected = false;
        boolean isMobileInternetConnected = false;
        if (context != null) {
            try {

                ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                assert manager != null;
                NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
                if (activeNetwork != null) { // connected to the internet
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        // connected to wifi
                        isWifiConnected = true;
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        // connected to the mobile provider's data plan
                        isMobileInternetConnected = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Logger.d(Constants.TAG, "isInternetConnected() -> Context is null");
        }
        return isWifiConnected || isMobileInternetConnected;
    }
}