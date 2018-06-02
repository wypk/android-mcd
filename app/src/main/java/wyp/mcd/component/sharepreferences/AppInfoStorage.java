/*
 * Copyright (C) 2018
 *
 * Source code is created by Elissa Software
 * Dictionary data is owned by UCST
 * Database is implemented by Salai Chit Oo Latt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wyp.mcd.component.sharepreferences;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import wyp.mcd.component.util.Logger;

public class AppInfoStorage {

    private final static String PREFERENCES_NAME = "org.mcd.AppInfoStorage";
    private final static String KEY_SHORTCUT = "org.mcd.AppInfoStorage.KEY_SHORTCUT";
    private final static String KEY_DB_PERSIST = "org.mcd.AppInfoStorage.KEY_DB_PERSIST";

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

    public boolean isShortcutCreated() {
        return this.sharedPreferences.getBoolean(KEY_SHORTCUT, false);
    }

    public void shortcutCreated() {
        this.editor.putBoolean(KEY_SHORTCUT, true);
        this.editor.commit();
    }

    public boolean isDbPersisted() {
        return this.sharedPreferences.getBoolean(KEY_DB_PERSIST, false);
    }

    public void dbPersisted() {
        this.editor.putBoolean(KEY_DB_PERSIST, true);
        this.editor.commit();
    }

    public void cleanUpAll() {
        this.editor.remove(KEY_SHORTCUT);
        this.editor.remove(KEY_DB_PERSIST);
        this.editor.commit();
        Logger.d(this.getClass(), "AppInfoStorage.cleanUpAll DONE");
    }
}
