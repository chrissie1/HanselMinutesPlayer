package be.baes.hanselMinutesPlayer.facade;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Observable;

import android.content.res.Resources;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.model.Position;
import android.os.Handler;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class PositionUpdater extends Observable {
	@Inject Player player;
	
	private final Handler handler = new Handler();
    private Position position;
    private Resources resources;
            
    public PositionUpdater()
    {
        position = new Position("","",0,0,false);        
    }

    private final Runnable updatePositionRunnable = new Runnable() {
            public void run() {
                    updatePosition(resources);
            }
    };

    public void emptyFile(Resources resources)
    {
        this.resources = resources;
        position.setProgress(0);
        position.setMaxDuration(0);
        position.setTimer(String.format(resources.getString(R.string.TimerWithTime), toMinutes(0), toMinutes(0)));
        position.setMessage(resources.getString(R.string.NoFileSelected));
        position.setHasPodCast(false);
        setChanged();
        notifyObservers(position);
    }

    public void startPosition(Resources resources)
    {
        this.resources = resources;
        position.setProgress(0);
        position.setMaxDuration(player.getDuration());
        position.setTimer(String.format(resources.getString(R.string.TimerWithTime), toMinutes(0), toMinutes(player.getDuration())));
        position.setMessage(String.format(resources.getString(R.string.Selected), player.getCurrentTitle()));
        position.setHasPodCast(true);
        setChanged();
        notifyObservers(position);
    }
    
    public void stopPosition(Resources resources)
    {
        this.resources = resources;
        handler.removeCallbacks(updatePositionRunnable);
        position.setTimer(String.format(resources.getString(R.string.TimerWithTime), toMinutes(0), toMinutes(player.getDuration())));
        position.setMessage(String.format(resources.getString(R.string.Stopped), player.getCurrentTitle()));
        position.setMaxDuration(player.getDuration());
        position.setHasPodCast(true);
        position.setProgress(0);
        setChanged();
        notifyObservers(position);
    }
    
    public void pausePosition(Resources resources)
    {
        this.resources = resources;
        position.setProgress(player.getCurrentPosition());
        position.setTimer(String.format(resources.getString(R.string.TimerWithTime), toMinutes(player.getCurrentPosition()), toMinutes(player.getDuration())));
        position.setMessage(String.format(resources.getString(R.string.Pausing), player.getCurrentTitle()));
        position.setMaxDuration(player.getDuration());
        position.setHasPodCast(true);
        handler.removeCallbacks(updatePositionRunnable);
        setChanged();
        notifyObservers(position);
    }
    
    public void updatePosition(Resources resources){
        this.resources = resources;
        handler.removeCallbacks(updatePositionRunnable);
        position.setProgress(player.getCurrentPosition());
        position.setTimer(String.format(resources.getString(R.string.TimerWithTime), toMinutes(player.getCurrentPosition()), toMinutes(player.getDuration())));
        position.setMessage(String.format(resources.getString(R.string.Playing), player.getCurrentTitle()));
        position.setMaxDuration(player.getDuration());
        position.setHasPodCast(true);
        handler.postDelayed(updatePositionRunnable, 500);
        setChanged();
        notifyObservers(position);
    }
    
    private String toMinutes(int milliSeconds)
    {
    	double durationInMin = ((double)milliSeconds/1000.0)/60.0;
        durationInMin = new BigDecimal(Double.toString(durationInMin)).setScale(2, BigDecimal.ROUND_UP).doubleValue();
        DecimalFormat myFormat = new DecimalFormat("00");
        String minutes = myFormat.format((int)durationInMin);
        String seconds = myFormat.format((int)((durationInMin - (double)((int)durationInMin))*60));
        return minutes + ":" + seconds;
       
    }

}
