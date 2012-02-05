package be.baes.hanselMinutesPlayer.controllers;

import android.view.View;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.view.Navigation;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 1/01/12
 * Time: 14:37
 */
public class OnViewGithubInBrowserOnClickListener implements View.OnClickListener {
    @Inject Navigation navigation;
    @Inject Player player;

    @Override
    public void onClick(View view) {
        navigation.openBrowser("https://github.com/chrissie1/HanselMinutesPlayer");
    }
}
