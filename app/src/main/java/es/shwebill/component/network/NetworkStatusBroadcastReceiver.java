package es.shwebill.component.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import es.shwebill.component.android.AndroidUtil;

public class NetworkStatusBroadcastReceiver extends BroadcastReceiver {

    private Context mContext;
    private NetworkStatusBroadcastReceiver.NetworkStatusListener networkStatusListener;

    public NetworkStatusBroadcastReceiver(
            Context mContext,
            NetworkStatusBroadcastReceiver.NetworkStatusListener networkStatusListener) {

        this.mContext = mContext;
        this.networkStatusListener = networkStatusListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (AndroidUtil.isInternetAvailable(this.mContext)) {
            networkStatusListener.onInternetAvailable();
        } else {
            networkStatusListener.onInternetUnavailable();
        }
    }

    public void registerToContext() {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.mContext.registerReceiver(this, intentFilter);
    }

    public void unregisterFromContext() {

        this.mContext.unregisterReceiver(this);
    }

    public interface NetworkStatusListener {

        void onInternetAvailable();

        void onInternetUnavailable();
    }
}
