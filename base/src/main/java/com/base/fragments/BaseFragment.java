package com.base.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.base.R;
import com.base.utils.Alerts;

public abstract class BaseFragment extends Fragment {
    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getView();
        activity = getActivity();
        return view;
    }

    public abstract View getView();

    public Activity getFragment() {
        return activity;
    }

    /**
     * shows message in Alert Dialog
     *
     * @param message String
     */
    public void showDialog(String message) {
        Alerts.with(activity).show(getString(R.string.app_name), message, true, false, new Alerts.DialogInterface() {
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
        Alerts.with(activity).showToast(message);
    }
}