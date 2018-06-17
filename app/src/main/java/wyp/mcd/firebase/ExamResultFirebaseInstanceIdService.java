package wyp.mcd.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import wyp.mcd.component.util.Logger;

public class ExamResultFirebaseInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Logger.log("Refreshed token: " + FirebaseInstanceId.getInstance().getToken());
    }
}
