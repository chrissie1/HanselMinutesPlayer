package be.baes.hanselMinutesPlayer.facade;

import be.baes.hanselMinutesPlayer.model.PodCast;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 10:42
 */
public interface Player {
    void play();

    String getCurrentTitle();

    void setCurrentFile(PodCast currentPodCast, File cacheDir);

    void stop();

    void pause();

    int getCurrentPosition();

    int getDuration();

    void seekTo(int progress);

    void destroy();

    PodCast getCurrentPodCast();

    void setDataSource(String path);
}
