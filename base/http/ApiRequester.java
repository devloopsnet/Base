package com.base.http;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.base.model.BaseModel;
import com.base.utils.Constants;
import com.base.utils.Logger;

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

    public void getTestRequest(final ApiCallback apiCallback) {
        apiMethods.getTestRequest().enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(@NonNull Call<BaseModel> call, @NonNull Response<BaseModel> response) {
                apiCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<BaseModel> call, @NonNull Throwable t) {
                apiCallback.onError(t.toString());
                Logger.i(Constants.TAG, t.toString());
            }
        });
    }

    public abstract static class ApiCallback {
        public abstract void onSuccess(BaseModel model);

        public abstract void onError(String error);
    }
}