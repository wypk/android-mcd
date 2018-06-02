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

package wyp.mcd;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import wyp.mcd.component.android.AppLifecycle;
import wyp.mcd.component.sharepreferences.AppInfoStorage;
import wyp.mcd.component.util.Logger;

public class McdApplication extends Application {

    private static McdApplication instance;

    public static synchronized McdApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Fabric.with(this, new Answers());
        instance = this;

        /* Register our AppLifecycle detector */
        this.registerActivityLifecycleCallbacks(new AppLifecycle());

        /* SharedPreferences init */
        Logger.d(McdApplication.class, "Storage init...");
        AppInfoStorage.initialize(this);

        /*
         For Vector Drawables
         Make sure we use vector drawables
         */
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        /* Setup default font for app */
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Gordita-Medium.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
