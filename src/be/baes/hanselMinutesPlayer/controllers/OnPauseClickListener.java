package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.Player;
import com.google.inject.Inject;

import android.view.View;
import android.view.View.OnClickListener;

public class OnPauseClickListener implements OnClickListener {
	@Inject Player player;

	@Override
    public void onClick(View arg0) {
        Log.i(Constants.LOG_ID, "Clicked pause");
        player.pause();
    }
}
