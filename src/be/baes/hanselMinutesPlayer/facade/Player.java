package be.baes.hanselMinutesPlayer.facade;

import be.baes.hanselMinutesPlayer.model.PodCast;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 10:42
 * To change this template use File | Settings | File Templates.
 */
public interface Player {
    void play();

    String getCurrentTitle();

    void setCurrentFile(PodCast currentPodCast);

    void stop();

    void pause();

    int getCurrentPosition();

    int getDuration();

    void seekTo(int progress);

    void destroy();
}
