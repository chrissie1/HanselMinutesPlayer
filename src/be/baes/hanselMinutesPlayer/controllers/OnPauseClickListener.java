package be.baes.hanselMinutesPlayer.controllers;

import com.google.inject.Inject;

import be.baes.hanselMinutesPlayer.facade.Player;
import android.view.View;
import android.view.View.OnClickListener;

public class OnPauseClickListener implements OnClickListener {
	@Inject Player player;

	@Override
    public void onClick(View arg0) {
        player.pause();
    }
}
