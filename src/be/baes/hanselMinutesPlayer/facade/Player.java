package be.baes.hanselMinutesPlayer.facade;

import android.app.Activity;
import be.baes.hanselMinutesPlayer.facade.task.OpeningPodCastAsyncTask;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import android.media.MediaPlayer;

@Singleton
public class Player 
{
	@Inject PositionUpdater positionUpdater;
    @Inject Activity activity;
    @Inject ProgressReport progressReport;
	private MediaPlayer mediaPlayer;
	private PodCast currentPodCast;
	
	public Player()
	{
		mediaPlayer = new MediaPlayer();
	}

	public void play()
	{
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
                OpeningPodCastAsyncTask task = new OpeningPodCastAsyncTask(activity,mediaPlayer,positionUpdater,currentPodCast,progressReport);
                task.execute();
            }
        }
	}

	public void stop()
	{
        positionUpdater.stopPosition();
		mediaPlayer.stop();
	}
	
	public void pause()
	{
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
