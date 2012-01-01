package be.baes.hanselMinutesPlayer.view;

import android.app.Activity;
import android.content.Intent;
import be.baes.hanselMinutesPlayer.*;
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
    public void openDetails(String parent) {
        Intent myIntent = new Intent(activity, DetailsActivity.class);
        myIntent.putExtra("parent", parent);
        activity.startActivity(myIntent);
    }

    @Override
    public void openSearch() {
        activity.onSearchRequested();
    }

    @Override
    public void openMain() {
        Intent myIntent = new Intent(activity, HanselminutesPlayerActivity.class);
        activity.startActivity(myIntent);
    }

    @Override
    public void openDetailsWitFlingAnimation(String parent) {
        Intent myIntent = new Intent(activity, DetailsActivity.class);
        myIntent.putExtra(Constants.PARENT, parent);
        activity.startActivity(myIntent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void openMainWithFlingAnimation() {
        Intent myIntent = new Intent(activity, HanselminutesPlayerActivity.class);
        activity.startActivity(myIntent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void openSearchWithFlingAnimation() {
        Intent myIntent = new Intent(activity, SearchTitlesActivity.class);
        activity.startActivity(myIntent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
