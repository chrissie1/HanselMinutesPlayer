package be.baes.hanselMinutesPlayer.facade.task;

import android.os.AsyncTask;
import android.util.Log;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.Player;
import be.baes.hanselMinutesPlayer.facade.PodCastList;
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
    private Player player;
    private PodCastList podCastList;
    private ProgressReport progressReport;
    private File tempFile;

    public DownloadMp3AsyncTask(ProgressReport progressReport,Player player, PodCastList podCastList, File tempFile)
    {
        this.tempFile = tempFile;
        this.progressReport = progressReport;
        this.player = player;
        this.podCastList = podCastList;
    }

    @Override
    protected void onPreExecute()
    {
        progressReport.startProgress("Downloading...");
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
            URL url = new URL(player.getCurrentPodCast().getMP3Link());
            URLConnection ucon  = url.openConnection();
            File tempMp3 = new File(tempFile, player.getCurrentPodCast().getPodCastName());
            Log.i(Constants.LOG_ID, String.format("cachingdirectory:%s", tempFile.getPath()));
            Log.i(Constants.LOG_ID, String.format("Mp3:%s", tempMp3.getPath()));
            ucon.connect();
            int lenghtOfFile = ucon.getContentLength();
            InputStream input = new BufferedInputStream(url.openStream());
            OutputStream output = new FileOutputStream(tempMp3);
            byte data[] = new byte[1024];
            long total = 0;
            while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress(String.format("Progress: %d%%", total * 100 / lenghtOfFile));
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
            Log.i(Constants.LOG_ID, String.format("temp file size:%d", tempFile.getTotalSpace()));
        } catch (Exception e) {
            Log.e(Constants.LOG_ID, String.format("Error: %s", e.getMessage()),e);
            e.printStackTrace();
        }
        return null;
    }
}
