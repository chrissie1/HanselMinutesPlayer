package be.baes.hanselMinutesPlayer;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import be.baes.hanselMinutesPlayer.controllers.*;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.PositionUpdater;
import be.baes.hanselMinutesPlayer.model.Position;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 27/12/11
 * Time: 10:16
 */
public class DetailsActivity extends RoboActivity implements Observer{
    @InjectView(R.id.detailsButton) Button detailsButton;
    @InjectView(R.id.currentPodCast) TextView currentPodCast;
    @InjectView(R.id.playButton) Button playButton;
    @InjectView(R.id.stopButton) Button stopButton;
    @InjectView(R.id.pauseButton) Button pauseButton;
    @InjectView(R.id.detailsDeleteDownloadButton) Button deleteDownloadButton;
    @InjectView(R.id.detailsDownloadButton) Button downloadButton;
    @InjectView(R.id.seekBar) SeekBar seekbar;
    @InjectView(R.id.timer) TextView timer;
    @InjectView(R.id.detailscurrentPodCast) TextView detailsCurrentPodCast;
    @InjectView(R.id.detailsDescription) TextView description;
    @InjectView(R.id.detailsView) View mainView;
    @InjectView(R.id.detailsPubDate) TextView pubDate;
    @InjectView(R.id.viewInBrowserButton) Button vieInBrowser;
    @Inject OnPlayClickListener onPlayClickListener;          
    @Inject OnStopClickListener onStopClickListener;
    @Inject OnPauseClickListener onPauseClickListener;
    @Inject OnSeekChangeListener onSeekChangeListener;
    @Inject PositionUpdater positionUpdater;
    @Inject OnDeleteDownloadedPodCastWithAlertDialogClickListener onDeleteDownloadedPodCastWithAlertDialogClickListener;
    @Inject OnDownloadPodCastWithAlertDialogClickListener onDownloadPodCastWithAlertDialogClickListener;
    @Inject Player player;
    @Inject ProgressReport progressReport;
    @Inject OnFlingDetailsOpenMainOnTouchListener onFlingDetailsOpenMainOnTouchListener;
    @Inject OnFlingDetailsOpenSearchOnTouchListener onFlingDetailsOpenSearchOnTouchListener;
    @Inject OnViewInBrowserOnClickListener onViewInBrowserOnClickListener;
    @Inject OnCreateOptionsMenu onCreateOptionsMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        currentPodCast.setVisibility(TextView.GONE);
        detailsButton.setVisibility(Button.GONE);
        progressReport.setActivity(this);
        if(progressReport.IsInProgress()) progressReport.startProgress("Continuing");
        setListeners();
        setObservers();
        if(player.getCurrentPodCast()!=null && savedInstanceState==null)
        {
            Log.i(Constants.LOG_ID, "OnResume with saved instance state");
            setPosition(positionUpdater.getCurrentPosition());
        }
        else if(savedInstanceState!=null)
        {
            setPosition((Position)savedInstanceState.getSerializable(Constants.POSITION));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle)
    {
        Log.i(Constants.LOG_ID, "Saving instance state");
        bundle.putSerializable(Constants.POSITION, positionUpdater.getCurrentPosition());
        super.onSaveInstanceState(bundle);
    }
    
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        positionUpdater.deleteObserver(this);
    }


    private void setObservers() {
        positionUpdater.addObserver(this);
    }

    private void setListeners() {
        playButton.setOnClickListener(onPlayClickListener);
        stopButton.setOnClickListener(onStopClickListener);
        pauseButton.setOnClickListener(onPauseClickListener);
        seekbar.setOnSeekBarChangeListener(onSeekChangeListener);
        deleteDownloadButton.setOnClickListener(onDeleteDownloadedPodCastWithAlertDialogClickListener);
        downloadButton.setOnClickListener(onDownloadPodCastWithAlertDialogClickListener);
        vieInBrowser.setOnClickListener(onViewInBrowserOnClickListener);
        if(getIntent().getExtras().getString(Constants.PARENT).equals(Constants.SEARCH_ACTIVITY))
        {
            mainView.setOnTouchListener(onFlingDetailsOpenSearchOnTouchListener);
        }
        else
        {
            mainView.setOnTouchListener(onFlingDetailsOpenMainOnTouchListener);
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable.getClass().equals(PositionUpdater.class))
        {
            setPosition((Position) o);
        }
    }

    private void setPosition(Position position) {
        deleteDownloadButton.setEnabled(player.hasCurrentPodCastDownloadedMp3());
        downloadButton.setEnabled(!player.hasCurrentPodCastDownloadedMp3());
        playButton.setEnabled(position.getHasPodCast());
        stopButton.setEnabled(position.getHasPodCast());
        pauseButton.setEnabled(position.getHasPodCast());
        seekbar.setEnabled(position.getHasPodCast());
        seekbar.setMax(position.getMaxDuration());
        seekbar.setProgress(position.getProgress());
        timer.setText(position.getTimer());
        if(player.getCurrentPodCast()!=null) pubDate.setText(player.getCurrentPodCast().getPubDate());
        description.setText(position.getDescription());
        detailsCurrentPodCast.setText(position.getMessage());
    }

    public boolean onCreateOptionsMenu(Menu menu){
        return onCreateOptionsMenu.onCreateOptionsMenu(menu, getMenuInflater()) ;
    }
}
