package be.baes.hanselMinutesPlayer;

import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import be.baes.hanselMinutesPlayer.view.adapters.PodCastAdapterImpl;
import be.baes.hanselMinutesPlayer.controllers.*;
import be.baes.hanselMinutesPlayer.facade.*;
import be.baes.hanselMinutesPlayer.model.FillListResult;
import be.baes.hanselMinutesPlayer.model.Position;
import be.baes.hanselMinutesPlayer.view.ListViewContextMenu;
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
	@InjectView(R.id.seekBar) SeekBar seekbar;
    @InjectView(R.id.timer) TextView timer;
    @InjectView(R.id.currentPodCast) TextView currentPodCast;
    @InjectView(R.id.refreshListButton) Button refreshListButton;
	@InjectView(R.id.podCastList) ListView podCastListView;
    @InjectView(R.id.numberofpodcasts) TextView numberOfPodCasts;
    @Inject ProgressReport progressReport;
	@Inject OnPlayClickListener onPlayClickListener;
	@Inject OnStopClickListener onStopClickListener;
	@Inject OnPauseClickListener onPauseClickListener;
	@Inject OnRefreshListClickListener onRefreshListClickListener;
	@Inject PodCastItemListClickListener rssItemListClickListener;
	@Inject OnSeekChangeListener onSeekChangeListener;
	@Inject PositionUpdater positionUpdater;
    @Inject PodCastList podCastList;
    @Inject ListViewContextMenu listViewContextMenu;
    @Inject OnScrollPodCastListListener onScrollPodCastListListener;
    Position position;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        progressReport.setActivity(this);
        positionUpdater.addObserver(this);
        podCastList.addObserver(this);
        refreshListButton.setOnClickListener(onRefreshListClickListener);
        playButton.setOnClickListener(onPlayClickListener);
        stopButton.setOnClickListener(onStopClickListener);
        pauseButton.setOnClickListener(onPauseClickListener);
        podCastListView.setOnItemClickListener(rssItemListClickListener);
        podCastListView.setOnScrollListener(onScrollPodCastListListener);
        seekbar.setOnSeekBarChangeListener(onSeekChangeListener);
        registerForContextMenu(podCastListView);
        if(savedInstanceState==null)
        {
            Log.i(Constants.LOG_ID, "OnCreate no saved instance state");
            podCastList.load(0, getResources());
        }
        else
        {
            Log.i(Constants.LOG_ID, "OnCreate with saved instance state");
            Log.i(Constants.LOG_ID, String.format("CurrentPage: %d", savedInstanceState.getInt(Constants.CURRENT_PAGE)));
            Log.i(Constants.LOG_ID, String.format("Previous item visible was: %d", savedInstanceState.getInt(Constants.LIST_VIEW_POSITION)));
            podCastList.load(savedInstanceState.getInt(Constants.CURRENT_PAGE), savedInstanceState.getInt(Constants.LIST_VIEW_POSITION), getResources());
            setPosition((Position) savedInstanceState.getSerializable(Constants.POSITION));
        }
    }

    @Override
    public void onDestroy()
    {
        Log.i(Constants.LOG_ID, "Destroying activity");
        super.onDestroy();
        positionUpdater.deleteObserver(this);
        podCastList.deleteObserver(this);
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
    public void onSaveInstanceState(Bundle bundle)
    {
        Log.i(Constants.LOG_ID, "Saving instance state");
        bundle.putInt(Constants.CURRENT_PAGE, podCastList.getCurrentPage());
        bundle.putInt(Constants.LIST_VIEW_POSITION, podCastListView.getFirstVisiblePosition());
        bundle.putSerializable(Constants.POSITION, position);
        super.onSaveInstanceState(bundle);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void update(Observable observable, Object o) {
        if(observable.getClass().equals(PositionUpdater.class))
        {
            setPosition((Position) o);
        }
        if(observable.getClass().equals(PodCastListImpl.class))
        {
            setList((FillListResult) o);
        }
    }

    private void setList(FillListResult fillListResult) {
        if(fillListResult.getPodCasts()!=null)
        {
            PodCastAdapterImpl adapter = new PodCastAdapterImpl(this, R.layout.row, fillListResult.getPodCasts());
            podCastListView.setAdapter(adapter);
            podCastListView.setSelection(fillListResult.getPosition());
        } else
        {
            podCastListView.setAdapter(null);
        }
        numberOfPodCasts.setText(fillListResult.getNumberOfPodCasts());
    }

    private void setPosition(Position position) {
        playButton.setEnabled(position.getHasPodCast());
        stopButton.setEnabled(position.getHasPodCast());
        pauseButton.setEnabled(position.getHasPodCast());
        seekbar.setEnabled(position.getHasPodCast());
        seekbar.setMax(position.getMaxDuration());
        seekbar.setProgress(position.getProgress());
        timer.setText(position.getTimer());
        currentPodCast.setText(position.getMessage());
        this.position = position;
    }
}