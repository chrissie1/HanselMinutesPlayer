package be.baes.hanselMinutesPlayer.facade;

import java.io.IOException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import android.app.Activity;
import android.media.MediaPlayer;
import android.util.Log;

@Singleton
public class Player 
{
	@Inject Activity activity;
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
		mediaPlayer.start();
	}
	
	public String getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(String currentFile) {
		isPaused = false;
		this.currentFile = currentFile;
	}

	public void stop()
	{
		mediaPlayer.stop();
	}
	
	public void pause()
	{
		isPaused = true;
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
		mediaPlayer.stop();
		mediaPlayer.reset();
		mediaPlayer.release();
		mediaPlayer = null;
	}
	
}
