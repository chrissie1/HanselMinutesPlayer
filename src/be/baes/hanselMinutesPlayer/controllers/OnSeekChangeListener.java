package be.baes.hanselMinutesPlayer.controllers;

import be.baes.hanselMinutesPlayer.facade.Player;
import com.google.inject.Inject;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class OnSeekChangeListener implements OnSeekBarChangeListener {
	@Inject
    Player player;
	private boolean isMoveingSeekBar;

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		isMoveingSeekBar = false;
        player.play();
    }

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		isMoveingSeekBar = true;
		player.pause();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
		if(isMoveingSeekBar)
		{
			player.seekTo(progress);
		}
	}
}
