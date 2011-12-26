package be.baes.hanselMinutesPlayer.controllers;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.helpers.Network;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import com.google.inject.Inject;

/**
* Created by IntelliJ IDEA.
* User: christiaan
* Date: 26/12/11
* Time: 9:50
* To change this template use File | Settings | File Templates.
*/
class OnRefreshListClickListener implements DialogInterface.OnClickListener {
    @Inject Context context;
    @Inject PodCastList podCastList;
    @Inject StringResources stringResources;
    @Inject Network network;

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(network.haveInternet(context))
        {
            podCastList.getListFromRssAndUpdateDatabase(Constants.urlToRssFeedAll);
        }
        else
        {
            Log.i(Constants.LOG_ID, "No internet connection");
            Toast.makeText(context, stringResources.NoInternetConnection(), Toast.LENGTH_LONG).show();
        }
    }
}
