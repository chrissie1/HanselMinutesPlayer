package be.baes.hanselMinutesPlayer.facade.task;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.widget.Toast;
import be.baes.hanselMinutesPlayer.facade.PositionUpdater;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.view.ProgressReport;

import java.io.IOException;

public class OpeningPodCastAsyncTask extends AsyncTask<Void,Void,Void>{
    private ProgressReport progressReport;
    private Activity activity;
    private MediaPlayer mediaPlayer;
    private PositionUpdater positionUpdater;
    private PodCast currentPodCast;

    public OpeningPodCastAsyncTask(Activity activity,MediaPlayer mediaPlayer,PositionUpdater positionUpdater, PodCast currentPodCast,ProgressReport progressReport)
    {
        this.progressReport = progressReport;
        this.activity = activity;
        this.positionUpdater = positionUpdater;
        this.mediaPlayer = mediaPlayer;
        this.currentPodCast = currentPodCast;
    }

    @Override
    protected void onPreExecute()
    {
        progressReport.startProgress("Loading...");
    }

    @Override
    protected void onPostExecute(Void result)
    {
        progressReport.endProgress();
        positionUpdater.startPosition();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(currentPodCast.getMP3Link());
            mediaPlayer.prepare();
        } catch (IOException e) {
            Toast.makeText(activity, "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
            e.printStackTrace();
        }
        return null;
    }
}
