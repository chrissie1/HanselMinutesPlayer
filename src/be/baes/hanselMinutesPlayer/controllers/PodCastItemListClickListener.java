package be.baes.hanselMinutesPlayer.controllers;

import be.baes.hanselMinutesPlayer.model.PodCast;
import com.google.inject.Inject;

import roboguice.inject.InjectView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.rss.RSSItem;

public class PodCastItemListClickListener implements OnItemClickListener {
	@InjectView(R.id.textView2) TextView textView;
	@InjectView(R.id.playButton) Button playButton;
	@InjectView(R.id.pauseButton) Button pauseButton;
	@InjectView(R.id.stopButton) Button stopButton;
	@InjectView(R.id.seekBar1) SeekBar seekBar;
	@Inject Player player;
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		textView.setText("Now playing: " + ((PodCast)arg0.getAdapter().getItem(arg2)).getTitle());
		player.setCurrentFile(((PodCast)arg0.getAdapter().getItem(arg2)).getMP3Link());
		playButton.setEnabled(true);
		stopButton.setEnabled(true);
		pauseButton.setEnabled(true);
		seekBar.setEnabled(true);
	}
}
