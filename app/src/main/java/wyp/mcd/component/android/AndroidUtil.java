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

package wyp.mcd.component.android;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;

import wyp.mcd.R;
import wyp.mcd.component.sharepreferences.AppInfoStorage;

public class AndroidUtil {

    public static boolean isInternetAvailable(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting()
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static void playDefaultNotificationSound(Context context) {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(context, notification);
            ringtone.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createShortcut(Context context, Class clazz) {

        Intent shortcutIntent = new Intent(context.getApplicationContext(), clazz);
        shortcutIntent.setAction(Intent.ACTION_MAIN);
        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        intent.putExtra(
                Intent.EXTRA_SHORTCUT_NAME, context.getResources().getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource
                .fromContext(context.getApplicationContext(), R.mipmap.ic_launcher_round));
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        context.getApplicationContext().sendBroadcast(intent);
        AppInfoStorage.getInstance().shortcutCreated();
    }
}
