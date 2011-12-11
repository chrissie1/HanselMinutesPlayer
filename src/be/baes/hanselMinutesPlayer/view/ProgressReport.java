package be.baes.hanselMinutesPlayer.view;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 11/12/11
 * Time: 15:59
 * To change this template use File | Settings | File Templates.
 */
public interface ProgressReport {
    void startProgress(String message);

    void endProgress();

    void updateProgess(String message);
}
