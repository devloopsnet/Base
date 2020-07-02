package com.devloops.activities.application;

import com.base.utils.BaseApp;

public class Application extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        BaseApp.setBaseUrl("https://devloops.net/apis/");
    }
}
