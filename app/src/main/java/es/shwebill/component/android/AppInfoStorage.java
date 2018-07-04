package es.shwebill.component.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import es.shwebill.component.util.Logger;

public class AppInfoStorage {

    private final static String PREFERENCES_NAME = "es.shwebill.AppInfoStorage";
    private final static String KEY_ALREADY_SELECTED = "es.shwebill.KEY_ALREADY_SELECTED";

    private static AppInfoStorage appInfoStorage = null;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    private AppInfoStorage(Context context) {

        this.sharedPreferences =
                context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
    }

    public synchronized static void initialize(Context context) {

        if (appInfoStorage == null) {
            appInfoStorage = new AppInfoStorage(context);
        }
    }

    public synchronized static AppInfoStorage getInstance() {

        return AppInfoStorage.appInfoStorage;
    }

    public boolean isAlreadyLanguageSelected() {
        return this.sharedPreferences.getBoolean(KEY_ALREADY_SELECTED, false);
    }

    public void languageSelected() {
        this.editor.putBoolean(KEY_ALREADY_SELECTED, true);
        this.editor.commit();
    }

    public void cleanUpAll() {
        this.editor.remove(KEY_ALREADY_SELECTED);
        this.editor.commit();
        Logger.d(this.getClass(), "AppInfoStorage.cleanUpCredentials : ");
    }
}
