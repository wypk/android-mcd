package es.shwebill.component.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import es.shwebill.component.util.Logger;


public class UserInfoStorage {

    private final static String PREFERENCES_NAME = "es.shwebill.UserInfoStorage";
    private final static String KEY_USER_ID = "es.shwebill.UserInfoStorage.KEY_USER_ID";
    private final static String KEY_FULL_NAME = "es.shwebill.UserInfoStorage.KEY_FULL_NAME";
    private final static String KEY_TOKEN = "es.shwebill.UserInfoStorage.KEY_TOKEN";
    private final static String KEY_AUTO_MAX_ACCOUNT = "es.shwebill.UserInfoStorage.KEY_AUTO_MAX_ACCOUNT";

    private static UserInfoStorage userInfoStorage = null;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private UserInfoStorage(Context context) {

        this.sharedPreferences =
                context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        editor = null;
        this.editor = this.sharedPreferences.edit();
    }

    public synchronized static void initialize(Context context) {

        if (userInfoStorage == null) {
            userInfoStorage = new UserInfoStorage(context);
        }
    }

    public synchronized static UserInfoStorage getInstance() {

        return UserInfoStorage.userInfoStorage;
    }

    public void cleanUpAll() {
        this.editor.remove(KEY_FULL_NAME);
        this.editor.remove(KEY_TOKEN);
        this.editor.remove(KEY_USER_ID);
        this.editor.remove(KEY_AUTO_MAX_ACCOUNT);
        this.editor.commit();
        Logger.d(this.getClass(), "UserInfoStorage.cleanUpCredentials : ");
    }

    public String getFullName() {

        return this.sharedPreferences.getString(KEY_FULL_NAME, null);
    }

    public void setFullName(String fullName) {

        this.editor.putString(KEY_FULL_NAME, fullName);
        this.editor.commit();
    }

    public String getToken() {

        return this.sharedPreferences.getString(KEY_TOKEN, null);
    }

    public String getUserId() {

        return this.sharedPreferences.getString(KEY_USER_ID, null);
    }

    public String getAutoMaxAccount() {

        return this.sharedPreferences.getString(KEY_AUTO_MAX_ACCOUNT, "VISIBLE");
    }

    public void setAutoMaxAccount(String autoMaxAccount) {

        this.editor.putString(KEY_AUTO_MAX_ACCOUNT, autoMaxAccount);
        this.editor.commit();
    }

    public boolean isCredentialsAvailable() {

        return (this.getUserId() != null && this.getUserId().trim().length() > 0) && (this
                .getToken() != null && this.getToken().trim().length() > 0);
    }

    public void saveUserProfile(String userId, String token, String fullName) {

        this.editor.putString(KEY_USER_ID, userId);
        this.editor.commit();

        Logger.d(this.getClass(), "UserInfoStorage.userId : " + userId);

        this.editor.putString(KEY_TOKEN, token);
        this.editor.commit();

        Logger.d(this.getClass(), "UserInfoStorage.token : " + token);

        this.editor.putString(KEY_FULL_NAME, fullName);
        this.editor.commit();

        Logger.d(this.getClass(), "UserInfoStorage.fullName : " + fullName);
    }
}