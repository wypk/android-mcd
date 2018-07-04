package es.shwebill.component.util;


import android.util.Log;

import java.util.Date;

import es.shwebill.BuildConfig;

public class Logger {

    private static final String TAG = "Exam-Result";

    public static void log(String message) {

        if (BuildConfig.DEBUG) {
            Log.d(Logger.TAG, "thread [" + Thread.currentThread().getName() + "] - " + message);
        }
    }

    public static void d(Class<?> clazz, String log) {

        if (BuildConfig.DEBUG) {
            Log.d(TAG, clazz.getName() + " : " + Thread.currentThread().getName() + " - "
                    + (new Date()).toString() + " >>> " + log);
        }
    }
}
