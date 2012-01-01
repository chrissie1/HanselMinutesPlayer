package be.baes.hanselMinutesPlayer.controllers;

import android.view.View;
import be.baes.hanselMinutesPlayer.view.Navigation;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 30/12/11
 * Time: 20:36
 * To change this template use File | Settings | File Templates.
 */
public class OnSearchClickListener implements View.OnClickListener {
    @Inject Navigation navigation;

    @Override
    public void onClick(View view) {
        navigation.openSearch();
    }
}
