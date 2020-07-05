package com.base.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.base.R;
import com.base.fragments.BaseFragment;
import com.base.interfases.OnBackHandler;
import com.base.utils.Alerts;
import com.base.utils.AppSession;

import java.util.Locale;

public abstract class BaseActivity extends AppCompatActivity implements
        //to identify child tasks and perform on activity itself
        View.OnClickListener {
    protected String TAG;
    private OnBackHandler backHandler;
    private BaseFragment fragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getLocalClassName();
        Configuration config;
        config = new Configuration(getResources().getConfiguration());
        config.setLocale(new Locale(AppSession.getAccept_Language().toLowerCase()));
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        setContentView(getView());

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * This method is used to initialize UI of the layout. Called in onCreate()
     */
    protected abstract View getView();

    protected final Bundle getBundle() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            bundle = new Bundle();
        }
        return bundle;

    }

    /**
     * Gets back handler.
     *
     * @return OnBackHandler
     */
    public OnBackHandler getBackHandler() {
        return backHandler;
    }

    /**
     * Sets back handler.
     *
     * @param backHandler OnBackHandler
     */
    public void setBackHandler(OnBackHandler backHandler) {
        this.backHandler = backHandler;
    }

    @Override
    public void onBackPressed() {
        if (getBackHandler() != null) {
            getBackHandler().onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Sets fragment when called another activity or application eg camera or gallery
     * After completed operation set this to null.
     *
     * @param fragment the fragment
     */
    public void setOnActivityResultFragment(BaseFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * restarts current Activity
     */
    public void restart() {
        super.recreate();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * set Status Bar Color
     *
     * @param color Color
     */
    public void setStatusBarColor(int color) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(color);
    }

    /**
     * set Status Bar Color by Hash
     *
     * @param color String
     */
    public void setStatusBarColor(String color) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(Color.parseColor(color));
    }

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
     * Shows Messages in a Toast with centered text
     *
     * @param message String
     */
    public void showMessage(String message) {
        Alerts.with(this).showToast(message);
    }
}
