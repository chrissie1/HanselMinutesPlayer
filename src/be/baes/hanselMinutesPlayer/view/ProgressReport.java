package be.baes.hanselMinutesPlayer.view;

import android.app.Activity;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 19:43
 */
public interface ProgressReport {
    void setActivity(Activity activity);

    void startProgress(String message);

    void endProgress();

    void updateProgess(String message);

    boolean IsInProgress();
}
