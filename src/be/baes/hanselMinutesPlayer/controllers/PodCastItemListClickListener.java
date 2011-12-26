package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.widget.*;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.helpers.Network;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import com.google.inject.Inject;

import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

public class PodCastItemListClickListener implements OnItemClickListener {
	@Inject Player player;
    @Inject StringResources stringResources;
    @Inject Network network;

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Log.i(Constants.LOG_ID, "PodCast item selected");
        Log.i(Constants.LOG_ID, String.format("Selected file: %s", ((PodCast) arg0.getAdapter().getItem(arg2)).getTitle()));
        if(!player.hasCurrentPodCastDownloadedMp3() && !network.haveInternet(arg0.getContext()))
        {
            Log.i(Constants.LOG_ID, "No internet connection");
            Toast.makeText(arg0.getContext(), stringResources.NoInternetConnection(), Toast.LENGTH_LONG).show();
        }
        else
        {
            player.setCurrentFile(((PodCast) arg0.getAdapter().getItem(arg2)));
        }
    }
}
