package wyp.mcd.firebase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import wyp.mcd.R;
import wyp.mcd.component.android.AndroidUtil;
import wyp.mcd.component.android.McdNotificationManager;
import wyp.mcd.component.util.Logger;
import wyp.mcd.ui.activity.MainActivity;


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
                messageBody, MainActivity.class);
    }
}
