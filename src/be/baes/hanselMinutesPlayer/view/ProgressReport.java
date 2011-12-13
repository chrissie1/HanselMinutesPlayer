package be.baes.hanselMinutesPlayer.view;

import android.app.Activity;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 12/12/11
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */
public interface ProgressReport {
    void setActivity(Activity activity);

    void startProgress(String message);

    void endProgress();

    void updateProgess(String message);
}
