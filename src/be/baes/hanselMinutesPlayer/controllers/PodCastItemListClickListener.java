package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.widget.*;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.helpers.Network;
import be.baes.hanselMinutesPlayer.model.PodCast;
import com.google.inject.Inject;

import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

import java.io.File;

public class PodCastItemListClickListener implements OnItemClickListener {
	@Inject Player player;
    @Inject Settings settings;
    @Inject Network network;

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Log.i(Constants.LOG_ID, "PodCast item selected");
        Log.i(Constants.LOG_ID, String.format("Selected file: %s", ((PodCast) arg0.getAdapter().getItem(arg2)).getTitle()));
        File file = new File(settings.getCacheDirectory(),((PodCast) arg0.getAdapter().getItem(arg2)).getPodCastName());
        if(!file.exists() && !network.haveInternet(arg0.getContext()))
        {
            Log.i(Constants.LOG_ID, "No internet connection");
            Toast.makeText(arg0.getContext(),settings.NoInternetConnection(), Toast.LENGTH_LONG).show();
        }
        else
        {
            player.setCurrentFile(((PodCast) arg0.getAdapter().getItem(arg2)));
        }
    }
}
