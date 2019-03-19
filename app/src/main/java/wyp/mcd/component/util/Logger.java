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

package wyp.mcd.component.util;

import android.util.Log;

import java.util.Date;

import wyp.mcd.BuildConfig;

public class Logger {

    private static final String TAG = "Computer-Dictionary";

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

    public static void i(String message) {

        if (BuildConfig.DEBUG) {
            Log.i(Logger.TAG, "thread [" + Thread.currentThread().getName() + "] - " + message);
        }
    }

    public static void w(String message) {

        if (BuildConfig.DEBUG) {
            Log.w(Logger.TAG, "thread [" + Thread.currentThread().getName() + "] - " + message);
        }
    }

    public static void v(String message) {

        if (BuildConfig.DEBUG) {
            Log.v(Logger.TAG, "thread [" + Thread.currentThread().getName() + "] - " + message);
        }
    }
}
