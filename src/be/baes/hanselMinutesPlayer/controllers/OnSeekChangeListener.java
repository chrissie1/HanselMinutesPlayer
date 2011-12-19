package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.Player;
import com.google.inject.Inject;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class OnSeekChangeListener implements OnSeekBarChangeListener {
	@Inject Player player;
	private boolean isMoveingSeekBar;

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
        Log.i(Constants.LOG_ID, "Stop tracking");
		isMoveingSeekBar = false;
        player.play();
    }

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
        Log.i(Constants.LOG_ID, "start tracking");
		isMoveingSeekBar = true;
		player.pause();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
        Log.i(Constants.LOG_ID, "Progress changed");
		if(isMoveingSeekBar)
		{
			player.seekTo(progress);
		}
	}
}
