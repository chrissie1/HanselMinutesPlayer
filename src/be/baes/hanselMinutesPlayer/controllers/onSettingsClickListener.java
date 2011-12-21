package be.baes.hanselMinutesPlayer.controllers;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import be.baes.hanselMinutesPlayer.SettingsActivity;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 20/12/11
 * Time: 9:58
 */
public class OnSettingsClickListener implements View.OnClickListener {
    @Inject Activity activity;

    @Override
    public void onClick(View view) {
        Intent myIntent = new Intent(activity, SettingsActivity.class);
        activity.startActivity(myIntent);
    }
}
