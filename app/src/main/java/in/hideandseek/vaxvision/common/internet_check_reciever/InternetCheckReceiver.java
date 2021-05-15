package in.hideandseek.vaxvision.common.internet_check_reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import in.hideandseek.vaxvision.common.eventbus.*;

public class InternetCheckReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        try {
            if (isOnline(context)) {
                Events.NetworkOnline networkOnline = new Events.NetworkOnline();
                GlobalBus.getsBus().post(networkOnline);
            } else {
                Events.NetworkOffline networkOffline = new Events.NetworkOffline();
                GlobalBus.getsBus().post(networkOffline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns true if connected to the internet, and false otherwise
     *
     * <p>
     * NetworkInfo is deprecated in API 29
     * https://developer.android.com/reference/android/net/NetworkInfo
     * <p>
     * getActiveNetworkInfo() is deprecated in API 29
     * https://developer.android.com/reference/android/net/ConnectivityManager#getActiveNetworkInfo()
     * <p>
     * getNetworkInfo(int) is deprecated as of API 23
     * https://developer.android.com/reference/android/net/ConnectivityManager#getNetworkInfo(int)
     */
    private static boolean isOnline(Context context) {

        boolean mIsConnected = false;
        ConnectivityManager mConnectivityMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            // Checking internet connectivity
            NetworkInfo activeNetwork = null;
            if (mConnectivityMgr != null) {
                activeNetwork = mConnectivityMgr.getActiveNetworkInfo(); // Deprecated in API 29
            }
            mIsConnected = activeNetwork != null;

        } else {
            Network[] allNetworks = mConnectivityMgr.getAllNetworks(); // added in API 21 (Lollipop)
            for (Network network : allNetworks) {
                NetworkCapabilities networkCapabilities = mConnectivityMgr.getNetworkCapabilities(network);
                if (networkCapabilities != null) {
                    if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))
                        mIsConnected = true;
                }
            }
        }

        return mIsConnected;

    }
}
