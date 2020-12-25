package com.base.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.base.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * @author Odey M. Khalaf <odey@devloops.net>
 */
public class Utils {
    @SuppressLint("StaticFieldLeak")
    private static Utils instance;
    private Context mContext;

    public Utils() {
    }

    public static synchronized Utils with(Context context) {
        if (instance == null)
            instance = new Utils();
        instance.setContext(context);
        return instance;
    }

    /**
     * sets last used Context
     *
     * @param mContext Context
     */
    private void setContext(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * get formatted date-time
     *
     * @param selectedDate yyyy-MM-dd hh:mm:ss
     * @return String MMM dd, yyyy : KK:mm a
     */
    @SuppressLint("SimpleDateFormat")
    public static String getFormattedDateTime(String selectedDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date newDate = null;
        try {
            newDate = format.parse(selectedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        format = new SimpleDateFormat("MMM dd, yyyy : KK:mm a");

        return format.format(Objects.requireNonNull(newDate));
    }

    /**
     * updates locale configurations
     */
    public void updateConfig() {
        Resources res = mContext.getResources();
        android.content.res.Configuration conf = res.getConfiguration();
        Locale locale = new Locale(AppSession.getAccept_Language().toLowerCase());
        Locale.setDefault(locale);
        conf.setLocale(locale);
        conf.locale = locale;
        res.updateConfiguration(conf, res.getDisplayMetrics());
    }

    /**
     * get formatted time
     *
     * @param strCurrentDate yyyy-MM-dd hh:mm:ss
     * @return String KK:mm a
     */
    @SuppressLint("SimpleDateFormat")
    public static String getFormattedTime(String strCurrentDate) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date newDate = null;
        try {
            newDate = format.parse(strCurrentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        format = new SimpleDateFormat("KK:mm a");

        return format.format(Objects.requireNonNull(newDate));
    }

    /**
     * get formatted date
     *
     * @param strCurrentDate yyyy-mm-dd
     * @return String MMM dd, yyyy
     */
    @SuppressLint("SimpleDateFormat")
    public static String getFormattedDate(String strCurrentDate) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        Date newDate = null;
        try {
            newDate = format.parse(strCurrentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        format = new SimpleDateFormat("MMM dd, yyyy | hh:mm");

        return format.format(Objects.requireNonNull(newDate));
    }

    /**
     * call phone number intent
     *
     * @param number phone number
     */
    public void callNumber(String number) {
        String uri = "tel:" + number.trim();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
        mContext.startActivity(intent);
    }

    /**
     * launch google maps from current Location to a specific destination
     *
     * @param lat   latitude
     * @param longi longitude
     */
    public void openMapsCurrentToDestination(String lat, String longi) {
        String uri = "https://maps.google.com/maps?daddr=" + lat + "," + longi + "&z=14";
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mContext.startActivity(mapIntent);
    }

    /**
     * launch email app to send email
     *
     * @param address email address
     */
    public void sendEmail(String address) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + address)); // only email apps should handle this
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        }
    }

    /**
     * start a link intent
     *
     * @param url destination url
     */
    public void openLink(String url) {
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra("url", url);
        mContext.startActivity(intent);
    }

    /**
     * start a link intent
     *
     * @param url destination url
     */
    public void openLinkOut(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        mContext.startActivity(intent);
    }

    /**
     * launches twitter profile in app if installed otherwise launches in browser
     *
     * @param userName twitter user name
     */
    public void startTwitter(String userName) {
        Intent intent;
        try {
            // get the Twitter app if possible
            mContext.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=<" + userName + ">"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/<" + userName + ">"));
        }
        mContext.startActivity(intent);
    }

    public static String prepareHtmlBody(String body) {
        String html = "<!DOCTYPE html>";
        html += "<html>";
        html += "<head>";
        html += "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";
        html += "</head>";
        html += "<body>";
        html += body;
        html += "</body>";
        return html;
    }

    /**
     * launches linkedIn profile page
     *
     * @param linkedId userName
     */
    public void openLinkedIn(String linkedId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://add/%@" + linkedId));
        final PackageManager packageManager = mContext.getPackageManager();
        final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.isEmpty())
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=" + linkedId));
        mContext.startActivity(intent);
    }

    /**
     * Encodes File to Base64 String
     *
     * @param yourFile File
     * @return String
     */
    public static String encodeFileToBase64(File yourFile) {
        int size = (int) yourFile.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(yourFile));
            //noinspection ResultOfMethodCallIgnored
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    /**
     * convert video to bute[]
     *
     * @param selectedImagePath selectedImagePath
     * @return byte[]
     */
    public static byte[] convertVideo(String selectedImagePath) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(selectedImagePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] buf = new byte[1024];
        int n;
        try {
            if (fis != null) {
                while (-1 != (n = fis.read(buf)))
                    baos.write(buf, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    /**
     * save bitmap to a file
     *
     * @param bmp bitmap
     * @return File
     * @throws IOException IOException
     */
    public static File saveBitmap(Bitmap bmp) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
        File f = new File(Environment.getExternalStorageDirectory()
                + File.separator + System.currentTimeMillis() + "_image.jpg");
        //noinspection ResultOfMethodCallIgnored
        f.createNewFile();
        FileOutputStream fo = new FileOutputStream(f);
        fo.write(bytes.toByteArray());
        fo.close();
        return f;
    }

    /**
     * Turn bitmap into byte array.
     *
     * @param bitmap file from storage
     * @return byte array
     */
    public static byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, stream);
        return stream.toByteArray();
    }

    /**
     * get Bitmap from file path
     *
     * @param path image file path
     * @return Bitmap image
     */
    public Bitmap getBitmap(String path) {
        File sd = Environment.getExternalStorageDirectory();
        File imageFile = new File(sd + path);
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        return BitmapFactory.decodeFile(imageFile.getAbsolutePath(), bmOptions);
    }

    /**
     * turn byte[] into Base64 String format
     *
     * @param decodedImage image in bytes
     * @return Base64 String
     */
    public static String encodeTOBase64String(byte[] decodedImage) {
        return Base64.encodeToString(decodedImage, Base64.DEFAULT);
    }

    /**
     * parse volley error logs the error
     *
     * @param error volleyError
     */
    public String getError(VolleyError error) {
        String errorMessage = "";
        String errorCode;
        NetworkResponse response = error.networkResponse;
        if (response != null && response.data != null) {
            errorMessage = new String(response.data, StandardCharsets.UTF_8);
            errorCode = String.valueOf(response.statusCode);
            Logger.e(Constants.TAG, "code: " + errorCode + " message: " + errorMessage);
        }
        return errorMessage;
    }

    /**
     * Animation for View
     *
     * @param view     View
     * @param duration time in milli second
     */
    public void animateSlideDown(View view, int duration) {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_down);
        animation.setDuration(duration);
        view.setAnimation(animation);
        view.animate();
        animation.start();
    }

    /**
     * Animation for View
     *
     * @param view     View
     * @param duration time in milli second
     */
    public void animateSlideUp(View view, int duration) {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_up);
        animation.setDuration(duration);
        view.setAnimation(animation);
        view.animate();
        animation.start();
    }

    /**
     * Animation for View
     *
     * @param view     View
     * @param duration time in milli second
     */
    public void animateSlideInLeft(View view, int duration) {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_left);
        animation.setDuration(duration);
        view.setAnimation(animation);
        view.animate();
        animation.start();
    }

    /**
     * Animation for View
     *
     * @param view     View
     * @param duration time in milli second
     */
    public void animateSlideOutLeft(View view, int duration) {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_out_left);
        animation.setDuration(duration);
        view.setAnimation(animation);
        view.animate();
        animation.start();
    }

    /**
     * Animation for View
     *
     * @param view     View
     * @param duration time in milli second
     */
    public void animateSlideInRight(View view, int duration) {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_right);
        animation.setDuration(duration);
        view.setAnimation(animation);
        view.animate();
        animation.start();
    }

    /**
     * Animation for View
     *
     * @param view     View
     * @param duration time in milli second
     */
    public void animateSlideOutRight(View view, int duration) {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_out_right);
        animation.setDuration(duration);
        view.setAnimation(animation);
        view.animate();
        animation.start();
    }

    /**
     * Animation for View
     *
     * @param view     View
     * @param duration time in milli second
     */
    public void animateFadeIn(View view, int duration) {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.fade_in);
        animation.setDuration(duration);
        view.setAnimation(animation);
        view.animate();
        animation.start();
    }

    /**
     * Animation for View
     *
     * @param view     View
     * @param duration time in milli second
     */
    public void animateFadeOut(View view, int duration) {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.fade_out);
        animation.setDuration(duration);
        view.setAnimation(animation);
        view.animate();
        animation.start();
    }

    /**
     * animate recyclerView item
     *
     * @param view     itemView
     * @param duration int
     */
    public void setScaleAnimation(View view, int duration) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(duration);
        view.startAnimation(anim);
    }

    /**
     * Checks if the app is in background or not
     */
    public boolean isAppIsInBackground() {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        try {
            assert am != null;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
                for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                    if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        for (String activeProcess : processInfo.pkgList)
                            if (activeProcess.equals(mContext.getPackageName()))
                                isInBackground = false;
                    }
                }
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        return isInBackground;
    }

    /**
     * open google play store if installed
     */
    public void launchGooglePlay(String packageName) {
        try {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
        }
    }
}