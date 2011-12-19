package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.view.View;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.facade.task.DownloadMp3AsyncTask;
import be.baes.hanselMinutesPlayer.view.ProgressReport;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 13/12/11
 * Time: 9:31
 */
public class OnDownloadPodCastClickListener implements View.OnClickListener {
    @Inject PodCastAdapter podCastAdapter;
    @Inject Player player;
    @Inject PodCastList podCastList;
    @Inject ProgressReport progressReport;

    @Override
    public void onClick(View view) {
        Log.i(Constants.LOG_ID, "Clicked download podcast");
        DownloadMp3AsyncTask downloadMp3AsyncTask = new DownloadMp3AsyncTask(progressReport, player, podCastList, view.getContext().getExternalCacheDir());
        downloadMp3AsyncTask.execute(null,null,null);
    }
}
