package com.devloops.activities.http;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.base.http.ApiClient;
import com.base.utils.Constants;
import com.base.utils.Logger;
import com.devloops.activities.models.BaseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Odey M. Khalaf <odey@devloops.net>
 */
public class ApiRequester {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static ApiRequester instance;
    private ApiMethods apiMethods;

    private ApiRequester(Context context) {
        mContext = context;
        apiMethods = ApiClient.getClient().create(ApiMethods.class);
    }

    public static synchronized ApiRequester getInstance(Context context) {
        if (instance == null) {
            return instance = new ApiRequester(context);
        } else {
            return instance;
        }
    }

    /**
     * Example GET Request
     *
     * @param apiCallback ApiCallback
     */
    public void getExampleRequest(final ApiCallback apiCallback, String method) {
        apiMethods.getExampleRequest().enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(@NonNull Call<BaseModel> call, @NonNull Response<BaseModel> response) {
                handleResponse(apiCallback, method, response);
            }

            @Override
            public void onFailure(@NonNull Call<BaseModel> call, @NonNull Throwable t) {
                handleError(apiCallback, t);
            }
        });
    }

    /**
     * @param apiCallback ApiCallback
     * @param t           Throwable
     */
    private void handleError(ApiCallback apiCallback, Throwable t) {
        apiCallback.onError(t.toString());
        Logger.i(Constants.TAG, t.toString());
    }

    /**
     * @param apiCallback ApiCallback
     * @param method      String
     * @param response    Response<? extends BaseModel>
     */
    private void handleResponse(ApiCallback apiCallback, String method, Response<? extends BaseModel> response) {
        Logger.i(Constants.TAG, response.toString());
        if (response.code() == 200) {
            apiCallback.onSuccess(response.body(), method);
        } else if (response.code() == 404) {
            apiCallback.onError("Not Found!");
        } else if (response.code() == 401) {
            apiCallback.onError("Not Authorized");
        } else if (response.code() == 403) {
            apiCallback.onError("Forbidden");
        } else if (response.code() == 500) {
            apiCallback.onError("Internal Server Error");
        } else if (response.code() == 503) {
            apiCallback.onError("Service Un available");
        } else if (response.code() == 502) {
            apiCallback.onError("Bad Gateway");
        } else if (response.code() == 504) {
            apiCallback.onError("Gateway TimeOut");
        } else {
            apiCallback.onError(response.message());
        }
    }

    public interface ApiCallback {
        void onSuccess(BaseModel model, String method);

        void onError(String error);
    }
}