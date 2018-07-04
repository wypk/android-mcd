package es.shwebill;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import es.shwebill.component.android.AppInfoStorage;
import es.shwebill.component.android.AppLifecycle;
import es.shwebill.component.android.ShweBillNotificationManager;
import es.shwebill.component.util.LanguageHelper;
import es.shwebill.component.util.Logger;

public class ShweBillApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate(); /* Register our AppLifecycle detector */
        this.registerActivityLifecycleCallbacks(new AppLifecycle());

        /*
         For Vector Drawables
         Make sure we use vector drawables
         */
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        Logger.d(ShweBillApplication.class, "Storage init...");
        AppInfoStorage.initialize(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Logger.d(this.getClass(), "Registering notification channel...");
            ShweBillNotificationManager.createNotificationChannel(
                    this, ShweBillNotificationManager.CHAT_CHANNEL_ID, "Notify Incoming",
                    "Application will notify whenever there is incoming message");
        }
    }

    /* override the base context of application to update default locale for the application */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageHelper.onAttach(base, LanguageHelper.getLanguage(base)));
        MultiDex.install(this);
    }
}