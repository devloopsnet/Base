package com.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
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
    private Map<String, String> headers;

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

    public Api addHeaders(Map<String, String> headers) {
        if (headers == null) {
            this.headers = new HashMap<>();
        } else {
            this.headers = new HashMap<>();
            this.headers.putAll(headers);
        }
        return this;
    }

    private Map<String, String> getHeaders() {
        if (headers == null) {
            return new HashMap<>();
        }
        return headers;
    }

    /**
     * Create GET request
     *
     * @param url           String
     * @param listener      Response<String>
     * @param errorListener Error
     */
    public void get(String url, Response<String> listener, Error errorListener) {
        Logger.i(Constants.TAG, "url--> " + url);
        StringRequest objRequest = new StringRequest(Request.Method.GET, url, listener::response, error ->
                errorListener.error(Utils.with(context).getError(error))) {
            @Override
            public Map<String, String> getHeaders() {
                return Api.this.getHeaders();
            }
        };
        Singleton.getInstance(context).addRequestQueue(objRequest);
    }

    /**
     * Create POST request
     *
     * @param url           String
     * @param body          Map<String, String>
     * @param listener      Response<String>
     * @param errorListener Error
     */
    public void post(String url, final Map<String, String> body, Response<String> listener, Error errorListener) {
        Logger.i(Constants.TAG, "url--> " + url);
        StringRequest request = new StringRequest(Request.Method.POST, url, listener::response, error -> errorListener.error(Utils.with(context).getError(error))) {
            @Override
            public Map<String, String> getHeaders() {
                return Api.this.getHeaders();
            }

            @Override
            protected Map<String, String> getParams() {
                return body;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        Singleton.getInstance(context).addRequestQueue(request);
    }

    /**
     * Create POST request
     *
     * @param url           String
     * @param body          JSONObject
     * @param listener      Response<JSONObject>
     * @param errorListener Error
     */
    public void post(String url, final JSONObject body, Response<JSONObject> listener, Error errorListener) {
        Logger.i(Constants.TAG, "url--> " + url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body, listener::response, error -> errorListener.error(Utils.with(context).getError(error))) {
            @Override
            public Map<String, String> getHeaders() {
                return Api.this.getHeaders();
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
     * @param url           String
     * @param body          Map<String, String> body
     * @param listener      Response<String>
     * @param errorListener Error
     */
    public void put(String url, final Map<String, String> body, Response<String> listener, Error errorListener) {
        Logger.i(Constants.TAG, "url--> " + url);
        StringRequest objRequest = new StringRequest(Request.Method.PUT, url, listener::response, error -> errorListener.error(Utils.with(context).getError(error))) {
            @Override
            public Map<String, String> getHeaders() {
                return Api.this.getHeaders();
            }

            @Override
            protected Map<String, String> getParams() {
                return body;
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
     * @param url           String
     * @param listener      Response<String>
     * @param errorListener Error
     */
    public void delete(String url, Response<String> listener, Error errorListener) {
        Logger.i(Constants.TAG, "url--> " + url);
        StringRequest objRequest = new StringRequest(Request.Method.DELETE, url, listener::response, error -> {
            errorListener.error(Utils.with(context).getError(error));
        }) {
            @Override
            public Map<String, String> getHeaders() {
                return Api.this.getHeaders();
            }
        };
        Singleton.getInstance(context).addRequestQueue(objRequest);
    }

    public interface Response<T> {
        void response(T response);
    }

    public interface Error {
        void error(String error);
    }
}