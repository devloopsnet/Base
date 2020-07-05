package com.base.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.base.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * Custom Alerts Helper Class
 *
 * @author Odey M. Khalaf <odey@devloops.net>
 */
public class Alerts {
    private Dialog dialog;
    private Context mContext;
    public static final int SNAKE_BAR_SHORT = -1;
    public static final int SNAKE_BAR_LONG = 0;

    public static final int TOAST_SHORT = 0;
    public static final int TOAST_LONG = 1;

    public Alerts() {
    }

    public static Alerts with(Context context) {
        Alerts alerts = new Alerts();
        alerts.mContext = context;
        return alerts;
    }

    /**
     * shows centered text
     *
     * @param text text
     */
    public void showToast(String text) {
        Spannable centeredText = new SpannableString(text);
        centeredText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                0, text.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        Toast.makeText(mContext, centeredText, Toast.LENGTH_SHORT).show();
    }

    /**
     * shows centered text
     *
     * @param text     text
     * @param duration int
     */
    public void showToast(String text, int duration) {
        Spannable centeredText = new SpannableString(text);
        centeredText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                0, text.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        Toast.makeText(mContext, centeredText, duration).show();
    }

    /**
     * Custom dialog with title, message and buttons
     *
     * @param title           String
     * @param body            String
     * @param isHasPositive   boolean
     * @param isHasNegative   boolean
     * @param dialogInterface DialogInterface
     */
    public void show(String title, String body, boolean isHasPositive, boolean isHasNegative, final DialogInterface dialogInterface) {
        try {
            if (dialog != null)
                if (dialog.isShowing())
                    dialog.dismiss();
        } catch (Exception x) {
            x.printStackTrace();
        }

        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.base_dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        TextView txtTitle = dialog.findViewById(R.id.txt_title);
        TextView txtBody = dialog.findViewById(R.id.txt_body);
        Button btnPositive = dialog.findViewById(R.id.btn_positive);
        Button btnNegative = dialog.findViewById(R.id.btn_negative);

        if (!Validator.isEmptyOrNull(title))
            txtTitle.setText(title);
        else {
            txtTitle.setText(mContext.getString(R.string.app_name));
        }

        if (!Validator.isEmptyOrNull(body))
            txtBody.setText(body);
        else
            txtBody.setVisibility(View.GONE);

        if (isHasPositive) {
            btnPositive.setVisibility(View.VISIBLE);
        } else btnPositive.setVisibility(View.GONE);


        if (isHasNegative) {
            btnNegative.setVisibility(View.VISIBLE);
        } else btnNegative.setVisibility(View.GONE);

        btnPositive.setOnClickListener(v -> {
            if (dialogInterface != null)
                dialogInterface.onPositiveClick(dialog);

        });
        btnNegative.setOnClickListener(v -> {
            if (dialogInterface != null)
                dialogInterface.onNegativeClick(dialog);
        });

        if (dialog != null)
            dialog.show();
    }

    /**
     * Custom dialog with title, message and customizable titles for buttons
     *
     * @param title           String
     * @param body            String
     * @param isHasPositive   boolean
     * @param positive        String
     * @param isHasNegative   boolean
     * @param negative        String
     * @param dialogInterface DialogInterface
     */
    public void show(String title, String body, boolean isHasPositive, String positive, boolean isHasNegative, String negative, final DialogInterface dialogInterface) {
        try {
            if (dialog != null)
                if (dialog.isShowing())
                    dialog.dismiss();
        } catch (Exception x) {
            x.printStackTrace();
        }

        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.base_dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        TextView txtTitle = dialog.findViewById(R.id.txt_title);
        TextView txtBody = dialog.findViewById(R.id.txt_body);
        Button btnPositive = dialog.findViewById(R.id.btn_positive);
        Button btnNegative = dialog.findViewById(R.id.btn_negative);

        if (!Validator.isEmptyOrNull(title))
            txtTitle.setText(title);
        else {
            txtTitle.setText(mContext.getString(R.string.app_name));
        }

        if (!Validator.isEmptyOrNull(body))
            txtBody.setText(body);
        else
            txtBody.setVisibility(View.GONE);

        if (isHasPositive) {
            btnPositive.setVisibility(View.VISIBLE);
            btnPositive.setText(positive);
        } else btnPositive.setVisibility(View.GONE);

        if (isHasNegative) {
            btnNegative.setVisibility(View.VISIBLE);
            btnNegative.setText(negative);
        } else btnNegative.setVisibility(View.GONE);

        btnPositive.setOnClickListener(v -> {
            if (dialogInterface != null)
                dialogInterface.onPositiveClick(dialog);

        });
        btnNegative.setOnClickListener(v -> {
            if (dialogInterface != null)
                dialogInterface.onNegativeClick(dialog);
        });

        if (dialog != null)
            dialog.show();
    }

    /**
     * shows Snake Bar with message only
     *
     * @param mainView View
     * @param message  String
     * @param duration int
     */
    public void showSnakeBar(View mainView, String message, int duration) {
        Snackbar.make(mainView, message, Snackbar.LENGTH_LONG).setDuration(duration).show();
    }

    /**
     * shows Snake Bar with message only
     *
     * @param mainView   View
     * @param message    String
     * @param actionText String
     * @param duration   int
     */
    public void showSnakeBar(View mainView, String message, String actionText, int duration) {
        Snackbar.make(mainView, message, Snackbar.LENGTH_LONG)
                .setAction(actionText, null)
                .setDuration(duration)
                .show();
    }

    /**
     * shows Snake Bar with acton callback
     *
     * @param mainView            View
     * @param message             String
     * @param textColorHash       String
     * @param actionText          String
     * @param actionTextColorHash String
     * @param duration            int
     * @param callback            Callback
     */
    public void showSnakeBar(View mainView, String message, String textColorHash, String actionText, String actionTextColorHash, String backGroundColor, int duration, Callback callback) {
        Snackbar.make(mainView, message, Snackbar.LENGTH_LONG)
                .setAction(actionText, callback::onActionClicked)
                .setBackgroundTint(Color.parseColor(backGroundColor))
                .setActionTextColor(Color.parseColor(actionTextColorHash))
                .setTextColor(Color.parseColor(textColorHash))
                .setDuration(duration)
                .show();
    }

    public interface Callback {
        void onActionClicked(View view);
    }

    public interface DialogInterface {
        void onPositiveClick(Dialog dialog);

        void onNegativeClick(Dialog dialog);
    }
}