package com.base.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Odey M. Khalaf <odey@devloops.net>
 */
public class DateTimeFormatter {

    public static String getHrViewFormmate(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mma", Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getDateViewFormmate(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getDateViewFormateFromWS(String wsDate) {
        SimpleDateFormat simpleDateFormatWS = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = simpleDateFormatWS.parse(wsDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (date != null)
            return simpleDateFormat.format(date.getTime());
        else
            return "";
    }

    public static String getDateWsFormateFromView(String wsDate) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormatWS = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = simpleDateFormatWS.parse(wsDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        if (date != null)
            return simpleDateFormat.format(date.getTime());
        else
            return "";
    }

    public static boolean validPastDate(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mma", Locale.ENGLISH);
        Calendar calendarNow = Calendar.getInstance();
        try {
            if (simpleDateFormat.parse(string).compareTo(calendarNow.getTime()) <= 0)
                return true;
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return false;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateAndTimeWsFormateFromView(String s) {
        SimpleDateFormat simpleDateFormatWS = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        Date date = null;
        try {
            date = simpleDateFormatWS.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd KK:mm:00", Locale.ENGLISH);
        if (date != null)
            return simpleDateFormat.format(date.getTime());
        else
            return "";

    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateAndTimeWsFormateFromWs(String s) {
        SimpleDateFormat simpleDateFormatWS = new SimpleDateFormat("yyyy-MM-dd KK:mm:00", Locale.ENGLISH);
        Date date = null;
        try {
            date = simpleDateFormatWS.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        if (date != null)
            return simpleDateFormat.format(date.getTime());
        else
            return "";

    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateViewFormateFromWsDateTime(String s) {
        SimpleDateFormat simpleDateFormatWS = new SimpleDateFormat("yyyy-MM-dd HH:mm:00", Locale.ENGLISH);
        Date date = null;
        try {
            date = simpleDateFormatWS.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (date != null)
            return simpleDateFormat.format(date.getTime());
        else
            return "";
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTimeViewFormateFromWsDateTime(String s) {
        SimpleDateFormat simpleDateFormatWS = new SimpleDateFormat("yyyy-MM-dd HH:mm:00", Locale.ENGLISH);
        Date date = null;
        try {
            date = simpleDateFormatWS.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);
        if (date != null)
            return simpleDateFormat.format(date.getTime());
        else
            return "";
    }

    @SuppressLint("SimpleDateFormat")
    public static String getWsExpiryDateFromViewDate(String s, String m) {

        SimpleDateFormat simpleDateFormatView = new SimpleDateFormat("yyyy/MM");
        Date date = null;
        try {
            date = simpleDateFormatView.parse(m);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm/yy", Locale.ENGLISH);
        if (date != null)
            return simpleDateFormat.format(date.getTime());
        else
            return "";
    }

    @SuppressLint("SimpleDateFormat")
    public static String combineDateAndTime(int date, int month, int year, int hourOfDay, int minute) {
        String tempDate = (date < 10 ? "0" + date : date) + "/" + (month < 10 ? "0" + (month + 1) : (month + 1)) + "/" + year + " " + (hourOfDay < 10 ? "0" + hourOfDay + ":" + (minute < 10 ? "0" + minute : minute) : hourOfDay + ":" + (minute < 10 ? "0" + minute : minute));
        SimpleDateFormat simpleDateFormatWS = new SimpleDateFormat("dd/MM/yyyy KK:mm");
        Date dated = null;
        try {
            dated = simpleDateFormatWS.parse(tempDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        if (dated != null)
            return simpleDateFormat.format(dated.getTime());
        else
            return "";
    }

    public static Date getDateFromWsString(String date_of_birth) {
        SimpleDateFormat simpleDateFormatWS = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = simpleDateFormatWS.parse(date_of_birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    @SuppressLint("SimpleDateFormat")
    public static String getStringDateFromData(int day, int month, int year) {
        //(day < 10 ? "0" + day : "" + day) + "/" + (month < 10 ? "0" + month : "" + month) + "/" + year;
        SimpleDateFormat simpleDateFormatWS = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return simpleDateFormatWS.format(calendar.getTime());
    }
}
