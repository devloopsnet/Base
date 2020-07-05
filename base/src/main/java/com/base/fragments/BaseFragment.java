package com.base.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.base.R;
import com.base.activities.BaseActivity;
import com.base.interfases.OnBackHandler;
import com.base.utils.Alerts;

public abstract class BaseFragment extends Fragment implements View.OnClickListener, OnBackHandler {
    protected View view;
    private boolean enableBackHandle = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getView();
    }

    public abstract View getView();

    public FragmentActivity getFragment() {
        return getActivity();
    }

    protected Bundle getBundle() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        return bundle;

    }

    /**
     * Sets enable back handle.
     *
     * @param enableBackHandle the enable back handle
     */
    public void setEnableBackHandle(boolean enableBackHandle) {
        this.enableBackHandle = enableBackHandle;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    /**
     * gets fragment manager
     *
     * @return FragmentManager
     */
    public FragmentManager getFragManager() {
        return getChildFragmentManager();
    }

    @Override
    public void onResume() {
        /*
          Handle BackPress on Fragment.
         */
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).setBackHandler(null);
            if (enableBackHandle)
                ((BaseActivity) getActivity()).setBackHandler(this);
        }
        super.onResume();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {

    }

    /**
     * shows message in Alert Dialog
     *
     * @param message String
     */
    public void showDialog(String message) {
        Alerts.with(getActivity()).show(getString(R.string.app_name), message, true, false, new Alerts.DialogInterface() {
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
        Alerts.with(getActivity()).showToast(message);
    }
}