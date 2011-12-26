package be.baes.hanselMinutesPlayer.controllers;

import android.util.Log;
import android.view.View;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.facade.Settings;
import be.baes.hanselMinutesPlayer.view.YesNoAlertDialog;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: christiaan
 * Date: 13/12/11
 * Time: 9:31
 */
public class OnDownloadPodCastWithAlertDialogClickListener implements View.OnClickListener {
    @Inject Settings settings;
    @Inject YesNoAlertDialog yesNoAlertDialog;
    @Inject OnDownloadPodCastClickListener onDownloadPodCastClickListener;

    @Override
    public void onClick(View view) {
        Log.i(Constants.LOG_ID, "Clicked download podcast");
        yesNoAlertDialog.show(view, settings.getDownloadPodCastTitle(), settings.getDownloadPodcastMessage(), onDownloadPodCastClickListener, null);
    }

}
