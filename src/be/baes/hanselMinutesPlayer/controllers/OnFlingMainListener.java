package be.baes.hanselMinutesPlayer.controllers;

import android.app.Activity;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import be.baes.hanselMinutesPlayer.DetailsActivity;
import be.baes.hanselMinutesPlayer.R;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 28/12/11
 * Time: 21:39
 */
public class OnFlingMainListener extends GestureDetector.SimpleOnGestureListener {
    @Inject Activity activity;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Intent myIntent = new Intent(activity, DetailsActivity.class);
        activity.startActivity(myIntent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        return false;
    }

    // It is necessary to return true from onDown for the onFling event to register
    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }
}
