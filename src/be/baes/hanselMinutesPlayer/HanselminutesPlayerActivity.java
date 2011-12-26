package be.baes.hanselMinutesPlayer;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import be.baes.hanselMinutesPlayer.resources.ColorResources;
import be.baes.hanselMinutesPlayer.resources.StringResources;
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

import java.util.Observable;
import java.util.Observer;

public class HanselminutesPlayerActivity extends RoboActivity implements Observer{
    @Inject
    Settings settings;
    @InjectView(R.id.playButton) Button playButton;
	@InjectView(R.id.stopButton) Button stopButton;
	@InjectView(R.id.pauseButton) Button pauseButton;
	@InjectView(R.id.seekBar) SeekBar seekbar;
    @InjectView(R.id.timer) TextView timer;
    @InjectView(R.id.currentPodCast) TextView currentPodCast;
    @InjectView(R.id.settingsButton) ImageButton settingsButton;
	@InjectView(R.id.podCastList) ListView podCastListView;
    @InjectView(R.id.numberofpodcasts) TextView numberOfPodCasts;
    @Inject ProgressReport progressReport;
	@Inject OnPlayClickListener onPlayClickListener;
	@Inject OnStopClickListener onStopClickListener;
	@Inject OnPauseClickListener onPauseClickListener;
	@Inject OnSettingsClickListener onSettingsClickListener;
	@Inject PodCastItemListClickListener rssItemListClickListener;
	@Inject OnSeekChangeListener onSeekChangeListener;
	@Inject PositionUpdater positionUpdater;
    @Inject PodCastList podCastList;
    @Inject ListViewContextMenu listViewContextMenu;
    @Inject OnScrollPodCastListListener onScrollPodCastListListener;
    @Inject Player player;
    @Inject StringResources stringResources;
    @Inject ColorResources colorResources;
    Position position;
    SharedPreferences sharedPreferences;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        sharedPreferences = getPreferences(MODE_PRIVATE);
        progressReport.setActivity(this);
        settings.initialize(getExternalCacheDir());
        colorResources.initialize(getResources());
        stringResources.initialize(getResources());
        positionUpdater.addObserver(this);
        podCastList.addObserver(this);
        settingsButton.setOnClickListener(onSettingsClickListener);
        playButton.setOnClickListener(onPlayClickListener);
        stopButton.setOnClickListener(onStopClickListener);
        pauseButton.setOnClickListener(onPauseClickListener);
        podCastListView.setOnItemClickListener(rssItemListClickListener);
        podCastListView.setOnScrollListener(onScrollPodCastListListener);
        seekbar.setOnSeekBarChangeListener(onSeekChangeListener);
        registerForContextMenu(podCastListView);
        if(savedInstanceState==null && player.getCurrentPodCast()==null)
        {
            Log.i(Constants.LOG_ID, "OnCreate no saved instance state");
            podCastList.load(0);
        }
        else if(player.getCurrentPodCast()!=null && sharedPreferences.getAll().size()>0 && savedInstanceState==null)
        {
            Log.i(Constants.LOG_ID, "OnResume with saved instance state");
            podCastList.load(sharedPreferences.getInt(Constants.CURRENT_PAGE, 0), sharedPreferences.getInt(Constants.LIST_VIEW_POSITION, 0));
            setPosition(new Position(sharedPreferences.getString(Constants.TIMER,""),sharedPreferences.getString(Constants.MESSAGE,""),sharedPreferences.getInt(Constants.PROGRESS,0),sharedPreferences.getInt(Constants.MAX_DURATION,0),sharedPreferences.getBoolean(Constants.HASPODCAST,false)));
        }
        else if(savedInstanceState!=null)
        {
            Log.i(Constants.LOG_ID, "OnCreate with saved instance state");
            Log.i(Constants.LOG_ID, String.format("CurrentPage: %d", savedInstanceState.getInt(Constants.CURRENT_PAGE)));
            Log.i(Constants.LOG_ID, String.format("Previous item visible was: %d", savedInstanceState.getInt(Constants.LIST_VIEW_POSITION)));
            podCastList.load(savedInstanceState.getInt(Constants.CURRENT_PAGE), savedInstanceState.getInt(Constants.LIST_VIEW_POSITION));
            if(savedInstanceState.getSerializable(Constants.POSITION)!= null)
            {
                setPosition((Position) savedInstanceState.getSerializable(Constants.POSITION));
            }
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        progressReport.setActivity(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.i(Constants.LOG_ID, "OnPause called.");
        if(position!=null)
        {
            Log.i(Constants.LOG_ID, "Saving state");
            SharedPreferences.Editor ed = sharedPreferences.edit();
            ed.putBoolean(Constants.HASPODCAST, position.getHasPodCast());
            ed.putInt(Constants.MAX_DURATION, position.getMaxDuration());
            ed.putInt(Constants.PROGRESS, position.getProgress());
            ed.putString(Constants.MESSAGE, position.getMessage());
            ed.putString(Constants.TIMER, position.getTimer());
            ed.putInt(Constants.CURRENT_PAGE, podCastList.getCurrentPage());
            ed.putInt(Constants.LIST_VIEW_POSITION, podCastListView.getFirstVisiblePosition());
            ed.commit();
        }
        else
        {
            Log.i(Constants.LOG_ID, "Clearing state");
            SharedPreferences.Editor ed = sharedPreferences.edit();
            ed.clear();
            ed.commit();
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
            PodCastAdapterImpl adapter = new PodCastAdapterImpl(this, R.layout.row, fillListResult.getPodCasts(), settings, stringResources, colorResources);
            podCastListView.setAdapter(adapter);
            podCastListView.setSelection(fillListResult.getPosition());
        } else
        {
            podCastListView.setAdapter(null);
        }
        podCastListView.setEnabled(true);
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