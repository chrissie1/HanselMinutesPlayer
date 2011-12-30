package be.baes.hanselMinutesPlayer.facade.task;

import android.os.AsyncTask;
import android.util.Log;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.model.PodCast;
import be.baes.hanselMinutesPlayer.resources.StringResources;
import be.baes.hanselMinutesPlayer.view.ProgressReport;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 16/12/11
 * Time: 14:29
 */
public class DownloadMp3AsyncTask extends AsyncTask<Void,String,Void> {
    private PodCast currentPodCast;
    private PodCastList podCastList;
    private ProgressReport progressReport;
    private StringResources stringResources;
    private Settings settings;
    
    public DownloadMp3AsyncTask(ProgressReport progressReport, PodCast currentPodCast, PodCastList podCastList, StringResources stringResources1, Settings settings1)
    {
        this.stringResources = stringResources1;
        this.progressReport = progressReport;
        this.currentPodCast = currentPodCast;
        this.podCastList = podCastList;
        this.settings = settings1;
    }

    @Override
    protected void onPreExecute()
    {
        progressReport.startProgress(stringResources.getDownloading());
    }

    @Override
    protected void onPostExecute(Void result)
    {
        progressReport.endProgress();
        podCastList.load(0);
    }

    @Override
    protected void onProgressUpdate(String... messages)
    {
        progressReport.updateProgess(messages[0]);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int count;
        try {
            URL url = new URL(currentPodCast.getMP3Link());
            URLConnection urlConnection  = url.openConnection();
            File tempMp3 = new File(settings.getCacheDirectory(), currentPodCast.getPodCastName());
            Log.i(Constants.LOG_ID, String.format("cachingdirectory:%s", settings.getCacheDirectory().getPath()));
            Log.i(Constants.LOG_ID, String.format("Mp3:%s", tempMp3.getPath()));
            urlConnection.connect();
            int lenghtOfFile = urlConnection.getContentLength();
            InputStream input = new BufferedInputStream(url.openStream());
            OutputStream output = new FileOutputStream(tempMp3);
            byte data[] = new byte[1024];
            long total = 0;
            while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress(String.format(stringResources.getProgressPercentage(), total * 100 / lenghtOfFile));
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {
            Log.e(Constants.LOG_ID, String.format("Error: %s", e.getMessage()),e);
            e.printStackTrace();
        }
        return null;
    }
}
