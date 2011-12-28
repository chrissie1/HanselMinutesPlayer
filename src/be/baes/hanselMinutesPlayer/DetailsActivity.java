package be.baes.hanselMinutesPlayer;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import be.baes.hanselMinutesPlayer.controllers.*;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.PositionUpdater;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.model.Position;
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
    @InjectView(R.id.detailsPlayButton) Button playButton;
    @InjectView(R.id.detailsStopButton) Button stopButton;
    @InjectView(R.id.detailsPauseButton) Button pauseButton;
    @InjectView(R.id.detailsDeleteDownloadButton) Button deleteDownloadButton;
    @InjectView(R.id.detailsDownloadButton) Button downloadButton;
    @InjectView(R.id.detailsSeekBar) SeekBar seekbar;
    @InjectView(R.id.detailsTimer) TextView timer;
    @InjectView(R.id.detailscurrentPodCast) TextView currentPodCast;
    @InjectView(R.id.detailsDescription) TextView description;
    @Inject OnPlayClickListener onPlayClickListener;
    @Inject OnStopClickListener onStopClickListener;
    @Inject OnPauseClickListener onPauseClickListener;
    @Inject OnSeekChangeListener onSeekChangeListener;
    @Inject PositionUpdater positionUpdater;
    @Inject Player player;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

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
        description.setText(position.getDescription());
        currentPodCast.setText(position.getMessage());
    }
}
