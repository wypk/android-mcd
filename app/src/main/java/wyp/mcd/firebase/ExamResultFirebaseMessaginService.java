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

package wyp.mcd.firebase;

import android.annotation.SuppressLint;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import wyp.mcd.R;
import wyp.mcd.component.android.AndroidUtil;
import wyp.mcd.component.android.McdNotificationManager;
import wyp.mcd.component.util.Logger;
import wyp.mcd.ui.activity.AppUpdateActivity;


@SuppressLint("Registered")
public class ExamResultFirebaseMessaginService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().size() > 0) {
            Logger.log("Message received [" + remoteMessage.getData() + "]");
        }
        if (remoteMessage.getNotification() != null) {
            Logger.log("Message Notification Body: [" + remoteMessage.getNotification().getBody() + "]");
            sendNotification(remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(String messageBody) {

        AndroidUtil.playDefaultNotificationSound(this);
        McdNotificationManager.notify(
                this, McdNotificationManager.CHAT_CHANNEL_ID,
                getResources().getString(R.string.app_name),
                messageBody, AppUpdateActivity.class);
    }
}
