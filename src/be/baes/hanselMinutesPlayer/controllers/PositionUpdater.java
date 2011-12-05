package be.baes.hanselMinutesPlayer.controllers;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import roboguice.inject.InjectView;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.facade.Player;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class PositionUpdater {
	@InjectView(R.id.seekBar1) SeekBar seekbar;
	@InjectView(R.id.textView1) TextView textView1;
	@Inject Player player;
	
	private final Handler handler = new Handler();
    
    private final Runnable updatePositionRunnable = new Runnable() {
            public void run() {
                    updatePosition();
            }
    };
    
    public void startPosition()
    {
    	seekbar.setProgress(0);
    	seekbar.setMax(player.getDuration());
    	updatePosition();
    }
    
    public void stopPosition()
    {
    	handler.removeCallbacks(updatePositionRunnable);
    	textView1.setText("Timer: " + toMinutes(0) + "/" + toMinutes(player.getDuration()));
        seekbar.setProgress(0);
    }
    
    public void pausePosition()
    {
    	handler.removeCallbacks(updatePositionRunnable);
    }
    
    public void updatePosition(){
        handler.removeCallbacks(updatePositionRunnable);
        seekbar.setProgress(player.getCurrentPosition());
        textView1.setText("Timer: " + toMinutes(player.getCurrentPosition()) + "/" + toMinutes(player.getDuration()));
        handler.postDelayed(updatePositionRunnable, 500);
    }
    
    private String toMinutes(int milliSeconds)
    {
    	double durationInMin = ((double)milliSeconds/1000.0)/60.0;
        durationInMin = new BigDecimal(Double.toString(durationInMin)).setScale(2, BigDecimal.ROUND_UP).doubleValue();
        DecimalFormat myFormat = new DecimalFormat("00");
        String minutes = myFormat.format(new Integer((int)durationInMin));
        String seconds = myFormat.format(new Integer((int)((durationInMin - (double)((int)durationInMin))*60)));
        return minutes + ":" + seconds;
       
    }

}
