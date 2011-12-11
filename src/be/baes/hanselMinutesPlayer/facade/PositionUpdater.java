package be.baes.hanselMinutesPlayer.facade;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Observable;

import be.baes.hanselMinutesPlayer.model.Position;
import android.os.Handler;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class PositionUpdater extends Observable {
	@Inject Player player;
	
	private final Handler handler = new Handler();
    private Position position;

    public PositionUpdater()
    {
        position = new Position("Timer: 00:00/00:00","No file selected.",0,0,false);
    }

    private final Runnable updatePositionRunnable = new Runnable() {
            public void run() {
                    updatePosition();
            }
    };

    public void emptyFile()
    {
        position.setProgress(0);
        position.setMaxDuration(0);
        position.setTimer("Timer: 00:00/00:00");
        position.setMessage("No file selected.");
        position.setHasPodCast(false);
        setChanged();
        notifyObservers(position);
    }

    public void startPosition()
    {
    	position.setProgress(0);
        position.setMaxDuration(player.getDuration());
        position.setTimer("Timer: " + toMinutes(player.getCurrentPosition()) + "/" + toMinutes(player.getDuration()));
        position.setMessage("Selected: " + player.getCurrentTitle());
        position.setHasPodCast(true);
        setChanged();
        notifyObservers(position);
    }
    
    public void stopPosition()
    {
    	handler.removeCallbacks(updatePositionRunnable);
        position.setTimer("Timer: " + toMinutes(0) + "/" + toMinutes(player.getDuration()));
        position.setMessage("Stopped: " + player.getCurrentTitle());
        position.setProgress(0);
        setChanged();
        notifyObservers(position);
    }
    
    public void pausePosition()
    {
        position.setMessage("Pausing: " + player.getCurrentTitle());
        handler.removeCallbacks(updatePositionRunnable);
        setChanged();
        notifyObservers(position);
    }
    
    public void updatePosition(){
        handler.removeCallbacks(updatePositionRunnable);
        position.setProgress(player.getCurrentPosition());
        position.setTimer("Timer: " + toMinutes(player.getCurrentPosition()) + "/" + toMinutes(player.getDuration()));
        position.setMessage("Playing: " + player.getCurrentTitle());
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
