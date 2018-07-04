package es.shwebill.component.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AnimRes;

import java.io.Serializable;
import java.util.Map;

import es.shwebill.component.android.BasicActivity;

public class TransitionUtil {

    public static void showNextActivity(Activity activity, Class<? extends BasicActivity> activityClazz, boolean killMe) {

        TransitionUtil.showNextActivity(activity, activityClazz, null, killMe);
    }

    public static void showNextActivity(
            Activity activity,
            Class<? extends BasicActivity> activityClazz,
            Map<String, Object> data, boolean killMe) {

        Intent intent = new Intent(activity, activityClazz);
        if (data != null && data.isEmpty() == false) {
            for (String key : data.keySet()) {
                if (data.get(key) instanceof Serializable) {
                    intent.putExtra(key, (Serializable) data.get(key));
                } else if (data.get(key) instanceof Parcelable) {
                    intent.putExtra(key, (Parcelable) data.get(key));
                }
            }
        }
        activity.startActivity(intent);
        if (killMe) {
            activity.finish();
        }
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        if (killMe) {
            activity.finish();
        }
        activity.overridePendingTransition(incomingActivityAnim, outgoingActivityAnim);
    }
}
