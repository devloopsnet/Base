package com.base.activities;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.base.user.R;
import com.base.http.ApiRequester;
import com.base.utils.Alerts;
import com.base.utils.AppSession;

import java.util.Locale;

public abstract class BaseActivity extends AppCompatActivity {
    protected ApiRequester requester;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration config;
        config = new Configuration(getResources().getConfiguration());
        config.setLocale(new Locale(AppSession.getAccept_Language().toLowerCase()));
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        setContentView(getView());

        requester = ApiRequester.getInstance(this);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    protected abstract View getView();

    /**
     * shows message in Alert Dialog
     *
     * @param message String
     */
    public void showDialog(String message) {
        Alerts.with(this).show(getString(R.string.app_name), message, true, false, new Alerts.DialogInterface() {
            @Override
            public void onPositiveClick(Dialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void onNegativeClick(Dialog dialog) {

            }
        });
    }

    /**
     * Shows Messages in a Toast
     *
     * @param message String
     */
    public void showMessage(String message) {
        Alerts.with(this).showToast(message);
    }
}
