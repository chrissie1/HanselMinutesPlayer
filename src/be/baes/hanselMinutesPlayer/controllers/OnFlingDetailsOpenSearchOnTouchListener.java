package be.baes.hanselMinutesPlayer.controllers;

import android.view.MotionEvent;
import android.view.View;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 29/12/11
 * Time: 13:24
 */
public class OnFlingDetailsOpenSearchOnTouchListener implements View.OnTouchListener{
    @Inject
    OnFlingDetailsOpenSearchGestureDetector onFlingDetailsGestureDetector;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return onFlingDetailsGestureDetector.onTouchEvent(motionEvent);
    }
}
