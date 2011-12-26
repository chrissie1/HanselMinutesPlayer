package be.baes.hanselMinutesPlayer.controllers;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.helpers.Network;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import com.google.inject.Inject;

/**
* Created by IntelliJ IDEA.
* User: christiaan
* Date: 26/12/11
* Time: 9:11
* To change this template use File | Settings | File Templates.
*/
class OnDownloadPodCastClickListener implements DialogInterface.OnClickListener {
    @Inject Context context;
    @Inject Player player;
    @Inject StringResources stringResources;
    @Inject Network network;

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(network.haveInternet(context))
        {
            player.downloadMp3();
        }
        else
        {
            Log.i(Constants.LOG_ID, "No internet connection");
            Toast.makeText(context, stringResources.NoInternetConnection(), Toast.LENGTH_LONG).show();
        }
    }
}
