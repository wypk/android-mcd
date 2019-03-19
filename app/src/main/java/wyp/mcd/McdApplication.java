/*
 * Copyright 2019 Wai Yan (TechBase Software). All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wyp.mcd;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.multidex.MultiDex;
import androidx.appcompat.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;

import io.fabric.sdk.android.Fabric;
import wyp.mcd.component.android.McdNotificationManager;
import wyp.mcd.component.sharedpreferences.AppInfoStorage;
import wyp.mcd.component.util.Logger;

public class McdApplication extends Application {

    private static McdApplication instance;

    public static synchronized McdApplication getInstance() {
        if (null == instance) {
            instance = new McdApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Fabric.with(this, new Answers(), new Crashlytics());
        instance = this;

        /* SharedPreferences init */
        Logger.d(McdApplication.class, "Storage init...");
        AppInfoStorage.initialize(this);

        /*
         For Vector Drawables
         Make sure we use vector drawables
         */
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        /* Setup notification for app */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Logger.d(this.getClass(), "Registering notification channel...");
            McdNotificationManager.createNotificationChannel(this,
                    McdNotificationManager.CHAT_CHANNEL_ID,
                    "Notify Incoming",
                    "Application will notify whenever there is incoming message");
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
