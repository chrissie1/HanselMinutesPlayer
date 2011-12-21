package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.helpers.Network;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 13/12/11
 * Time: 9:31
 */
public class OnDownloadPodCastClickListener implements View.OnClickListener {
    @Inject Player player;
    @Inject Settings settings;
    @Inject Network network;
    @Inject YesNoAlertDialog yesNoAlertDialog;

    @Override
    public void onClick(View view) {
        Log.i(Constants.LOG_ID, "Clicked download podcast");
        if(!yesNoAlertDialog.show(view, "Download podcast?", "Are you sure you want to download the podcast?")) return;
        if(network.haveInternet(view.getContext()))
        {
            player.downloadMp3();
        }
        else
        {
            Log.i(Constants.LOG_ID, "No internet connection");
            Toast.makeText(view.getContext(), settings.NoInternetConnection(), Toast.LENGTH_LONG).show();
        }
    }
}
