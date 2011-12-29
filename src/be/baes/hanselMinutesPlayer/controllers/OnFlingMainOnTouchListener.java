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
public class OnFlingMainOnTouchListener implements View.OnTouchListener{
    @Inject OnFlingMainGestureDetector onFlingMainGestureDetector;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return onFlingMainGestureDetector.onTouchEvent(motionEvent);
    }
}
