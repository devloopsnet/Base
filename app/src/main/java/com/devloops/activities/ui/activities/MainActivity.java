package com.devloops.activities.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.base.activities.BaseActivity;
import com.base.utils.Api;
import com.base.utils.Constants;
import com.base.utils.Logger;
import com.devloops.R;
import com.devloops.activities.http.ApiMethods;
import com.devloops.activities.http.ApiRequester;
import com.devloops.activities.models.BaseModel;
import com.devloops.activities.ui.fragments.BlankFragment;
import com.devloops.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binder;
    private ApiRequester requester;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    /**
     * example of launching a fragment in the activity
     */
    private void attachFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, BlankFragment.newInstance())
                .addToBackStack("blank-fragment")
                .commit();
    }

    /**
     * example making http request using volley
     */
    private void getExampleRequestVolley() {
        Map<String, String> headers = new HashMap<>();

        Api.with(context).addHeaders(headers).get("",
                response -> {

                }, error -> {

                });
    }

    /**
     * example making http request using Retrofit
     */
    private void getExampleRequestRetrofit() {
        //initialize your requester
        requester = ApiRequester.getInstance(context);

        //send request
        requester.getExampleRequest(new ApiRequester.ApiCallback() {
            @Override
            public void onSuccess(BaseModel model, String method) {
                //handle results
                Logger.i(Constants.TAG, "success");
            }

            @Override
            public void onError(String error) {
                //log errors
                Logger.i(Constants.TAG, error);
            }
        }, ApiMethods.Methods.EXAMPLE_PATH);
    }

    @Override
    protected View getView() {
        binder = ActivityMainBinding.inflate(getLayoutInflater());
        return binder.getRoot();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStackImmediate();
        else super.onBackPressed();
    }
}