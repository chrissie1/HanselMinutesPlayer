package be.baes.hanselMinutesPlayer.facade;

import android.content.res.Resources;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 20/12/11
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
public interface Settings {
    void initialize(Resources resources, File cacheDirectory);

    String getTimerWithTime();

    File getCacheDirectory();

    String getPlaying();

    String getPausing();

    String getStopped();

    String getSelected();

    String getNoFileSelected();

    String getProgressPercentage();

    String getDownloading();

    String getLoading();

    String getListViewTitleText();

    int getColorGreen();

    int getColorWhite();

    String getNoPodCasts();

    String getTotalLoaded();

    String NoInternetConnection();

    String NoFileToDelete();

    String getDeleteAllTitle();

    String getDeleteAllMessage();

    String getDeleteDownloadMessage();

    String getDeleteDownloadTitle();

    String getDownloadPodcastMessage();

    String getDownloadPodCastTitle();

    String getRefreshListTitle();

    String getRefreshListMessage();

    String getTotalDownloadedFiles();
}
