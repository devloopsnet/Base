package com.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.base.model.UserModel;


/**
 * @author Odey M. Khalaf <odey.khalaf@gmail.com>
 */
public class AppSession {

    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;

    private AppSession() {
    }

    public static void init(Context context) {
        prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
    }

    /**
     * puts a string in sharedPreferences
     *
     * @param name  name
     * @param value value
     * @auther Odey M. Khalaf on 12/25/18
     */
    public static void putString(String name, String value) {
        editor = prefs.edit();
        editor.putString(name, value);
        editor.apply();
    }

    /**
     * puts integer in sharedPreferences
     *
     * @param name  name
     * @param value value
     * @auther Odey M. Khalaf on 12/25/18
     */
    public static void putInt(String name, int value) {
        editor = prefs.edit();
        editor.putInt(name, value);
        editor.apply();
    }

    /**
     * puts a bool in sharedPreferences
     *
     * @param name  name
     * @param value value
     * @auther Odey M. Khalaf on 12/25/18
     */
    public static void putBoolean(String name, boolean value) {
        editor = prefs.edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    /**
     * gets a string from sharedPreferences
     * without default value
     *
     * @param name name
     * @return saved value
     * @auther Odey M. Khalaf on 12/25/18
     */
    public static String getString(String name) {
        return prefs.getString(name, "");
    }

    /**
     * gets a string from sharedPreferences
     * with a default value
     *
     * @param name name
     * @return saved value
     * @auther Odey M. Khalaf on 12/25/18
     */
    public static String getString(String name, String defaultValue) {
        return prefs.getString(name, defaultValue);
    }

    /**
     * gets int from sharedPreferences
     *
     * @param name name
     * @return saved value
     * @auther Odey M. Khalaf on 12/25/18
     */
    public static int getInt(String name) {
        return prefs.getInt(name, 0);
    }

    /**
     * gets a bool from sharedPreferences
     *
     * @param name name
     * @return saved value
     * @auther Odey M. Khalaf on 12/25/18
     */
    public static boolean getBoolean(String name) {
        return prefs.getBoolean(name, false);
    }

    /**
     * puts a bool in sharedPreferences
     *
     * @param name  name
     * @param value value
     * @auther Odey M. Khalaf on 12/25/18
     */
    public static void setFirstTimeLaunch(String name, boolean value) {
        editor = prefs.edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    /**
     * gets a bool from sharedPreferences
     *
     * @param name name
     * @return saved value
     * @auther Odey M. Khalaf on 12/25/18
     */
    public static boolean getFirstTimeLaunch(String name) {
        return prefs.getBoolean(name, false);
    }

    /**
     * clears Shared preferences
     *
     * @auther Odey M. Khalaf on 12/25/18
     */
    public static void clearAllPrefs() {
        editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * get updated firebase token
     *
     * @return String
     */
    public static String getUserToken() {
        return AppSession.getString("user-token");
    }

    /**
     * saves user access token to prefs
     *
     * @param token String
     */
    public static void setUserToken(String token) {
        AppSession.putString("user-token", token);
    }

    /**
     * get updated firebase token
     *
     * @return String
     */
    public static String getFirebaseToken() {
        return AppSession.getString("token");
    }

    /**
     * saves firebase token to prefs
     *
     * @param token String
     */
    public static void setFirebaseToken(String token) {
        AppSession.putString("token", token);
    }

    /**
     * get user from prefs and serialize
     *
     * @return UserModel
     */
    public static UserModel getUser() {
        String user = AppSession.getString(Constants.USER);
        return new Gson().fromJson(user, UserModel.class);
    }

    /**
     * save user to prefs
     *
     * @param user String
     */
    public static void setUser(String user) {
        AppSession.putString(Constants.USER, user);
    }

    /**
     * get user login
     *
     * @return boolean
     */
    public static boolean getLoggedIn() {
        return AppSession.getBoolean(Constants.LOGGED_IN);
    }

    /**
     * set user login
     */
    public static void setLoggedIn(boolean boo) {
        AppSession.putBoolean(Constants.LOGGED_IN, boo);
    }

    /**
     * gets user language from prefs
     *
     * @return String
     */
    public static String getAccept_Language() {
        return AppSession.getString(Constants.ACCEPT_LANGUAGE, "en");
    }

    /**
     * saves app language in prefs
     *
     * @param accept_Language String
     */
    public static void setAccept_Language(String accept_Language) {
        AppSession.putString(Constants.ACCEPT_LANGUAGE, accept_Language);
    }
}