package be.baes.hanselMinutesPlayer;

import com.google.inject.Inject;

import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.controllers.OnPauseClickListener;
import be.baes.hanselMinutesPlayer.controllers.OnPlayClickListener;
import be.baes.hanselMinutesPlayer.controllers.OnRefreshListClickListener;
import be.baes.hanselMinutesPlayer.controllers.OnSeekChangeListener;
import be.baes.hanselMinutesPlayer.controllers.OnStopClickListener;
import be.baes.hanselMinutesPlayer.controllers.PositionUpdater;
import be.baes.hanselMinutesPlayer.controllers.RssItemListClickListener;
import be.baes.hanselMinutesPlayer.facade.Player;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;

public class HanselminutesPlayerActivity extends RoboActivity {
	@InjectView(R.id.playButton) Button playButton;
	@InjectView(R.id.stopButton) Button stopButton;
	@InjectView(R.id.pauseButton) Button pauseButton;
	@InjectView(R.id.seekBar1) SeekBar seekbar;
	@InjectView(R.id.refreshListButton) Button refreshListButton;
	@InjectView(R.id.listView1) ListView listView;
	@Inject OnPlayClickListener onPlayClickListener;
	@Inject OnStopClickListener onStopClickListener;
	@Inject OnPauseClickListener onPauseClickListener;
	@Inject OnRefreshListClickListener onRefreshListClickListener;
	@Inject RssItemListClickListener rssItemListClickListener;
	@Inject OnSeekChangeListener onSeekChangeListener;
	@Inject Player player;
	@Inject PositionUpdater positionUpdater;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        refreshListButton.setOnClickListener(onRefreshListClickListener);
        playButton.setOnClickListener(onPlayClickListener);
        stopButton.setOnClickListener(onStopClickListener);
        pauseButton.setOnClickListener(onPauseClickListener);
        listView.setOnItemClickListener(rssItemListClickListener);
        seekbar.setOnSeekBarChangeListener(onSeekChangeListener);
    }
    
    @Override
    public void onDestroy()
    {
    	positionUpdater.pausePosition();
    	player.destroy();
    }
    
    
}