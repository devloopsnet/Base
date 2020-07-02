package com.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${OdeyFox} on 10/8/2018.
 * dependency  implementation 'dev.dworks.libs:volleyplus:+'
 *
 * @author Odey M. Khalaf <odey.khalaf@gmail.com>
 */
public class Api {
    @SuppressLint("StaticFieldLeak")
    private static Context context = null;

    private static boolean isMultipart = false;

    private Api() {

    }

    /**
     * gets the context
     *
     * @param mContext app context
     */
    public static Api with(Context mContext) {
        context = mContext;
        return new Api();
    }

    /**
     * creates the headers for API calls
     *
     * @param requiresLogin true if api call requires login
     * @return HashMap of headers
     */
    public static Map<String, String> createHeaders(boolean requiresLogin) {
        Map<String, String> headers = new HashMap<>();
        if (requiresLogin)
            if (!isMultipart) {
                headers.put(Constants.ACCEPT, Constants.CONTENT_TYPE);
                headers.put(Constants.Authorization, AppSession.getUserToken());
                headers.put("Content-Encoding", "gzip");
            } else {
                headers.put(Constants.ACCEPT, Constants.MULTIPART_CONTENT_TYPE);
                headers.put(Constants.Authorization, AppSession.getUserToken());
                headers.put("Content-Encoding", "gzip");
            }
        else {
            headers.put(Constants.ACCEPT, Constants.CONTENT_TYPE);
        }
        Logger.i(Constants.TAG, "Headers: " + headers.toString());
        return headers;
    }

    /**
     * Create GET request
     *
     * @param url           Desired URL
     * @param listener      Success callback
     * @param errorListener Error callback
     * @param requiresLogin true if login is required
     */
    public void get(String url, Response.Listener<String> listener, Response.ErrorListener errorListener, final boolean requiresLogin) {
        isMultipart = false;
        Logger.i(Constants.TAG, "url--> " + url);
        StringRequest objRequest = new StringRequest(Request.Method.GET, url, listener, errorListener) {
            @Override
            public Map<String, String> getHeaders() {
                return createHeaders(requiresLogin);
            }
        };
        Singleton.getInstance(context).addRequestQueue(objRequest);
    }

    /**
     * Create POST request
     *
     * @param url           Desired URL
     * @param body          params
     * @param listener      response listener
     * @param errorListener error listener
     * @param requiresLogin true if login is required
     */
    public void post(String url, final Map<String, String> body, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, final boolean requiresLogin) {
        isMultipart = false;
        Logger.i(Constants.TAG, "url--> " + url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(body), listener, errorListener) {
            @Override
            public Map<String, String> getHeaders() {
                return createHeaders(requiresLogin);
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        Singleton.getInstance(context).addRequestQueue(request);
    }

    /**
     * Create PUT request
     *
     * @param url           Desired URL
     * @param body          Map<String, String> body
     * @param listener      Success callback
     * @param errorListener Error callback
     * @param requiresLogin true if login is required
     */
    public void put(String url, final Map<String, String> body, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, final boolean requiresLogin) {
        isMultipart = false;
        Logger.i(Constants.TAG, "url--> " + url);
        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(body), listener, errorListener) {
            @Override
            public Map<String, String> getHeaders() {
                return createHeaders(requiresLogin);
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        Singleton.getInstance(context).addRequestQueue(objRequest);
    }

    /**
     * Create DELETE request
     *
     * @param url           Desired URL
     * @param listener      Success callback
     * @param errorListener Error callback
     * @param requiresLogin true if login is required
     */
    public void delete(String url, Response.Listener<String> listener, Response.ErrorListener errorListener, final boolean requiresLogin) {
        isMultipart = false;
        Logger.i(Constants.TAG, "url--> " + url);
        StringRequest objRequest = new StringRequest(Request.Method.DELETE, url, listener, errorListener) {
            @Override
            public Map<String, String> getHeaders() {
                return createHeaders(requiresLogin);
            }
        };
        Singleton.getInstance(context).addRequestQueue(objRequest);
    }
}