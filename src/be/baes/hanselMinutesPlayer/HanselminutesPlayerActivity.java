package be.baes.hanselMinutesPlayer;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import be.baes.hanselMinutesPlayer.controllers.*;
import be.baes.hanselMinutesPlayer.facade.ListViewContextMenu;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import com.google.inject.Inject;

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
	@Inject
    PodCastItemListClickListener rssItemListClickListener;
	@Inject OnSeekChangeListener onSeekChangeListener;
	@Inject Player player;
	@Inject PositionUpdater positionUpdater;
    @Inject PodCastList podCastList;
    @Inject ListViewContextMenu listViewContextMenu;
    @Inject OnScrollPodCastListListener onScrollPodCastListListener;

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
        listView.setOnScrollListener(onScrollPodCastListListener);
        seekbar.setOnSeekBarChangeListener(onSeekChangeListener);
        registerForContextMenu(listView);
        podCastList.load(0);
    }
    
    @Override
    public void onDestroy()
    {
    	positionUpdater.pausePosition();
    	player.destroy();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        listViewContextMenu.onCreate(menu, v);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        listViewContextMenu.onItemSelected(item);
        return true;
    }

}