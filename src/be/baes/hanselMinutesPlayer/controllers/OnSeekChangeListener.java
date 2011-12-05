package be.baes.hanselMinutesPlayer.controllers;

import com.google.inject.Inject;

import be.baes.hanselMinutesPlayer.facade.Player;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class OnSeekChangeListener implements OnSeekBarChangeListener {
	@Inject Player player;
	@Inject PositionUpdater positionUpdater;
	
	private boolean isMoveingSeekBar;

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		isMoveingSeekBar = false;
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		isMoveingSeekBar = true;
		positionUpdater.pausePosition();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
		if(isMoveingSeekBar)
		{
			player.seekTo(progress);
			positionUpdater.updatePosition();
		}
	}
}
