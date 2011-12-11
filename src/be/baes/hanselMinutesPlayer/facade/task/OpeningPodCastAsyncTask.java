package be.baes.hanselMinutesPlayer.facade.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.widget.Toast;
import be.baes.hanselMinutesPlayer.facade.PositionUpdater;
import be.baes.hanselMinutesPlayer.model.PodCast;

import java.io.IOException;

public class OpeningPodCastAsyncTask extends AsyncTask<Void,Void,Void>{
    private ProgressDialog dialog;
    private Activity activity;
    private MediaPlayer mediaPlayer;
    private PositionUpdater positionUpdater;
    private PodCast currentPodCast;

    public OpeningPodCastAsyncTask(Activity activity,MediaPlayer mediaPlayer,PositionUpdater positionUpdater, PodCast currentPodCast)
    {
        this.activity = activity;
        this.positionUpdater = positionUpdater;
        this.mediaPlayer = mediaPlayer;
        this.currentPodCast = currentPodCast;
    }

    @Override
    protected void onPreExecute()
    {
        dialog = new ProgressDialog(activity);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    protected void onPostExecute(Void result)
    {
        dialog.dismiss();
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
