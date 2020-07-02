package com.base.activities;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.base.http.ApiRequester;
import com.base.model.BaseModel;
import com.base.user.databinding.ActivitySplashBinding;
import com.base.utils.BaseApp;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected View getView() {
        binder = ActivitySplashBinding.inflate(getLayoutInflater());
        return binder.getRoot();
    }

    /**
     * force update WebCall
     */
    public void testRequest() {
        requester.getTestRequest(new ApiRequester.ApiCallback() {
            @Override
            public void onSuccess(BaseModel model) {

            }

            @Override
            public void onError(String error) {

            }
        });
    }
}