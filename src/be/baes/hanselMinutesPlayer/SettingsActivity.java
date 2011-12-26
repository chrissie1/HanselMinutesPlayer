package be.baes.hanselMinutesPlayer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import be.baes.hanselMinutesPlayer.controllers.OnDeleteAllWithAlertDialogClickListener;
import be.baes.hanselMinutesPlayer.controllers.OnPlayerClickListener;
import be.baes.hanselMinutesPlayer.controllers.OnRefreshListWithAlertDialogClickListener;
import be.baes.hanselMinutesPlayer.controllers.OnRefreshListLatestWithAlertDialogClickListener;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
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
    @InjectView(R.id.closeButton) Button closeButton;
    @InjectView(R.id.refreshListLatestButton) Button refreshListLatestButton;
    @Inject OnRefreshListLatestWithAlertDialogClickListener onRefreshListLatestClickListener;
    @Inject OnRefreshListWithAlertDialogClickListener onRefreshListClickListener;
    @Inject OnDeleteAllWithAlertDialogClickListener onDeleteAllClickListener;
    @Inject OnPlayerClickListener onPlayerClickListener;
    @Inject PodCastList podCastList;
    @Inject ProgressReport progressReport;
    @Inject StringResources stringResources;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        progressReport.setActivity(this);
        podCastList.addObserver(this);
        refreshListLatestButton.setOnClickListener(onRefreshListLatestClickListener);
        refreshListButton.setOnClickListener(onRefreshListClickListener);
        deleteAllButton.setOnClickListener(onDeleteAllClickListener);
        closeButton.setOnClickListener(onPlayerClickListener);
        podCastList.load(0);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        progressReport.setActivity(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        totalInDatabase.setText(((FillListResult)o).getNumberOfPodCasts());
        totalDownloadedFiles.setText(stringResources.getTotalDownloadedFiles() + " " + ((FillListResult) o).getNumberOfDownloadedPodCasts());
    }
}