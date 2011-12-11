package be.baes.hanselMinutesPlayer.facade.task;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import be.baes.hanselMinutesPlayer.facade.PositionUpdater;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.view.ProgressReport;

import java.io.IOException;

public class OpeningPodCastAsyncTask extends AsyncTask<Void,String,Void>{
    private ProgressReport progressReport;
    private MediaPlayer mediaPlayer;
    private PositionUpdater positionUpdater;
    private PodCast currentPodCast;

    public OpeningPodCastAsyncTask(MediaPlayer mediaPlayer,PositionUpdater positionUpdater, PodCast currentPodCast,ProgressReport progressReport)
    {
        this.progressReport = progressReport;
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
    protected Void doInBackground(Void... voids) {
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(currentPodCast.getMP3Link());
            mediaPlayer.prepare();
        } catch (IOException e) {
            publishProgress("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
