package com.base.utils;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * @author Odey M. Khalaf <odey@devloops.net>
 */
public class WebAppInterface {
    private Context mContext;

    /**
     * Instantiate the interface and set the context
     */
    public WebAppInterface(Context c) {
        mContext = c;
    }


    /**
     * Show a toast from the web page
     */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
}