/*
 * Copyright (C) 2018
 *  Source code is created by Elissa Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package wyp.mcd.component.android;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import wyp.mcd.component.util.Logger;

public class AppLifecycle
        implements Application.ActivityLifecycleCallbacks {

    private static int resumed = 0;
    private static int paused = 0;
    private static int started = 0;
    private static int stopped = 0;

    public static boolean isApplicationVisible() {

        return started > stopped;
    }

    public static boolean isApplicationInForeground() {

        return resumed > paused;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

        Logger.log("AppLifecycle - onActivityStarted : " + activity.getClass().getName());
        ++started;
        Logger.log("AppLifecycle - started : " + started + " stopped : " + stopped);
    }

    @Override
    public void onActivityResumed(Activity activity) {

        Logger.log("AppLifecycle - onActivityResumed : " + activity.getClass().getName());
        ++resumed;
        Logger.log("AppLifecycle - resumed : " + resumed + " paused : " + paused);
    }

    @Override
    public void onActivityPaused(Activity activity) {

        Logger.log("AppLifecycle - onActivityPaused : " + activity.getClass().getName());
        ++paused;
        Logger.log("AppLifecycle - resumed : " + resumed + " paused : " + paused);
    }

    @Override
    public void onActivityStopped(Activity activity) {

        Logger.log("AppLifecycle - onActivityStopped : " + activity.getClass().getName());
        ++stopped;
        Logger.log("AppLifecycle - started : " + started + " stopped : " + stopped);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
