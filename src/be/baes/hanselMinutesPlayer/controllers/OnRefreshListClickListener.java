package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.widget.Toast;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.helpers.Network;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;
import com.google.inject.Inject;
import android.view.View;
import android.view.View.OnClickListener;

public class OnRefreshListClickListener implements OnClickListener {
	@Inject PodCastList podCastList;
    @Inject Settings settings;
    @Inject Network network;
    @Inject YesNoAlertDialog yesNoAlertDialog;

    @Override
    public void onClick(View arg0) {
        Log.i(Constants.LOG_ID, "Clicked refresh list");
        if(!yesNoAlertDialog.show(arg0, "Refresh list", "Are you sure you want to refresh the list? This could take a while.")) return;
        if(network.haveInternet(arg0.getContext()))
        {
		    podCastList.getListFromRssAndUpdateDatabase();
        }
        else
        {
            Log.i(Constants.LOG_ID, "No internet connection");
            Toast.makeText(arg0.getContext(),settings.NoInternetConnection(), Toast.LENGTH_LONG).show();
        }
    }
}
