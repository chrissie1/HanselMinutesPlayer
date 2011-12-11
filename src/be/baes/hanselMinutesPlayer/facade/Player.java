package be.baes.hanselMinutesPlayer.facade;

import java.io.IOException;

import android.app.Activity;
import be.baes.hanselMinutesPlayer.facade.task.OpeningPodCastAsyncTask;
import be.baes.hanselMinutesPlayer.model.PodCast;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import android.media.MediaPlayer;
import android.util.Log;

@Singleton
public class Player 
{
	@Inject PositionUpdater positionUpdater;
    @Inject Activity activity;
	private MediaPlayer mediaPlayer;
	private boolean isPaused;
	private PodCast currentPodCast;
	
	public Player()
	{
		mediaPlayer = new MediaPlayer();
	}

	public void play()
	{
		isPaused = false;
        positionUpdater.updatePosition();
		mediaPlayer.start();
	}
	
	public String getCurrentTitle() {
        if(currentPodCast==null) {
            return "No current podcast.";
        }
        else
        {
            return currentPodCast.getTitle();
        }
	}

	public void setCurrentFile(PodCast currentPodCast) {
		if(this.currentPodCast != currentPodCast)
        {
            stop();
            this.currentPodCast = currentPodCast;
            if(currentPodCast == null)
            {
                positionUpdater.emptyFile();
            }
            else
            {
                OpeningPodCastAsyncTask task = new OpeningPodCastAsyncTask(activity,mediaPlayer,positionUpdater,currentPodCast);
                task.execute();
            }
        }
	}

	public void stop()
	{
        isPaused = false;
        positionUpdater.stopPosition();
		mediaPlayer.stop();
	}
	
	public void pause()
	{
		isPaused = true;
        positionUpdater.pausePosition();
		mediaPlayer.pause();
	}
	
	public int getCurrentPosition()
	{
		if(mediaPlayer==null) return 0;
		return mediaPlayer.getCurrentPosition();
	}
	
	public int getDuration()
	{
		if(mediaPlayer==null) return 0;
		return mediaPlayer.getDuration();
	}

	public void seekTo(int progress) {
		mediaPlayer.seekTo(progress);
	}
	
	public void destroy()
	{
        positionUpdater.pausePosition();
		mediaPlayer.stop();
		mediaPlayer.reset();
		mediaPlayer.release();
		mediaPlayer = null;
	}
	
}
