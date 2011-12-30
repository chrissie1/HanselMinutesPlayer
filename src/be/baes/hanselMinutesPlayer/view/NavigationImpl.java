package be.baes.hanselMinutesPlayer.view;

import android.app.Activity;
import android.content.Intent;
import be.baes.hanselMinutesPlayer.DetailsActivity;
import be.baes.hanselMinutesPlayer.HanselminutesPlayerActivity;
import be.baes.hanselMinutesPlayer.R;
import be.baes.hanselMinutesPlayer.SettingsActivity;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 30/12/11
 * Time: 10:26
 */
public class NavigationImpl implements Navigation{
    @Inject Activity activity;

    @Override
    public void openSettings() {
        Intent myIntent = new Intent(activity, SettingsActivity.class);
        activity.startActivity(myIntent);
    }

    @Override
    public void openDetails() {
        Intent myIntent = new Intent(activity, DetailsActivity.class);
        activity.startActivity(myIntent);
    }

    @Override
    public void openMain() {
        Intent myIntent = new Intent(activity, HanselminutesPlayerActivity.class);
        activity.startActivity(myIntent);
    }

    @Override
    public void openDetailsWitFlingAnimation() {
        Intent myIntent = new Intent(activity, DetailsActivity.class);
        activity.startActivity(myIntent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void openMainWithFlingAnimation() {
        Intent myIntent = new Intent(activity, HanselminutesPlayerActivity.class);
        activity.startActivity(myIntent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
