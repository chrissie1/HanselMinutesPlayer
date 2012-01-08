package be.baes.hanselMinutesPlayer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import be.baes.hanselMinutesPlayer.controllers.*;
import be.baes.hanselMinutesPlayer.facade.*;
import be.baes.hanselMinutesPlayer.model.FillListResult;
import be.baes.hanselMinutesPlayer.model.Position;
import be.baes.hanselMinutesPlayer.resources.ColorResources;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import be.baes.hanselMinutesPlayer.view.adapters.PodCastAdapterImpl;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 30/12/11
 * Time: 20:11
 */
public class SearchTitlesActivity extends RoboActivity implements Observer{
    @InjectView(R.id.searchedText) TextView searchedText;
    @InjectView(R.id.detailsButton) Button detailsButton;
    @InjectView(R.id.playButton) Button playButton;
    @InjectView(R.id.stopButton) Button stopButton;
    @InjectView(R.id.pauseButton) Button pauseButton;
    @InjectView(R.id.seekBar) SeekBar seekbar;
    @InjectView(R.id.timer) TextView timer;
    @InjectView(R.id.currentPodCast) TextView currentPodCast;
    @InjectView(R.id.searchList) ListView searchList;
    @InjectView(R.id.searchMainView) View mainView;
    @InjectView(R.id.foundText) TextView foundText;
    @Inject OnPodCastItemListClickListener onPodCastItemListClickListener;
    @Inject OnPlayClickListener onPlayClickListener;
    @Inject OnStopClickListener onStopClickListener;
    @Inject OnPauseClickListener onPauseClickListener;
    @Inject OnDetailsFromSearchClickListener onDetailsClickListener;
    @Inject OnSeekChangeListener onSeekChangeListener;
    @Inject OnSearchClickListener onSearchClickListener;
    @Inject OnSettingsClickListener onSettingsClickListener;
    @Inject PositionUpdater positionUpdater;
    @Inject SearchList searchListFacade;
    @Inject StringResources stringResources;
    @Inject ColorResources colorResources;
    @Inject Settings settings;
    @Inject ProgressReport progressReport;
    private Position position;
    private SharedPreferences sharedPreferences;
    @Inject OnFlingSearchOnTouchListener onFlingSearchOnTouchListener;
    @Inject Player player;
    private String query;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        progressReport.setActivity(this);
        settings.initialize(getExternalFilesDir(Environment.DIRECTORY_PODCASTS));
        colorResources.initialize(getResources());
        stringResources.initialize(getResources());

        setGestureDetector();
        setObservers();
        setListeners();
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            searchedText.setText("Searched for: " + query);
            searchListFacade.search(query);
            if(player.getCurrentPodCast()!=null)
            {
                Log.i(Constants.LOG_ID, "Loading current podcast");
                setPosition(positionUpdater.getCurrentPosition());
            }
        }
        else if(player.getCurrentPodCast()!=null && sharedPreferences.getAll().size()>0 && savedInstanceState==null)
        {
            Log.i(Constants.LOG_ID, "OnResume with saved instance state");
            searchListFacade.search(sharedPreferences.getString(Constants.SEARCH_TEXT, ""),sharedPreferences.getInt(Constants.LIST_VIEW_POSITION,0));
            query =  sharedPreferences.getString(Constants.SEARCH_TEXT, "");
            setPosition(new Position(sharedPreferences.getString(Constants.TIMER,""),sharedPreferences.getString(Constants.MESSAGE,""),sharedPreferences.getInt(Constants.PROGRESS,0),sharedPreferences.getInt(Constants.MAX_DURATION,0),sharedPreferences.getBoolean(Constants.HASPODCAST,false), sharedPreferences.getString(Constants.DESCRIPTION, "")));
        }
        else if(savedInstanceState!=null)
        {
            Log.i(Constants.LOG_ID, "OnCreate with saved instance state");
            Log.i(Constants.LOG_ID, String.format("CurrentPage: %d", savedInstanceState.getInt(Constants.CURRENT_PAGE)));
            Log.i(Constants.LOG_ID, String.format("Previous item visible was: %d", savedInstanceState.getInt(Constants.LIST_VIEW_POSITION)));
            searchListFacade.search(savedInstanceState.getString(Constants.SEARCH_TEXT),savedInstanceState.getInt(Constants.LIST_VIEW_POSITION));
            query = savedInstanceState.getString(Constants.SEARCH_TEXT);
            if(savedInstanceState.getSerializable(Constants.POSITION)!= null)
            {
                setPosition((Position) savedInstanceState.getSerializable(Constants.POSITION));
            }
        }
    }

    private void setGestureDetector()
    {
        searchList.setOnTouchListener(onFlingSearchOnTouchListener);
        mainView.setOnTouchListener(onFlingSearchOnTouchListener);
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
            ed.putInt(Constants.LIST_VIEW_POSITION, searchList.getFirstVisiblePosition());
            ed.putString(Constants.SEARCH_TEXT, query);
            ed.putString(Constants.DESCRIPTION, position.getDescription());
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
    public void onSaveInstanceState(Bundle bundle)
    {
        Log.i(Constants.LOG_ID, "Saving instance state");
        bundle.putInt(Constants.LIST_VIEW_POSITION, searchList.getFirstVisiblePosition());
        bundle.putString(Constants.SEARCH_TEXT, query);
        bundle.putSerializable(Constants.POSITION, position);
        super.onSaveInstanceState(bundle);
    }

    private void unsetGestureDetector() {
        mainView.setOnTouchListener(null);
        searchList.setOnTouchListener(null);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        searchListFacade.deleteObserver(this);
        unsetGestureDetector();
    }

    private void setObservers() {
        positionUpdater.addObserver(this);
        searchListFacade.addObserver(this);
    }

    private void setListeners() {
        detailsButton.setOnClickListener(onDetailsClickListener);
        playButton.setOnClickListener(onPlayClickListener);
        stopButton.setOnClickListener(onStopClickListener);
        pauseButton.setOnClickListener(onPauseClickListener);
        searchList.setOnItemClickListener(onPodCastItemListClickListener);
        seekbar.setOnSeekBarChangeListener(onSeekChangeListener);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable.getClass().equals(PositionUpdater.class))
        {
            setPosition((Position) o);
        }
        if(observable.getClass().equals(SearchListImpl.class))
        {
            setList((FillListResult) o);
        }
    }

    private void setList(FillListResult fillListResult) {
        if(fillListResult.getPodCasts()!=null)
        {
            PodCastAdapterImpl adapter = new PodCastAdapterImpl(this, R.layout.row, fillListResult.getPodCasts(), settings, stringResources, colorResources);
            searchList.setAdapter(adapter);
            searchList.setSelection(fillListResult.getPosition());
        } else
        {
            searchList.setAdapter(null);
        }
        searchList.setEnabled(true);
        foundText.setText(fillListResult.getNumberOfPodCasts());
        searchedText.setText("Searched for: " + query);
    }

    private void setPosition(Position position) {
        onFlingSearchOnTouchListener.setDoDetail(position.getHasPodCast());
        detailsButton.setEnabled(position.getHasPodCast());
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

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem settingsMenu = menu.findItem(R.id.settingsMenu);
        MenuItem searchMenu = menu.findItem(R.id.searchmenu);
        settingsMenu.setOnMenuItemClickListener(onSettingsClickListener);
        searchMenu.setOnMenuItemClickListener(onSearchClickListener);
        return true ;
    }
}
