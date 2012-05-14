package be.baes.hanselMinutesPlayer.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import be.baes.hanselMinutesPlayer.dal.SettingsAdapter;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 20/12/11
 * Time: 17:53
 */
public class NetworkImpl implements Network {
    @Inject SettingsAdapter settingsAdapter;

    @Override
    public boolean haveInternet(Context ctx) {

        NetworkInfo info = ((ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null || !info.isConnected()) {
            return false;
        }
        if (info.isRoaming()) {
            return settingsAdapter.getSettings().getOnRoaming();
        }
        return true;
    }
}
