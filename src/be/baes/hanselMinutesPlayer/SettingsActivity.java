package be.baes.hanselMinutesPlayer;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import be.baes.hanselMinutesPlayer.controllers.*;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.model.FillListResult;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 20/12/11
 * Time: 10:01
 */
public class SettingsActivity extends RoboActivity implements Observer{
    @InjectView(R.id.refreshListButton) Button refreshListButton;
    @InjectView(R.id.deleteAllButton) Button deleteAllButton;
    @InjectView(R.id.totalInDatabase) TextView totalInDatabase;
    @InjectView(R.id.totalDowloadedFiles) TextView totalDownloadedFiles;
    @InjectView(R.id.refreshListLatestButton) Button refreshListLatestButton;
    @InjectView(R.id.onRoaming) CheckBox onRoaming;
    @Inject OnRefreshListLatestWithAlertDialogClickListener onRefreshListLatestWithAlertDialogClickListener;
    @Inject OnRefreshListWithAlertDialogClickListener onRefreshListWithAlertDialogClickListener;
    @Inject OnDeleteAllWithAlertDialogClickListener onDeleteAllWithAlertDialogClickListener;
    @Inject PodCastList podCastList;
    @Inject ProgressReport progressReport;
    @Inject StringResources stringResources;
    @Inject OnCreateOptionsMenu onCreateOptionsMenu;
    @Inject Settings settings;
    @Inject
    OnRoamingCheckChangeListener onRoamingCheckedChangeListener;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        progressReport.setActivity(this);
        if(progressReport.IsInProgress()) progressReport.startProgress("Continuing");
        setObservers();
        setListeners();
        podCastList.load(0);
        settings.getSettings();
    }

    private void setObservers() {
        podCastList.addObserver(this);
        settings.addObserver(this);
    }

    private void setListeners() {
        refreshListLatestButton.setOnClickListener(onRefreshListLatestWithAlertDialogClickListener);
        refreshListButton.setOnClickListener(onRefreshListWithAlertDialogClickListener);
        deleteAllButton.setOnClickListener(onDeleteAllWithAlertDialogClickListener);
        onRoaming.setOnCheckedChangeListener(onRoamingCheckedChangeListener);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        progressReport.setActivity(this);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        podCastList.deleteObserver(this);
        settings.deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o.getClass().equals(be.baes.hanselMinutesPlayer.model.Settings.class))
        {
            onRoaming.setChecked(((be.baes.hanselMinutesPlayer.model.Settings)o).getOnRoaming());
        }
        else
        {
            totalInDatabase.setText(((FillListResult)o).getNumberOfPodCasts());
            totalDownloadedFiles.setText(stringResources.getTotalDownloadedFiles() + " " + ((FillListResult) o).getNumberOfDownloadedPodCasts());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        return onCreateOptionsMenu.onCreateOptionsMenu(menu, getMenuInflater()) ;
    }
}