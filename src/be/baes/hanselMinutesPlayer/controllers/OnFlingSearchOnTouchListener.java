package be.baes.hanselMinutesPlayer.controllers;

import android.view.MotionEvent;
import android.view.View;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 29/12/11
 * Time: 13:31
 */
public class OnFlingSearchOnTouchListener implements View.OnTouchListener{
    @Inject OnFlingSearchGestureDetector onFlingSearchGestureDetector;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return onFlingSearchGestureDetector.onTouchEvent(motionEvent);
    }

    public void setDoDetail(boolean doDetail)
    {
        onFlingSearchGestureDetector.setDoDetail(doDetail);
    }
}
