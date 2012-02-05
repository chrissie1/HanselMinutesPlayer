package be.baes.hanselMinutesPlayer.controllers;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import be.baes.hanselMinutesPlayer.R;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 5/02/12
 * Time: 14:38
 */
public class OnCreateOptionsMenu {
    @Inject OnSearchClickListener onSearchClickListener;
    @Inject OnAboutClickListener onAboutClickListener;
    @Inject OnSettingsClickListener onSettingsClickListener;

    public boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu, menu);
        MenuItem settingsMenu = menu.findItem(R.id.settingsMenu);
        MenuItem searchMenu = menu.findItem(R.id.searchmenu);
        MenuItem aboutMenu = menu.findItem(R.id.aboutmenu);
        settingsMenu.setOnMenuItemClickListener(onSettingsClickListener);
        searchMenu.setOnMenuItemClickListener(onSearchClickListener);
        aboutMenu.setOnMenuItemClickListener(onAboutClickListener);
        return true ;
    }
}
