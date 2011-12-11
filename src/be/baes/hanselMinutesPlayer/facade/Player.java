package be.baes.hanselMinutesPlayer.facade;

import java.io.IOException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import android.media.MediaPlayer;
import android.util.Log;

@Singleton
public class Player 
{
	@Inject PositionUpdater positionUpdater;
	private MediaPlayer mediaPlayer;
	private boolean isPaused;
	private String currentFile;
	
	public Player()
	{
		mediaPlayer = new MediaPlayer();
	}

	public void play() throws IllegalStateException, IOException
	{
		if(!isPaused)
		{
			Log.i("cbaes", "Play started");
			mediaPlayer.reset();
			mediaPlayer.setDataSource(currentFile);
			mediaPlayer.prepare();
		}
		isPaused = false;
        positionUpdater.updatePosition();
		mediaPlayer.start();
	}
	
	public String getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(String currentFile) {
		stop();
		this.currentFile = currentFile;
        if(currentFile.equals(""))
        {
            positionUpdater.emptyFile();
        }
        else
        {
            positionUpdater.startPosition();
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
