package be.baes.hanselMinutesPlayer.controllers;

import android.view.View;
import be.baes.hanselMinutesPlayer.view.Navigation;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 20/12/11
 * Time: 9:58
 */
public class OnSettingsClickListener implements View.OnClickListener {
    @Inject Navigation navigation;

    @Override
    public void onClick(View view) {
        navigation.openSettings();
    }
}
