package be.baes.hanselMinutesPlayer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import be.baes.hanselMinutesPlayer.controllers.OnDeleteAllClickListener;
import be.baes.hanselMinutesPlayer.controllers.OnPlayerClickListener;
import be.baes.hanselMinutesPlayer.controllers.OnRefreshListClickListener;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.model.FillListResult;
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
 * To change this template use File | Settings | File Templates.
 */
public class SettingsActivity extends RoboActivity implements Observer{
    @InjectView(R.id.refreshListButton) Button refreshListButton;
    @InjectView(R.id.deleteAllButton) Button deleteAllButton;
    @InjectView(R.id.totalInDatabase) TextView totalInDatabase;
    @InjectView(R.id.closeButton) Button closeButton;
    @Inject OnRefreshListClickListener onRefreshListClickListener;
    @Inject OnDeleteAllClickListener onDeleteAllClickListener;
    @Inject OnPlayerClickListener onPlayerClickListener;
    @Inject PodCastList podCastList;
    @Inject ProgressReport progressReport;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        progressReport.setActivity(this);
        podCastList.addObserver(this);
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
    }
}