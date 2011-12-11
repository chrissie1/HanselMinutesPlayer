package be.baes.hanselMinutesPlayer;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import be.baes.hanselMinutesPlayer.controllers.*;
import be.baes.hanselMinutesPlayer.facade.*;
import be.baes.hanselMinutesPlayer.model.FillListResult;
import be.baes.hanselMinutesPlayer.model.Position;
import com.google.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.Observable;
import java.util.Observer;

public class HanselminutesPlayerActivity extends RoboActivity implements Observer{
	@InjectView(R.id.playButton) Button playButton;
	@InjectView(R.id.stopButton) Button stopButton;
	@InjectView(R.id.pauseButton) Button pauseButton;
	@InjectView(R.id.seekBar1) SeekBar seekbar;
    @InjectView(R.id.textView1) TextView textView1;
    @InjectView(R.id.textView2) TextView textView2;
    @InjectView(R.id.refreshListButton) Button refreshListButton;
	@InjectView(R.id.listView1) ListView listView;
    @InjectView(R.id.numberofpodcasts) TextView numberOfPodCasts;
	@Inject OnPlayClickListener onPlayClickListener;
	@Inject OnStopClickListener onStopClickListener;
	@Inject OnPauseClickListener onPauseClickListener;
	@Inject OnRefreshListClickListener onRefreshListClickListener;
	@Inject PodCastItemListClickListener rssItemListClickListener;
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
        
        positionUpdater.addObserver(this);
        podCastList.addObserver(this);
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

    @Override
    public void update(Observable observable, Object o) {
        if(observable.getClass().equals(PositionUpdater.class))
        {
            playButton.setEnabled(((Position) o).getHasPodCast());
            stopButton.setEnabled(((Position)o).getHasPodCast());
            pauseButton.setEnabled(((Position)o).getHasPodCast());
            seekbar.setEnabled(((Position) o).getHasPodCast());
            seekbar.setMax(((Position) o).getMaxDuration());
            seekbar.setProgress(((Position) o).getProgress());
            textView1.setText(((Position)o).getTimer());
            textView2.setText(((Position)o).getMessage());
        }
        if(observable.getClass().equals(PodCastList.class))
        {
            listView.setAdapter(((FillListResult)o).getPodCastAdapter());
            listView.setSelection(((FillListResult)o).getPosition());
            numberOfPodCasts.setText(((FillListResult)o).getNumberOfPodCasts());
            
        }
    }
}