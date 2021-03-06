package be.baes.hanselMinutesPlayer.facade.task;

import android.os.AsyncTask;
import android.util.Log;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.PositionUpdater;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.view.ProgressReport;

import java.io.File;

public class OpeningPodCastAsyncTask extends AsyncTask<File,String,Void>{
    private ProgressReport progressReport;
    private Player player;
    private PodCast currentPodCast;
    private StringResources stringResources;
    private PositionUpdater positionUpdater;

    public OpeningPodCastAsyncTask(Player player, PodCast currentPodCast,ProgressReport progressReport, PositionUpdater positionUpdater, StringResources stringResources1)
    {
        this.positionUpdater = positionUpdater;
        this.progressReport = progressReport;
        this.player = player;
        this.currentPodCast = currentPodCast;
        this.stringResources = stringResources1;
    }

    @Override
    protected void onPreExecute()
    {
        progressReport.startProgress(stringResources.getLoading() + "PodCast");
    }

    @Override
    protected void onPostExecute(Void result)
    {
        progressReport.endProgress();
        positionUpdater.startPosition();
    }

    @Override
    protected void onProgressUpdate(String... messages)
    {
        progressReport.updateProgess(messages[0]);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(File... voids) {
        try {
            File downloadedFile = new File(voids[0] , currentPodCast.getPodCastName());
            if (downloadedFile.exists()) {
                Log.i(Constants.LOG_ID, String.format("Playing local file: %s", downloadedFile.getPath()));
                player.setDataSource(downloadedFile.getPath());
            }
            else
            {
                Log.i(Constants.LOG_ID, String.format("Playing remote file: %s", currentPodCast.getMP3Link()));
                player.setDataSource(currentPodCast.getMP3Link());
            }
        } catch (Exception e) {
            Log.e(Constants.LOG_ID, String.format("Error: %s", e.getMessage()),e);
            e.printStackTrace();
            publishProgress("Error: " + e.getMessage());
        }
        return null;
    }
}
