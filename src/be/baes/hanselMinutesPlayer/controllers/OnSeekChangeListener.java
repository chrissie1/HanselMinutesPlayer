package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.Player;
import com.google.inject.Inject;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class OnSeekChangeListener implements OnSeekBarChangeListener {
	@Inject Player player;
	private boolean isMovingSeekBar;

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
        Log.i(Constants.LOG_ID, "Stop tracking");
		isMovingSeekBar = false;
        player.play();
    }

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
        Log.i(Constants.LOG_ID, "start tracking");
		isMovingSeekBar = true;
		player.pause();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
        if(!fromUser) return;
        Log.i(Constants.LOG_ID, "Progress changed");
		if(isMovingSeekBar)
		{
			player.seekTo(progress);
		}
	}
}
