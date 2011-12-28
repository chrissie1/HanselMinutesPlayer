package be.baes.hanselMinutesPlayer.controllers;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import be.baes.hanselMinutesPlayer.DetailsActivity;
import be.baes.hanselMinutesPlayer.SettingsActivity;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 27/12/11
 * Time: 10:14
 */
public class OnDetailsClickListener implements View.OnClickListener {
    @Inject Activity activity;

    @Override
    public void onClick(View view) {
        Intent myIntent = new Intent(activity, DetailsActivity.class);
        activity.startActivity(myIntent);    }
}
