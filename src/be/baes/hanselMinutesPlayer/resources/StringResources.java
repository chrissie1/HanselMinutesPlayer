package be.baes.hanselMinutesPlayer.resources;

import android.content.res.Resources;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 26/12/11
 * Time: 10:59
 * To change this template use File | Settings | File Templates.
 */
public interface StringResources {
    void initialize(Resources resources);

    String getTimerWithTime();

    String getPlaying();

    String getPausing();

    String getStopped();

    String getSelected();

    String getNoFileSelected();

    String getProgressPercentage();

    String getDownloading();

    String getLoading();

    String getListViewTitleText();

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
