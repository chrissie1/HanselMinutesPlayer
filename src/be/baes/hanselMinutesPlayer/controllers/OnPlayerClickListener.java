package be.baes.hanselMinutesPlayer.controllers;

import android.app.Activity;
import android.view.View;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 20/12/11
 * Time: 9:58
 */
public class OnPlayerClickListener implements View.OnClickListener {
    @Inject Activity activity;

    @Override
    public void onClick(View view) {
        activity.finish();
    }
}
