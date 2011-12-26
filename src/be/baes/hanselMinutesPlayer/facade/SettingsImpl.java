package be.baes.hanselMinutesPlayer.facade;

import android.content.res.Resources;
import be.baes.hanselMinutesPlayer.R;
import com.google.inject.Singleton;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 19/12/11
 * Time: 21:15
 */
@Singleton
public class SettingsImpl implements Settings {
    private Resources resources;
    private File cacheDirectory;

    @Override
    public void initialize(Resources resources, File cacheDirectory)
    {
        this.resources = resources;
        this.cacheDirectory = cacheDirectory;
    }

    @Override
    public String getTimerWithTime()
    {
        return resources.getString(R.string.TimerWithTime);
    }
    
    @Override
    public File getCacheDirectory()
    {
        return cacheDirectory;
    }

    @Override
    public String getPlaying() {
        return resources.getString(R.string.Playing);
    }

    @Override
    public String getPausing() {
        return resources.getString(R.string.Pausing);
    }

    @Override
    public String getStopped() {
        return resources.getString(R.string.Stopped);
    }

    @Override
    public String getSelected() {
        return resources.getString(R.string.Selected);
    }

    @Override
    public String getNoFileSelected() {
        return resources.getString(R.string.NoFileSelected);
    }

    @Override
    public String getProgressPercentage() {
        return resources.getString(R.string.ProgressPercentage);
    }

    @Override
    public String getDownloading() {
        return resources.getString(R.string.Downloading);
    }

    @Override
    public String getLoading() {
        return resources.getString(R.string.Loading);
    }

    @Override
    public String getListViewTitleText() {
        return resources.getString(R.string.ListViewTitleText);
    }

    @Override
    public int getColorGreen() {
        return resources.getColor(R.color.green);
    }

    @Override
    public int getColorWhite() {
        return resources.getColor(R.color.white);
    }

    @Override
    public String getNoPodCasts() {
        return resources.getString(R.string.NoPodCasts);
    }

    @Override
    public String getTotalLoaded() {
        return resources.getString(R.string.TotalLoaded);
    }

    @Override
    public String NoInternetConnection() {
        return resources.getString(R.string.NoInternetConnection);
    }

    @Override
    public String NoFileToDelete() {
        return resources.getString(R.string.NoFileToDelete);
    }

    @Override
    public String getDeleteAllTitle() {
        return resources.getString(R.string.DeleteAllTitle);
    }

    @Override
    public String getDeleteAllMessage() {
        return resources.getString(R.string.DeleteAllMessage);
    }

    @Override
    public String getDeleteDownloadMessage() {
        return resources.getString(R.string.DeleteDownloadMessage);
    }

    @Override
    public String getDeleteDownloadTitle() {
        return resources.getString(R.string.DeleteDownloadTitle);
    }

    @Override
    public String getDownloadPodcastMessage() {
        return resources.getString(R.string.DownloadPodcastMessage);
    }

    @Override
    public String getDownloadPodCastTitle() {
        return resources.getString(R.string.DownloadPodCastTitle);
    }

    @Override
    public String getRefreshListTitle() {
        return resources.getString(R.string.RefreshListTitle);
    }

    @Override
    public String getRefreshListMessage() {
        return resources.getString(R.string.RefreshListMessage);
    }
    
    @Override
    public String getTotalDownloadedFiles()
    {
        return resources.getString(R.string.totalDownloadedFiles);
    }
    
}
