package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.view.Navigation;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 28/12/11
 * Time: 21:39
 */
public class OnFlingSearchListener extends GestureDetector.SimpleOnGestureListener {
    @Inject Navigation navigation;
    private boolean doDetails = false;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    {
        Log.d("---onFling---", e1.toString() + e2.toString() + "");
        try
        {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;
            // right to left swipe
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && doDetails)
            {
                navigation.openDetailsWitFlingAnimation(Constants.SEARCH_ACTIVITY);

            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
            {
                navigation.openMainWithFlingAnimation();
            }
        }
        catch (Exception e)
        {
        // nothing
        }
        return false;
    }

    // It is necessary to return true from onDown for the onFling event to register
    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    public void setDoDetail(boolean doDetail)
    {
        this.doDetails = doDetail;
    }
}
