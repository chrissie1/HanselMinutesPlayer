package be.baes.hanselMinutesPlayer.facade;

import android.util.Log;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.task.DownloadMp3AsyncTask;
import be.baes.hanselMinutesPlayer.facade.task.OpeningPodCastAsyncTask;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import android.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

@Singleton
public class PlayerImpl implements Player {
	@Inject PositionUpdater positionUpdater;
    @Inject ProgressReport progressReport;
    @Inject Settings settings;
	private MediaPlayer mediaPlayer;
	private PodCast currentPodCast;
    @Inject PodCastList podCastList;

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
    public PodCast getCurrentPodCast()
    {
        return currentPodCast;
    }

    @Override
    public void setDataSource(String path) {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
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
                Log.i(Constants.LOG_ID, "currentPodCast is null");
                positionUpdater.emptyFile();
            }
            else
            {
                Log.i(Constants.LOG_ID, "currentPodCast is filled");
                OpeningPodCastAsyncTask task = new OpeningPodCastAsyncTask(this,currentPodCast, progressReport, positionUpdater, settings);
                task.execute(settings.getCacheDirectory(),null,null);
            }
        }
        else
        {
            Log.i(Constants.LOG_ID, "currentPodCast will not be updated because it did not change.");
        }
	}

	@Override
    public void stop()
	{
        positionUpdater.stopPosition();
        if(mediaPlayer!=null)
        {
		    mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }
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
        if(mediaPlayer!=null)
        {
		    mediaPlayer.stop();
		    mediaPlayer.reset();
		    mediaPlayer.release();
		    mediaPlayer = null;
        }
	}

    @Override
    public void downloadMp3() {
        if(currentPodCast==null) return;
        DownloadMp3AsyncTask downloadMp3AsyncTask = new DownloadMp3AsyncTask(progressReport, currentPodCast, podCastList, settings);
        downloadMp3AsyncTask.execute(null,null,null);
    }

    @Override
    public boolean hasCurrentPodCastDownloadedMp3() {
        if(currentPodCast==null) return false;
        File file = new File(settings.getCacheDirectory(),currentPodCast.getPodCastName());
        return file.exists();
    }

    @Override
    public void deleteCurrentPodCastDownLoadedMp3() {
        if(currentPodCast==null) return;
        if(!hasCurrentPodCastDownloadedMp3()) return;
        File file = new File(settings.getCacheDirectory(),currentPodCast.getPodCastName());
        file.delete();
    }


}
