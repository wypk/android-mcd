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

package wyp.mcd.component.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;

import java.io.Serializable;
import java.util.Map;

import wyp.mcd.component.ui.BasicActivity;

public class TransitionUtil {

    public static void showNextActivity(
            Activity activity,
            Class<? extends BasicActivity> activityClazz,
            @AnimRes int incomingActivityAnim,
            @AnimRes int outgoingActivityAnim,
            boolean killMe) {

        activity.startActivity(new Intent(activity, activityClazz));

        if (killMe) {
            activity.finish();
        }
        activity.overridePendingTransition(incomingActivityAnim, outgoingActivityAnim);
    }

    public static void showNextActivityWithMap(
            Activity activity,
            Class<? extends BasicActivity> activityClazz,
            Map<String, Serializable> data,
            @AnimRes int incomingActivityAnim,
            @AnimRes int outgoingActivityAnim,
            boolean killMe) {

        Intent intent = new Intent(activity, activityClazz);

        if (data != null && !data.isEmpty()) {
            for (String key : data.keySet()) {
                intent.putExtra(key, data.get(key));
            }
        }

        activity.startActivity(intent);

        if (killMe) {
            activity.finish();
        }

        activity.overridePendingTransition(incomingActivityAnim, outgoingActivityAnim);
    }

    public static void showNextActivityWithBundle(
            Activity activity,
            Class<? extends BasicActivity> activityClazz,
            Bundle data,
            @AnimRes int incomingActivityAnim,
            @AnimRes int outgoingActivityAnim,
            boolean killMe) {

        Intent intent = new Intent(activity, activityClazz);

        intent.putExtras(data);

        activity.startActivity(intent);

        if (killMe) {
            activity.finish();
        }

        activity.overridePendingTransition(incomingActivityAnim, outgoingActivityAnim);
    }

    public static void showNextActivityWithTransitionAsRoot(
            Activity activity,
            Class<? extends BasicActivity> activityClazz,
            Bundle extras,
            @AnimRes int incomingActivityAnim,
            @AnimRes int outgoingActivityAnim,
            boolean killMe) {

        Intent intent = new Intent(activity, activityClazz);

        if (extras != null) {
            intent.putExtras(extras);
        }

        intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);

        activity.startActivity(intent);

        if (killMe) {
            activity.finish();
        }

        activity.overridePendingTransition(incomingActivityAnim, outgoingActivityAnim);
    }
}
