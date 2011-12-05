package be.baes.hanselMinutesPlayer.controllers;

import java.io.IOException;

import com.google.inject.Inject;

import be.baes.hanselMinutesPlayer.facade.Player;
import android.view.View;
import android.view.View.OnClickListener;

public class OnPlayClickListener implements OnClickListener {
	@Inject Player player;
	@Inject PositionUpdater positionUpdater;
	
	@Override
    public void onClick(View arg0) {
            try {
            	positionUpdater.startPosition();
				player.play();
				positionUpdater.updatePosition();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}
