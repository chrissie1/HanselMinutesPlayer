package be.baes.hanselMinutesPlayer.facade;

import be.baes.hanselMinutesPlayer.facade.task.OpeningPodCastAsyncTask;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import android.media.MediaPlayer;

@Singleton
public class PlayerImpl implements Player {
	@Inject PositionUpdater positionUpdater;
    @Inject
    ProgressReport progressReport;
	private MediaPlayer mediaPlayer;
	private PodCast currentPodCast;
	
	public PlayerImpl()
	{
		mediaPlayer = new MediaPlayer();
	}

	@Override
    public void play()
	{
	    positionUpdater.updatePosition();
		mediaPlayer.start();
	}
	
	@Override
    public String getCurrentTitle() {
        if(currentPodCast==null) {
            return "No current podcast.";
        }
        else
        {
            return currentPodCast.getTitle();
        }
	}

	@Override
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
                OpeningPodCastAsyncTask task = new OpeningPodCastAsyncTask(mediaPlayer,positionUpdater,currentPodCast,progressReport);
                task.execute(null,null,null);
            }
        }
	}

	@Override
    public void stop()
	{
        positionUpdater.stopPosition();
		mediaPlayer.stop();
	}
	
	@Override
    public void pause()
	{
	    positionUpdater.pausePosition();
		mediaPlayer.pause();
	}
	
	@Override
    public int getCurrentPosition()
	{
		if(mediaPlayer==null) return 0;
		return mediaPlayer.getCurrentPosition();
	}
	
	@Override
    public int getDuration()
	{
		if(mediaPlayer==null) return 0;
		return mediaPlayer.getDuration();
	}

	@Override
    public void seekTo(int progress) {
		mediaPlayer.seekTo(progress);
	}
	
	@Override
    public void destroy()
	{
        positionUpdater.pausePosition();
		mediaPlayer.stop();
		mediaPlayer.reset();
		mediaPlayer.release();
		mediaPlayer = null;
	}
	
}
