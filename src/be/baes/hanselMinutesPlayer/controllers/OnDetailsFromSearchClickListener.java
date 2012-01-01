package be.baes.hanselMinutesPlayer.controllers;

import android.view.View;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.view.Navigation;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 27/12/11
 * Time: 10:14
 */
public class OnDetailsFromSearchClickListener implements View.OnClickListener {
    @Inject
    Navigation navigation;

    @Override
    public void onClick(View view) {
        navigation.openDetails(Constants.SEARCH_ACTIVITY);
    }
}
